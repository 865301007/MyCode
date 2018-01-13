# -*- coding: utf-8 -*-

import scrapy

from scrapy import Selector
from personal.items import PersonalItem
class PersonalSpider(scrapy.Spider):
    name="personal" #爬虫的名字
    allowed_domain=["xiaoweizb.com"] #允许的域名
    start_urls=["http://www.xiaoweizb.com/touzi/index?p=1629"]
    def parse(self,response):
       strs=response.xpath("//li/a/@href").extract()
       for st in strs:
            url="http://www.xiaoweizb.com"+st
            yield scrapy.Request(url,callback=self.parse_detile)
       next=response.xpath("//*[@id='wx_touzi_list']/div/div/ul/a[9]/@href").extract()
       next_url="http://www.xiaoweizb.com"+next[0]
       yield scrapy.Request(next_url,callback=self.parse)
                
    def parse_detile(self,response):
        names=Selector(response).re(u'姓名\W*(\w*)')
        person_ids=Selector(response).re(u'身份证号码\W*(\d+)')
        addresss=Selector(response).re(u'家庭住址\W*(\w*)')
        name=''
        person_id=''
        address=''
        if len(names)<1 and len(person_ids)<1 and len(addresss)<1:
            return
        if len(names)>0:
            name=names[0]
        if len(person_ids)>0:
            person_id=person_ids[0]
        if len(addresss)>0:
            address=addresss[0]
        item=PersonalItem(name=name,person_id=person_id,address=address)
        yield item