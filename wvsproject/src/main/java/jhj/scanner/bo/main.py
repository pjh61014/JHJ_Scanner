# -*- coding: utf-8 -*-
'''
Created on 2016. 7. 20.

@author: jhj
'''


import urllib
from formMechanize import form_mechanize
from payload import req_url 
import sys



def url(url):
    # url = raw_input("url를 입력하세요: ")
    # real_url ="http://s50247-102131-hm3.tarentum.hack.me/index.php"
    #real_url = "http://192.168.0.32:5555/WebGoat/start.mvc"
    real_url = "http://demo.testfire.net/bank/login.aspx"
    #real_url="http://127.0.0.1:8088/mySecondPro/login.do"
    #real_url ="http://testasp.vulnweb.com/Login.asp?RetURL=%2FDefault%2Easp%3F"
    #real_url = "http://demo.testfire.net/bank/transaction.aspx"
    #real_url="http://www.daum.net/"
    print "입력된 url", url
    scan_header(url)
    #scan_header(real_url)
               
def scan_header(url):
    print url, " \nopen..."
    opener = urllib.urlopen(url)
    print "url 유효성 검사 중..."
    if opener.code == 200:
        print "url status: 200 ok"
        test2 =form_mechanize(url)
        print test2
        req_url(url,test2)
        #print opener.headers     
    elif opener.code == 404:
        
        print "page was not found check it"
        exit()
    
if __name__ == "__main__":
    url(sys.argv[1])
    
