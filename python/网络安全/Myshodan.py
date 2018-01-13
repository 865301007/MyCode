# -*- coding: utf-8 -*-
"""
Created on Sun Jan  7 18:58:52 2018

@author: 王崇菲
"""

import shodan

SHODAN_API_KEY = "3BLrIqmeSmSJ0IybTMsNN1E4AKIEyixd"

api = shodan.Shodan(SHODAN_API_KEY)
# Wrap the request in a try/ except block to catch errors
try:
        # Search Shodan
        results = api.search('phpmyadmin')
      

        # Show the results
        print ('Results found: %s' % results['total'])
        for result in results['matches']:
                #print(result)
                print (result['ip_str']+':'+str(result['port']))
                
                #print (result['data'])
                #print ('')
except shodan.APIError as e:
        print ('Error: %s' % e)