# -*- coding: utf-8 -*-

'''
Created on 2016. 7. 26.

@author: JongHyuk
'''
'''
SQL Injection ì¢…ë¥˜?—?„œ error baseê°? ê°??¥ ???‘œ? ?´?¼ê³? ë§í•  ?ˆ˜ ?ˆ?‹¤.
?‚¬?š©?ê°? ?‚¬?š©?•œ ?˜ëª»ëœ SQL êµ¬ë¬¸?œ¼ë¡? ?›¹?˜?´ì§??—?„œ ?—?Ÿ¬ë¥? ?„ì¶? ?‹œ?‚¤?Š” ë°©ë²•?„ 
error based SQL Injection?´?¼ ?•œ?‹¤.
?¼ë°˜ì ?œ¼ë¡? ë¸Œë¼?š°? ¸ê°? ë³´ì—¬ì£¼ëŠ” 500 errorë¥? DB error?¼ê³? ?•œ?‹¤.
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
