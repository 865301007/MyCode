# -*- coding: utf-8 -*-
"""
Created on Thu Sep 28 11:41:44 2017

@author: 王崇菲
"""

import pyfpgrowth
from numpy import *
import itertools
  
support_dic = {}
  
#生成原始数据，用于测试
def loadDataSet():
    data=[]
    fr=open("D:\课业\数据挖掘\实验1\kosarak.dat")
    for line in fr.readlines(1000000):
        lineArr=line.strip().split()
        data.append(lineArr)
    return data

transactions = loadDataSet()
patterns = pyfpgrowth.find_frequent_patterns(transactions, 990)
rules = pyfpgrowth.generate_association_rules(patterns, 0.3)

print (patterns)
print(rules)