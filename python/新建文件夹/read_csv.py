# -*- coding: utf-8 -*-
"""
Created on Sun Dec 17 19:22:49 2017

@author: 王崇菲
"""
import csv
csv_reader = csv.reader(open('E:\迅雷下载\网贷黑名单.csv', encoding='utf-8'))
for row in csv_reader:
    print(row)
    sql="INSERT INTO dbo.blacklist VALUES('"+row[2]+"','"+row[3]+"','"+row[1]+"','"+row[0]+"','"+row[4]+"','"+row[5]+"+')"
    print(sql)