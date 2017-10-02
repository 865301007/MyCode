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
  
#获取整个数据库中的一阶元素
# C1 = {1, 2, 3, 4, 5} 
def createC1(dataSet):
    C1 = set([])
    for item in dataSet:
        C1 = C1.union(set(item))
    return [frozenset([i]) for i in C1]
  
#输入数据库（dataset） 和 由第K-1层数据融合后得到的第K层数据集（Ck），
#用最小支持度（minSupport)对 Ck 过滤，得到第k层剩下的数据集合（Lk）
def getLk(dataset, Ck, minSupport):
    global support_dic
    Lk = {}
    #计算Ck中每个元素在数据库中出现次数
    for item in dataset:
        for Ci in Ck:
            if Ci.issubset(item):
                if not Ci in Lk:
                    Lk[Ci] = 1
                else:
                    Lk[Ci] += 1

    #用最小支持度过滤
    Lk_return = []
    for Li in Lk:
        support_Li = Lk[Li] / float(len(dataSet))
        if support_Li >= minSupport:
            Lk_return.append(Li)
            support_dic[Li] = support_Li
    return Lk_return
 
#将经过支持度过滤后的第K层数据集合（Lk）融合
#得到第k+1层原始数据Ck1
'''连接步'''
def genLk1(Lk):
    
    Ck1 = []
    for i in range(len(Lk) - 1):
        for j in range(i + 1, len(Lk)):
            if sorted(list(Lk[i]))[0:-1] == sorted(list(Lk[j]))[0:-1]:
                Ck1.append(Lk[i] | Lk[j])                                        
    return Ck1
   
#遍历所有二阶及以上的频繁项集合
def genItem(freqSet, support_dic):
    for i in range(1, len(freqSet)):
        for freItem in freqSet[i]:
            genRule(freItem)
 
#输入一个频繁项，根据“置信度”生成规则
#采用了递归，对规则树进行剪枝
def genRule(Item, minConf=0.7):
    if len(Item) >= 2:
        for element in itertools.combinations(list(Item), 1):
            if support_dic[Item] / float(support_dic[Item - frozenset(element)]) >= minConf:
                print(str([Item - frozenset(element)]) + "----->" + str(element))
                print (support_dic[Item] / float(support_dic[Item - frozenset(element)]))
                genRule(Item - frozenset(element))
 
#输出结果
if __name__ == '__main__':
    dataSet = loadDataSet()
    print("data has been load")
    result_list = []
    Ck = createC1(dataSet)
    #循环生成频繁项集合，直至产生空集
    print (Ck)
    print("Ck1")
    while True:
        Lk = getLk(dataSet, Ck, 0.1)
        if not Lk:
            break
        result_list.append(Lk)
        Ck = genLk1(Lk)
        if not Ck:
            break
    print("over")
    #输出频繁项及其“支持度”
    print(support_dic)
    #输出规则
    genItem(result_list, support_dic)