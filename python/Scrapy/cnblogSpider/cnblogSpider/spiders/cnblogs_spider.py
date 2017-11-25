# -*- coding: utf-8 -*-
"""
Created on Thu Nov 23 20:22:35 2017

@author: 王崇菲

爬虫模块必须定义三个属性
1.name:用于区别Spider，必须唯一
2.start_urls:它是Spider启动时进行爬取的入口URL列表。
3.parse():被调用时，每个初始URL响应后返回的Response对象，将作为唯一的参数传递给该方法。
该方法负责解析返回的数据（resoponse data）、提取数据（生成item）以及生成需要进一步处理的URL的Request对象。
"""


'''
为了测xpath的效果，可以使用以下命令
scrapy shell "http://www.cnblogs.com/qiyeboy/default.html?page=1"
response.xpath(".//*[@class='postTitle']/a/text()").extract()
view(response)  #可以将获取的响应在浏览器中打开

'''

# 在根目录下执行scrapy crawl cnblogs 就能启动名称为cnblogs的爬虫
import scrapy
class CnblogsSpider(scrapy.Spider):
    name="cnblogs" #爬虫的名字
    allowed_domain=["cnblogs.com"] #允许的域名
    start_urls=["http://www.cnblogs.com/qiyeboy/default.html?page=1"]
    def parse(self,response):
        #实现网页的解析
        
        #首先抽取所有的文章
        papers=response.xpath(".//*[@class='day']")
        for paper in papers:
            url=paper.xpath(".//*[@class='postTitle']/a/@href").extract()[0]
            title=paper.xpath(".//*[@class='postTitle']/a/text()").extract()[0]
            time=paper.xpath(".//*[@class='dayTitle']/a/text()").extract()[0]
            content=paper.xpath(".//*[@class='postTitle']/a/text()").extract()[0]
            print (url,title,time,content)
        
        #从每篇文章中抽取数据
    
    
    