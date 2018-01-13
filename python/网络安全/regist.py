# -*- coding: utf-8 -*-
"""
Created on Mon Jan  8 23:07:36 2018

@author: 王崇菲
"""





url = 'http://88.hinews.cn/bbs/member.php?mod=register&inajax=1'

user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'

values = {'a9RwXs' : 'aaa','activationauth':'','e7O76d':'jialong1234','formhash':'19ed72cd',
          'referer':'http://88.hinews.cn/bbs/forum.php','j0FpaP':'jialong','Lbkzw3':'jialong','regsubmit':'yes'}

import requests



r = requests.post(url,data=values)

print(r.text)
