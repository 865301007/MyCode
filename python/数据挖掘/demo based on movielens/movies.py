import pandas as pd 
import os
encoding='latin1'
upath=os.path.expanduser('movielens/users.dat')
rpath=os.path.expanduser('movielens/ratings.dat')
mpath=os.path.expanduser('movielens/movies.dat')

unames=['user_id','gender','age','occupation','zip']
rnames=['user_id','movie_id','rating','timestamp']
mnames=['movie_id','title','genres']

# 从csv文件中读取
users = pd.read_csv(upath, sep='::', header=None,names=unames, encoding=encoding,engine='python')

ratings = pd.read_csv(rpath, sep='::', header=None,  names=rnames, encoding=encoding,engine='python')
movies = pd.read_csv(mpath, sep='::', header=None, names=mnames, encoding=encoding,engine='python')

users[:5]

ratings[:5]

movies[:5]

# 连接几个表
data = pd.merge(pd.merge(ratings, users), movies)
mean_ratings = data.pivot_table('rating', index='title',
columns='gender', aggfunc='mean')
ratings_by_title = data.groupby('title').size()

ratings_by_title[:5]
active_titles = ratings_by_title.index[ratings_by_title >= 250]
active_titles[:10]
mean_ratings = mean_ratings.ix[active_titles]
mean_ratings


data = pd.merge(pd.merge(ratings, users), movies)
data

# index的重命名
mean_ratings = mean_ratings.rename(index={'Seven Samurai(The Magnificent Seven) (Shichinin no samurai) (1954)': 'Seven Samurai(Shichinin no samurai) (1954)'})
top_female_ratings = mean_ratings.sort_values(by='F', ascending=False)
top_female_ratings[:10]

# 计算两列的差值
mean_ratings['diff'] = mean_ratings['M'] - mean_ratings['F']

sorted_by_diff = mean_ratings. sort_values(by='diff')
sorted_by_diff[:15]
sorted_by_diff[::-1][:15]
rating_std_by_title = data.groupby('title')['rating'].std()

rating_std_by_title = rating_std_by_title.ix[active_titles]

rating_std_by_title.sort_values(ascending=False)[:10]