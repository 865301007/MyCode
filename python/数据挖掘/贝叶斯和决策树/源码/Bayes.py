# -*- coding: utf-8 -*-
"""
Created on Mon Oct 16 20:44:38 2017

@author: R212-1
"""
#伯努利朴素贝叶斯

import numpy as np
from sklearn.naive_bayes import BernoulliNB  
    
def read(filename):
    data=[]
    fr=open(filename)
    Y = fr.readline().strip().split(",")
    for line in fr.readlines():
        lineArr=line.strip().split(",")
        data.append(lineArr)
        
    X = [[r[col] for r in data] for col in range(len(data[0]))]
    
    X_Array = np.array(X, dtype = float)
    
    return X_Array, Y

def myPredict(filetrain, filetest):
    X, Y = read(filetrain)
    clf = BernoulliNB()  
    clf.fit(X, Y)
    BernoulliNB(alpha=1.0, binarize=0.0, class_prior=None, fit_prior=True)  
    X1, Y1 = read(filetest)
    result = clf.predict(X1)
    length = len(result)
    num = 0
    print("真实值、预测值、目前正确个数")
    for i in range(0, length):
        if(Y1[i] == result[i]):
            num = num + 1
        print(Y1[i], result[i], num)
    print("总数：%d  正确数：%d  正确率：%f" %( length, num, num*1.0/length))

print("\nLeukemia1训练及测试结果如下：")
myPredict("data\Leukemia1_train.data", "data\Leukemia1_test.data")

print("\nLeukemia2训练及测试结果如下：")
myPredict("data\Leukemia2_train.data", "data\Leukemia2_test.data")

print("\nBreast训练及测试结果如下：")
myPredict("data\Breast_train.data", "data\Breast_test.data")

print("\nGCM训练及测试结果如下：")
myPredict("data\GCM_train.data", "data\GCM_test.data")


