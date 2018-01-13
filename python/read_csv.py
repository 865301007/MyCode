# -*- coding: utf-8 -*-
"""
Created on Sun Dec 17 19:22:49 2017

@author: 王崇菲
"""
import csv
csv_reader = csv.reader(open('C:/dnf.csv', encoding='utf-8'))
for row in csv_reader:
    print(row)
    if len(row[0])<32 and len(row[1])<32:
        sql="INSERT INTO dnf_temp VALUES('"+row[0]+"','"+row[1]+"')"
    print(sql)