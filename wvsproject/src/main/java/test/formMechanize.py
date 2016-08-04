# -*- coding: utf-8 -*-

'''
Created on 2016. 7. 26.

@author: JongHyuk
'''
import mechanize


def form_mechanize(real_url):
    '''
    mechanize는 urllib, urllib2 혹은 requests 모듈과 같이 python에서 웹 요청을
    보낼 수 있는 모듈이다. 다른 모듈다 다른 점으 가상으로 브라우져를 생성하여 웹 요청을 보내므로
    cookie 혹은 session을 쉽게 다룰 수 있고, select_form, submit과 같은 함수
    들을 이용해 로그인을 할 수 있다. 
    '''
    cj = mechanize.CookieJar()
    br = mechanize.Browser()# create Browser
    br.set_cookiejar(cj)
    br.set_handle_robots(False)
    br.set_handle_equiv(True)
    br.set_handle_gzip(True)
    br.set_handle_redirect(True)
    br.set_handle_referer(True)
    br.set_handle_robots(False)
        
    br.open(real_url)
    
    formname =[]
    userTagId =[]
    formSubmit=[]
    for form in br.forms():
        #print form
        #print "form name list up"
        formname.append(form.name)  
    
    print "-------------[*] formname list up-----------"     
    print formname
    
    for names in formname :
        if names != "None":
            test = names
    formSubmit.append(test)
    br.select_form(name=test) 
    for control in br.form.controls:
        #print control.type,control.name,br[control.name]
        if control.name != "None":    
            if control.type != "submit" :
                userTagId.append(control.name)
            else:
                formSubmit.append(control.name)
                     
    print "-------------[*] form input id list up-----------"
    print userTagId   
       
    return userTagId 
 