# coding=UTF-8

import urllib
import re
import requests




from lxml import etree
import requests



def saveImg1(imgurl,imgpath):
    user_agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36"
    referer = imgurl
    headers = {"User-Agent": user_agent, 'Referer': referer}
    response = requests.get(imgurl,headers)
    with open(r'2.mp3', 'wb') as f:
        f.write(response.content)
        f.flush()
        quit(1)



def Schedule(blocknum,blocksize,totalsize):
    '''''
    blocknum:已经下载的数据块
    blocksize:数据块的大小
    totalsize:远程文件的大小
    '''
    per = 100.0 * blocknum * blocksize / totalsize
    if per > 100 :
        per = 100
    print '当前下载进度：%d'%per


def get_mp3_url(url):
    user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
    headers = {'User-Agent': user_agent}
    r = requests.get(url, headers=headers)
    html = etree.HTML(r.text)
    img_urls = html.xpath('.//script/text()')

    pattern = re.compile(r'exurl=.*http.*mp3.*')

    for img_url in img_urls:
        if (re.match(pattern, img_url)):
            img_url=img_url[29:]
            img_url="http://mp33.yscn8.com"+img_url
            print (img_url)
            saveImg1(img_url,img_url[img_url.rfind("/")+1:])
            #opener = urllib.request.build_opener()
            #opener.addheaders = [('User-Agent',
            #                     'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1941.0 Safari/537.36')]
            #urllib.request.install_opener(opener)
            #urllib.urlretrieve(img_url,r'1.mp3')
            #quit(1)


def get_url(url):
    user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
    headers = {'User-Agent': user_agent}
    r = requests.get(url, headers=headers)

    html = etree.HTML(r.text)

    detail_urls = html.xpath('.//a/@href')
    detail_urls = sorted(set(detail_urls), key=detail_urls.index)
    pattern = re.compile(r'http://www.yscn8.com/listen.*\.html')

    for detail_url in detail_urls:
        if (re.match(pattern, detail_url)):
            get_mp3_url(detail_url)

get_url('http://www.yscn8.com/source-9692.html')
for i in range(2,32):
    url='http://www.yscn8.com/source-9692-'+str(i)+'.html'
    get_url(url)
