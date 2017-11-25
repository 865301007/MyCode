# -*- coding: utf-8 -*-
"""
Created on Sun Nov 19 19:14:48 2017

@author: 王崇菲
"""

import requests
import urllib
import re
import requests
import os




from lxml import etree


# url="http://www.xiaoweizb.com/touzi/30461.html"



def get_image(url):
    user_agent='Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36'
    headers={'User-Agent':user_agent}
    r=requests.get(url,headers=headers)
    html = etree.HTML(r.text)

    detail_urls = html.xpath('.//a/@href')
    detail_urls = sorted(set(detail_urls), key=detail_urls.index)
    pattern = re.compile(r'.*[0-9]*.png|.*[0-9]*.jpg')
    print("**********"+url)
    if(os.path.exists(url[-10:-5])):
        return
    os.mkdir(url[-10:-5])
    for detail_url in detail_urls:
        if (re.match(pattern, detail_url)):
                image_url="http://www.xiaoweizb.com"+detail_url
                print(image_url)
                u = urllib.request.urlopen(image_url)  
                data = u.read()  
                f = open(url[-10:-5]+"/"+detail_url[-12:],'wb+')  
                f.write(data)  
                
    print("**********")
    
    
    

         
def get_info(url):
    user_agent='Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36'
    headers={'User-Agent':user_agent}
    r=requests.get(url,headers=headers)
    html = etree.HTML(r.text)

    detail_urls = html.xpath('.//a/@href')
    detail_urls = sorted(set(detail_urls), key=detail_urls.index)
    pattern = re.compile(r'.*[0-9]*.html')
    for detail_url in detail_urls:
        if (re.match(pattern, detail_url)):
            get_image("http://www.xiaoweizb.com"+detail_url)
            
#url="http://www.xiaoweizb.com/touzi/index"   
#get_info(url)
for i in range(243,250):
    url="http://www.xiaoweizb.com/touzi/index?p="+str(i)
    print("***")
    print(url)
    print("***")
    get_info(url)

#上次下载到44页
