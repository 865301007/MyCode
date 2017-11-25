# -*- coding: utf-8 -*-
"""
Created on Tue Nov 21 22:07:52 2017

@author: 王崇菲

参考网站:http://docs.python-requests.org/zh_CN/latest/user/quickstart.html
"""

import requests

r=requests.get("http://www.baidu.com")

'''
>>> r = requests.put("http://httpbin.org/put")
>>> r = requests.delete("http://httpbin.org/delete")
>>> r = requests.head("http://httpbin.org/get")
>>> r = requests.options("http://httpbin.org/get")

传递URL参数：
>>> payload = {'key1': 'value1', 'key2': 'value2'}
>>> r = requests.get("http://httpbin.org/get", params=payload)


'''
# 默认会推测文本编码，也可以手动指定
import chardet
print(r.encoding)
#r.encoding='ISO-8859-1'

#用chardet检测编码，一般情况下不会出现乱码问题
print(chardet.detect(r.content))
r.encoding=chardet.detect(r.content)['encoding']
    
print (r.text)

"""
制定请求头
>>> url = 'https://api.github.com/some/endpoint'
>>> headers = {'user-agent': 'my-app/0.0.1'}

>>> r = requests.get(url, headers=headers)
"""

# 获得响应状态码

print(r.status_code)


