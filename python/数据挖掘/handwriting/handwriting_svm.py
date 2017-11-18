# -*- coding: utf-8 -*-
"""
Created on Fri Nov  3 12:05:37 2017

@author: 王崇菲
"""

import numpy as np
from sklearn import svm

def load_date(filename):
    data=[]
    fr=open(filename)
    #Y = fr.readline().strip().split(",")
    for line in fr.readlines():
        lineArr=line.strip().split(",")
        data.append(lineArr)
   # print (data) 
    data = np.array(data,dtype=int)
    X=data[:,0:-1]
    Y=data[:,-1]
    return X, Y

def train(X,Y):
    clf=svm.SVC(decision_function_shape='ovo',C=10,kernel='rbf',gamma=0.001)
    clf.fit(X,Y)
    return clf

def accuracy(clf,test_X,test_Y):
    result=clf.predict(test_X)
    length=len(result)
    num=0
    for i in range(0,length):
        if test_Y[i]==result[i]:
            num=num+1
    return num*1.0/length
        


    

train_X,train_Y=load_date("optdigits.tra")
test_X,test_Y=load_date("optdigits.tes")

clf=train(train_X,train_Y)

print ("训练数据准确率：",accuracy(clf,train_X,train_Y))
print ("测试数据准确率：",accuracy(clf,test_X,test_Y))

"""
参考：http://blog.csdn.net/chunxiao2008/article/details/50448154
     http://blog.csdn.net/han_xiaoyang/article/details/50282141

svm.SVC参数说明
C           越大，相当于惩罚松弛变量，希望松弛变量接近0，即对误分类的惩罚增大，趋向于对训练集全分对的情况，这样对训练集测试时准确率很高，但泛化能力弱。
            C值小，对误分类的惩罚减小，允许容错，将他们当成噪声点，泛化能力较强。
            
kernel      核函数，默认是rbf,可以选择线性、多项式、RBF函数、sigmoid函数
            Linear核：主要用于线性可分的情形。参数少，速度快，对于一般数据，分类效果已经很理想了。
            RBF核：主要用于线性不可分的情形。参数多，分类结果非常依赖于参数。无论是小样本还是大样本，高维还是低维等情况，RBF核函数均适用。线性核函数是RBF的一个特例，也就是说如果考虑使用RBF，那么就没有必要考虑线性核函数了。

gamma       核函数参数 gamma越大，支持向量越少，gamma值越小，支持向量越多


"""



