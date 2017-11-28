# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html

'''
item在Spider中被收集之后就会被传递给item pipeline
定制完item pipeline 它是无法工作的，需要进行激活

ITEM_PIPELINES确定每个pipeline的运行顺序，在0-1000范围内

也可以直接通过命令输出
scrapy crawl cnblogs -p paper.csv
输出csv格式是乱码的，可以用notepad++进行转码
'''
import json
from scrapy.exceptions import DropItem
class CnblogspiderPipeline(object):
    def __init__(self):
        self.file=open('pepers.json','w')
        
    def process_item(self, item, spider):
        if item['title']:
            line=json.dumps(dict(item))+"\n"
            self.file.write(line)
            return item
        else:
            raise DropItem("Missing title in %s" %  item)
            
