# -*- coding: utf-8 -*-

'''
Created on 2016. 7. 26.

@author: JongHyuk
'''
'''
SQL Injection μ’λ₯?? error baseκ°? κ°??₯ ???? ?΄?Όκ³? λ§ν  ? ??€.
?¬?©?κ°? ?¬?©? ?λͺ»λ SQL κ΅¬λ¬Έ?Όλ‘? ?Ή??΄μ§??? ??¬λ₯? ?μΆ? ??€? λ°©λ²? 
error based SQL Injection?΄?Ό ??€.
?Όλ°μ ?Όλ‘? λΈλΌ?°? Έκ°? λ³΄μ¬μ£Όλ 500 errorλ₯? DB error?Όκ³? ??€.
'''

import urllib, urllib2, cookielib

from vulCheck import errorCheck


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
