# -*- coding: utf-8 -*-
"""
Created on Tue Oct 31 16:07:09 2017

@author: tangkai
"""

print(__doc__)

import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.decomposition import PCA
from sklearn.metrics import precision_recall_fscore_support as prf
from sklearn.preprocessing import normalize
from time import time 

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

#将标签重复的剔除，即得到聚类结果对应的每一个类型（因为我们的训练数据是规律性的，所以可以这么处理）
def get_labels_num(labels):
    labels_num = []
    for label in labels:
        if label not in labels_num:
            labels_num.append(label)
    return labels_num

def get_kmeans_result(filename):
    #读取训练数据测试数据
    data, labels = read('data\\'+ filename +'_train.data')
    data_test , test_lable = read('data\\'+ filename +'_test.data');
    n_samples, n_features = data.shape
    n_digits = len(np.unique(labels))
    print("未使用数据降维，init='random'，n_init=10")
    print("n_digits: %d, \t n_samples %d, \t n_features %d"
          % (n_digits, n_samples, n_features))
    #kmeans = KMeans(init='k-means++', n_clusters=n_digits, n_init=10).fit(data)
    t0 = time()
    kmeans = KMeans(init='random', n_clusters=n_digits, n_init=10).fit(data)
    t1 = time()    
    predict_label = kmeans.predict(data_test)
    t2 = time()
    labels_dict = get_labels_num(labels)
        
    predict_label_d = []
    
    predict_label_d = [labels_dict[k] for k in predict_label]

    #print(predict_label_d)

    #print(test_lable)
    precision, recall, fbeta_score, support = prf(test_lable, predict_label_d, average='weighted')
    print('precision:', precision)
    print('recall:', recall)
    print('fbeta_socre:', fbeta_score)
    print('模型训练时间：',t1 -t0)
    print('测试数据预测时间：',t2 -t1)
    
    

def get_kmeans_pca_result(filename):
    data, labels = read('data\\'+ filename +'_train.data')
    data_test , test_lable = read('data\\'+ filename +'_test.data');
    n_samples, n_features = data.shape
    n_digits = len(np.unique(labels))
    print("数据降至二维，init='random'，n_init=10")
    print("n_digits: %d, \t n_samples %d, \t n_features %d"
          % (n_digits, n_samples, n_features))  
    #将数据降至二维
    reduced_data = PCA(n_components=2).fit_transform(data)
    reduced_data = normalize(reduced_data)
    t0 = time()
    kmeans_pca = KMeans(init='random', n_clusters=n_digits, n_init=10)
    t1 = time()
    kmeans_pca.fit(reduced_data)
    t2 = time()
    predict_data = PCA(n_components=2).fit_transform(data_test)
    predict_data = normalize(predict_data)
    y_kmeans = kmeans_pca.predict(predict_data) 
    labels_dict = get_labels_num(labels)
        
    predict_label_d = []
    
    predict_label_d = [labels_dict[k] for k in y_kmeans]
    precision, recall, fbeta_score, support = prf(test_lable, predict_label_d, average='weighted')
    print('precision:', precision)
    print('recall:', recall)
    print('fbeta_socre:', fbeta_score)
    print('模型训练时间：',t1 -t0)
    print('测试数据预测时间：',t2 -t1)
    plt.scatter(predict_data[:, 0], predict_data[:, 1], c=y_kmeans, s=50, cmap='viridis') 
    centers = kmeans_pca.cluster_centers_ 
    plt.scatter(centers[:, 0], centers[:, 1], c='black', s=200, alpha=0.5); 
    plt.show()
    
    

get_kmeans_result('Leukemia1')
get_kmeans_pca_result('Leukemia1')
