# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

'''
Item对象是一种简单的容器，用于保存爬到的内容
提供了类似词典的API以及用于声明可用字段的简单语法，需要继承Scrapy.Item
'''
import scrapy


class CnblogspiderItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    url=scrapy.Field()
    time=scrapy.Field()
    title=scrapy.Field()
    content=scrapy.Field()
    cimage_urls = scrapy.Field()
    cimages = scrapy.Field()
