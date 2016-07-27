# -*- coding: utf-8 -*-

'''
Created on 2016. 7. 26.

@author: JongHyuk
'''
from vulCheck import errorCheck
import urllib, urllib2,cookielib
'''
SQL Injection 종류에서 error base가 가장 대표적이라고 말할 수 있다.
사용자가 사용한 잘못된 SQL 구문으로 웹페이지에서 에러를 도출 시키는 방법을 
error based SQL Injection이라 한다.
일반적으로 브라우져가 보여주는 500 error를 DB error라고 한다.
'''

def sqli_attack(real_url,result):
    
    cj = cookielib.CookieJar()
    opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
    urllib2.install_opener(opener)
    
    for re in result:
        print re
        data =urllib.urlencode(re)
           
        try:
            request = urllib2.Request(real_url, data)
            res = opener.open(request)
            print "****************************"
           
            print re,"-----------------------"
            print res.read()
            print "----------------------"
            res.close()
        except urllib2.HTTPError, error:
            #content = error.read()
            if error.getcode()==500:
                #print "*****----------------*************"
                #print "fasdfadf\n",error.getcode
                dberror = error.read()
                errorCheck(re, dberror)
                   
                   
            #print "fasdfasdf\n",content
            
    
    
    # req = urllib2.Request(real_url,data)
