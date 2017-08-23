# coding=UTF-8
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
chromedriver = "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe"

options = webdriver.ChromeOptions()
prefs = {'profile.default_content_settings.popups': 0, 'download.default_directory': 'd:\\'}
options.add_experimental_option('prefs', prefs)

browser = webdriver.Chrome(chromedriver,chrome_options=options)

#browser.get('http://mp33.yscn8.com/file/1509/9692/vsid-2f44f3a48b3d9214ec606ab4a92c3efb/102.mp3')
browser.get("http://www.baidu.com")
assert u"百度" in browser.title
elem=browser.find_element_by_name("wd")
elem.clear()
elem.send_keys(u"网络爬虫")
elem.send_keys(Keys.RETURN)
time.sleep(3)
browser.close()