# -*- coding: utf-8 -*-
"""
Created on Tue Nov 28 11:27:47 2017

@author: 王崇菲
"""


from scrapy.http import Request
from scrapy import Selector
from blacklist.items import BlacklistItem
import scrapy
class BlackSpider(scrapy.Spider):
    name="blacklist"
    allowed_domain=["xinyongheimingdan.cc"]
    def start_requests(self):
      yield Request("https://www.xinyongheimingdan.cc/",cookies=dict(__cfduid='dac4943422d8c9e73abf65f702b9b39171511698740', PHPSESSID='uepr0r1gv6fvhtkkrds6hrjkbu'), callback=self.parse)
    def parse(self,response):
        strs=response.xpath("//tr/@onclick").extract()
        for st in strs:
            url="https://www.xinyongheimingdan.cc/blacklist-"+st[-12:-2]+".html"
            yield scrapy.Request(url,cookies=dict(__cfduid='dac4943422d8c9e73abf65f702b9b39171511698740', PHPSESSID='uepr0r1gv6fvhtkkrds6hrjkbu'),callback=self.parse_detile)
            for i in range(2,132):
                yield scrapy.Request("https://www.xinyongheimingdan.cc/"+"s?p="+str(i),callback=self.parse)
                
    def parse_detile(self,response):
        name=Selector(response).re(u'姓名：(\w*)')[0]
        id_number=Selector(response).re(u'身份证号码：(\d+)')[0]
        tel=Selector(response).re(u'手机号码：(\d+)')[0]
        wechat=Selector(response).re(u'微信：(\w*)')[0]
        alipay=Selector(response).re(u'支付宝：(\w*)')[0]
        address=Selector(response).re(u'家庭地址：(\w*)')[0]
        item=BlacklistItem(name=name,id_number=id_number,tel=tel,wechat=wechat,alipay=alipay,address=address)
        '''
        print("***************************************")
        print(name)
        print(id_number)
        print(tel)
        print(wechat)
        print(alipay)
        print(address)
        '''
        yield item
        
        
        
        
        
        
        
        
        
        