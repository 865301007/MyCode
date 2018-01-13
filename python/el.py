# -*- coding: utf-8 -*-
"""
Created on Sat Dec 23 21:15:41 2017

@author: 王崇菲
"""

from elasticsearch import Elasticsearch
from elasticsearch import helpers
es = Elasticsearch()

def find_id_by_account_and_password(account,password):
    query={"query": {"bool":{"should": [{ "match": { "account": account }},{ "match": { "author": password   }}]}}}
    results=es.search(index='sgdb',doc_type='sgdb',body=query)
    for result in results['hits']['hits']:
        yield result.get('_id')
    

if __name__ == '__main__':
   query={"query" : {"match_all" : {}}}
   response= es.search(body=query,scroll="1m",size=5000)
   scroll_id=response.get('_scroll_id')
   count=0;
   while scroll_id :
       count=count+1;
       if count % 100 ==0:
           print("scroll"+str(count))
       results=es.scroll(scroll_id=scroll_id,scroll="100m")
       scroll_id=results.get('_scroll_id')
      
       for result in results['hits']['hits']:
           doc_id=result.get('_id')
           account=result.get('_source').get('account')
           password=result.get('_source').get('password')
           if account and password:
               query={"query": {
                       "bool": {
                               "must": [
                                       {
                                               "term": {
                                                       "account": {
                                                               "value": account
                                                               }
                                                       }
                                                       },
                                                       {
                                                               "term": {
                                                                       "password": {
                                                                               "value": password
                                                                               }
                                                                       }
                                                                       }
                                                                       ]
    
        }
    }}
               query_results=es.search(index='sgdb',doc_type='sgdb',body=query)
               total=query_results.get('hits').get('total')
               if total<2:
                   pass
               else:
                   print(account+","+password+","+str(total))
                   for query_result in query_results['hits']['hits']:
                           dup_id=query_result.get('_id')
                           if dup_id != doc_id: 
                               es.delete(index='sgdb',doc_type='sgdb',id=dup_id)
            
    
