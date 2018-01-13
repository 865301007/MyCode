# -*- coding: utf-8 -*-
"""
Created on Sun Dec 17 19:37:59 2017

@author: 王崇菲
"""

import pymssql
import csv
csv_reader = csv.reader(open('E:\迅雷下载\网贷黑名单.csv', encoding='utf-8'))


conn=pymssql.connect(host='192.168.31.232',user='sa',password='123456',database='other',charset='utf8')
'''
如果和本机数据库交互，只需修改链接字符串
conn=pymssql.connect(host='.',database='Michael')
'''
cur=conn.cursor()

for row in csv_reader:
    print(row)
    sql="INSERT INTO dbo.blacklist VALUES('"+row[2]+"','"+row[3]+"','"+row[1]+"','"+row[0]+"','"+row[4]+"','"+row[5]+"')"
    print(sql)
    cur.execute(sql.encode('utf8'))
#如果update/delete/insert记得要conn.commit()
#否则数据库事务无法提交
conn.commit()
for row in cur:
    #chardet.detect(srow[2])
    print(row)

cur.close()

conn.close()