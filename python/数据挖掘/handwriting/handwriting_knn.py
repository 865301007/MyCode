# -*- coding: utf-8 -*-
"""
Created on Mon Nov  6 14:58:20 2017

@author: 王崇菲
"""

import numpy as np
import pandas as pd
from sklearn.decomposition import PCA
from sklearn.neighbors import KNeighborsClassifier
from sklearn.cross_validation import train_test_split
from sklearn.cross_validation import cross_val_score
from sklearn import metrics
import matplotlib.pyplot as plt
import time

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

def accuracy(neighbors,test_X,test_Y):
    result=neighbors.predict(test_X)
    length=len(result)
    num=0
    for i in range(0,length):
        if test_Y[i]==result[i]:
            num=num+1
    return num*1.0/length

train_X,train_Y=load_date("optdigits.tra")
test_X,test_Y=load_date("optdigits.tes")

train_num=train_X.shape[0]
test_num=test_X.shape[0]

t=time.time()
#pca=PCA(n_components=0.8)
#train_X=pca.fit_transform(train_X)
#test_X=pca.fit_transform(test_X)

neighbors=KNeighborsClassifier(n_neighbors=1)

scores = cross_val_score(neighbors, train_X, train_Y, cv=10, scoring='accuracy')
print (scores)


neighbors.fit(train_X,train_Y)

print ("训练数据准确率：",accuracy(neighbors,train_X,train_Y))
print ("测试数据准确率：",accuracy(neighbors,test_X,test_Y))


k_range = range(1,31)
k_scores = []
for k in k_range:
    knn = KNeighborsClassifier(n_neighbors=k)
    scores = cross_val_score(knn, train_X, train_Y, cv=10, scoring='accuracy')
    k_scores.append(scores.mean())

print (k_scores)


plt.plot(k_range, k_scores)
plt.xlabel("Value of K for KNN")
plt.ylabel("Cross validated accuracy")
plt.show()

'''

参考:http://blog.csdn.net/jasonding1354/article/details/50562513

以下代码是画准确率随k值变化的曲线

X=[]
Y=[]
for n in range(1,15):
    neighbors=KNeighborsClassifier(n_neighbors=n)
    neighbors.fit(train_X,train_Y)
    X.append(n)
    Y.append(accuracy(neighbors,test_X,test_Y))

plt.plot(X,Y)
plt.xlabel("k")  
plt.ylabel("accuracy")  
plt.show()
    
'''



#print ("时间:  ",time.time()-t)

'''

参数解释

n_neighbors : int, optional (default = 5)
                Number of neighbors to use by default for kneighbors queries.
weights : str or callable, optional (default = ‘uniform’)
            weight function used in prediction. Possible values:
            ‘uniform’ : uniform weights. All points in each neighborhood are weighted equally.
            ‘distance’ : weight points by the inverse of their distance. in this case, closer neighbors of a query point will have a greater influence than neighbors which are further away.
            [callable] : a user-defined function which accepts an array of distances, and returns an array of the same shape containing the weights.
algorithm : {‘auto’, ‘ball_tree’, ‘kd_tree’, ‘brute’}, optional
            Algorithm used to compute the nearest neighbors:
            ‘ball_tree’ will use BallTree
            ‘kd_tree’ will use KDTree
            ‘brute’ will use a brute-force search.
            ‘auto’ will attempt to decide the most appropriate algorithm based on the values passed to fit method.
            Note: fitting on sparse input will override the setting of this parameter, using brute force.
leaf_size : int, optional (default = 30)
            Leaf size passed to BallTree or KDTree. This can affect the speed of the construction and query, as well as the memory required to store the tree. The optimal value depends on the nature of the problem.
p : integer, optional (default = 2)
            Power parameter for the Minkowski metric. When p = 1, this is equivalent to using manhattan_distance (l1), and euclidean_distance (l2) for p = 2. For arbitrary p, minkowski_distance (l_p) is used.
metric : string or callable, default ‘minkowski’
            the distance metric to use for the tree. The default metric is minkowski, and with p=2 is equivalent to the standard Euclidean metric. See the documentation of the DistanceMetric class for a list of available metrics.
            metric_params : dict, optional (default = None)
            Additional keyword arguments for the metric function.
n_jobs : int, optional (default = 1)
            The number of parallel jobs to run for neighbors search. If -1, then the number of jobs is set to the number of CPU cores. Doesn’t affect fit method.
'''


