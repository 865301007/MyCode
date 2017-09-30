import numpy as np
import math
import matplotlib.pyplot as plt

class LogisticRegres(object):

    def __init__(self):
        pass

    def sigmoid(self,inX):
        return 1.0 / (1+np.exp(-inX))
    
    def gradDescent(self,x_train,y_train):
        m,n = x_train.shape
        alpha = 0.001
        maxEpoch = 5000
        weights = np.ones((n,1))
	for i in range(maxEpoch):
		value = self.sigmoid(x_train*weights)
		error =value-y_train
		grad=np.dot(x_train.transpose(),error)/m
		weights=weights -alpha*grad
          
        '''
        TODO: You should use grad descent method to complete the code.
        '''
        return weights

    def gradAscent(self,x_train,y_train):
        # y_train=-y_train
        #print y_train
        m,n=y_train.shape
        """
        for i in range(m):
            if y_train[i]==0:
                y_train[i]=1
            else:
                y_train[i]=0
        #print y_train
        """

        m,n = x_train.shape
        alpha = 0.001
        maxEpoch = 500 
        weights = np.ones((n,1))
        for i in range(maxEpoch):
            value=self.sigmoid(x_train*weights)
            error=y_train-value
            grad=x_train.transpose()*error
            #print y_train.shape
           # print error.shape
            weights=weights+alpha*grad
           
            '''
            TODO: You should use grad ascent method to complete the code.
            '''
        return weights

    def stocGradDescent(self,x_train,y_train,numEpochs):
        m,n = x_train.shape
        weights = np.ones((n,1))
        for j in range (numEpochs):
            index = range(m)
            for i in range(m):
		alpha=4/(1.0+j+i)+0.0001 #apha decreases with iteration,does not
		randIndex=int(np.random.uniform(0,len(index)))
		value = self.sigmoid(sum(x_train*weights))
		error = y_train[randIndex]-value
		#print alpha.shape
		
		weights=weights+alpha*error[0,0]*x_train[randIndex].transpose()
		del(index[randIndex])
                '''
                TODO: You should use stocGradDescent method to complete the code.
                '''
        return weights

    def predict(self,weights,x_test,y_test):
        m,n = np.shape(x_test)
        rightCount = 0
        for i in range(m):
            if self.sigmoid(x_test[i,:]*weights) > 0.5:
                predict = 1
            else:
                predict = 0 
            if predict == y_test[0,i]:
                rightCount += 1
        accuracy = float(rightCount)/ m
        return accuracy
        
    def plotBestFit(self,weights,x_train,y_train):
        m,n = np.shape(x_train)
        for i in range(m):
            if int(y_train[0,i]) == 0:
                plt.plot(x_train[i,1],x_train[i,2],'or')
            else:
                plt.plot(x_train[i,1],x_train[i,2],'ob')
        min_x = min(x_train[:, 1])[0, 0]  
        max_x = max(x_train[:, 1])[0, 0]  
        weights = weights.getA()  # convert mat to array  
        y_min_x = float(-weights[0] - weights[1] * min_x) / weights[2]  
        y_max_x = float(-weights[0] - weights[1] * max_x) / weights[2]  
        plt.plot([min_x, max_x], [y_min_x, y_max_x], '-g')  
        plt.xlabel('X1'); plt.ylabel('X2')  
        plt.show()  
   