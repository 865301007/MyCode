# -*- coding: utf-8 -*-
"""
Created on Tue Nov 28 11:27:47 2017

@author: 王崇菲
"""

import requests
import scrapy
class BlackSpider(scrapy.Spider):
    name="black"
    allowed_domain=["xinyongheimingdan.cc"]
    