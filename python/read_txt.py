# -*- coding: utf-8 -*-
"""
Created on Sun Dec 17 21:49:26 2017

@author: 王崇菲
"""

f = open("C:/dnf.csv",encoding='UTF-8')
line=f.readlines(1000000)
print(line)
for raw in line:
    vector=raw.split(',')
    while '' in vector:
        vector.remove('')
    print(len(vector))
    print (vector)
