# -*- coding: utf-8 -*-
"""
Created on Sun Oct 29 19:09:38 2017

@author: tangkai
"""

print(__doc__)

import numpy as np

from sklearn.cluster import DBSCAN
from time import time 
#from sklearn.datasets.samples_generator import make_blobs
#from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
from sklearn.metrics import precision_recall_fscore_support as prf
from sklearn.preprocessing import normalize

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

# #############################################################################
#将标签重复的剔除，即得到聚类结果对应的每一个类型（因为我们的训练数据是规律性的，所以可以这么处理）
def get_labels_num(labels):
    labels_num = []
    for label in labels:
        if label not in labels_num:
            labels_num.append(label)
    return labels_num

def change_labels_2_num(labels):
    labels_dict = {}
   # print(labels)
    k = 0
    for i in labels:
        if i not in labels_dict.keys():
            labels_dict[i] = k
            k += 1
    #print(labels_dict)
    for i in range(len(labels)):
       labels[i] = labels_dict[labels[i]]      
    #print(labels)
    return labels

# #############################################################################
def get_DBSCAN_result(filename, eps, min_samples):
    #读取训练数据测试数据
    data, labels = read('data\\'+ filename +'_train.data')
    #data_test , test_lable = read('data\\'+ filename +'_test.data');
    #labels_dict = get_labels_num(labels)
    t0 = time()
    dbscan = DBSCAN(eps=eps, min_samples=min_samples).fit(data)
    #预测结果
    t1 = time()
    predict_label = dbscan.labels_
    labels = change_labels_2_num(labels)
    #predict_label_d = []
    #predict_label_d = [labels_dict[k] for k in predict_label]
    print("数据未降维 eps:", eps,"\t min_samples: " , min_samples)
    precision, recall, fbeta_score, support = prf(labels, predict_label, average='weighted')
    print('precision:', precision)
    print('recall:', recall)
    print('fbeta_socre:', fbeta_score)
    print('所用时间：',t1 -t0)
    
def get_DBSCAN_pca_result(filename, eps, min_samples):
    #读取训练数据测试数据
    data, labels = read('data\\'+ filename +'_train.data')
    #将数据降至二维
    reduced_data = PCA(n_components=2).fit_transform(data)
    reduced_data = normalize(reduced_data)
    #print(reduced_data)
    #data_test , test_lable = read('data\\'+ filename +'_test.data');
    #labels_dict = get_labels_num(labels)
    t0 = time()
    dbscan = DBSCAN(eps=eps, min_samples=min_samples, metric='euclidean', algorithm='auto', leaf_size=30, p=None, n_jobs=1).fit(reduced_data)
    #预测结果
    t1 = time()
    #print(labels_dict) 
    labels = change_labels_2_num(labels)
    predict_label = dbscan.labels_
    #print(predict_label)
    #for temp in predict_label:
    #    print(temp)
    print("数据降至二维 eps:", eps,"\t min_samples: " , min_samples)
    precision, recall, fbeta_score, support = prf(labels, predict_label, average='weighted')
    print('precision:', precision)
    print('recall:', recall)
    print('fbeta_socre:', fbeta_score)
    print('所用时间：',t1 -t0)

get_DBSCAN_result('Leukemia1', 0.5, 3)
eps_s = [0.25, 0.5, 0.75]
min_samples_s = [3, 5, 7]
for eps in eps_s:
    for min_sample in min_samples_s:
        get_DBSCAN_pca_result('Leukemia1', eps, min_sample)
    




    
