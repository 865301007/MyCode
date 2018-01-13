# -*- coding: utf-8 -*-
"""
Created on Sun Dec 17 22:18:28 2017

@author: 王崇菲
"""

import pymssql
import csv
import sys

maxInt = sys.maxsize
decrement = True

while decrement:
    # decrease the maxInt value by factor 10 
    # as long as the OverflowError occurs.

    decrement = False
    try:
        csv.field_size_limit(maxInt)
    except OverflowError:
        maxInt = int(maxInt/10)
        decrement = True
        
conn=pymssql.connect(host='192.168.31.232',user='sa',password='123456',database='other',charset='utf8')
'''
如果和本机数据库交互，只需修改链接字符串
conn=pymssql.connect(host='.',database='Michael')
'''

output = open('data', 'w')
cur=conn.cursor()

cur.execute

csv_reader = csv.reader(open('C:/dnf.csv', encoding='utf-8'))
index=1
for row in csv_reader:
    
    if  len(row)>1 and len(row[0])<32 and len(row[1])<32: 
        sql="INSERT INTO dnf_temp VALUES('"+row[0]+"','"+row[1]+"')"
        #print(sql)
        try:
            cur.execute(sql.encode('utf8'))
        except Exception as e:
            print(row[0]+","+row[1])
            
#ur.execute("INSERT INTO dbo.blacklist VALUES('452','111','13977679442','111','13977679442','FL5320')")
#如果update/delete/insert记得要conn.commit()
#否则数据库事务无法提交
conn.commit()
for row in cur:
    #chardet.detect(srow[2])
    print(row)

cur.close()

conn.close()