#-*- coding: utf-8 -*-

#from BeautifulSoup import BeautifulSoup
import HTMLParser
import mechanize
import urllib2
#import dtos
import getSession
from mechanize._form import TextControl
import attp
import re


att_patterns = attp.getPatterns()

#print att_patterns
# with open('./xss_payloads.txt', 'r') as f:
#    att_patterns.append(f.readlines())
#    f.close()

# att_patterns = dtos.singlilist(att_patterns)

print len(att_patterns)

#url = 'http://s50586-101060-omj.sipontum.hack.me/search.php'
#url='http://s50247-101021-q2q.sipontum.hack.me/xss2.php'
#url = 'http://192.168.1.11/dvwa/vulnerabilities/xss_r/'
#url = 'http://s50247-101060-qbm.sipontum.hack.me/search.php'
#url = 'http://192.168.0.5/dvwa/vulnerabilities/xss_s/' #reflected
#url='http://192.168.0.5/dvwa/vulnerabilities/xss_s/' #stored
#br, ch = getSession.getBr('http://192.168.0.5/dvwa','admin','password')
url = 'https://hack.me/101020/xss-1.html'
br = mechanize.Browser()
br.open(url)
br.set_handle_equiv(True)
br.set_handle_redirect(True)
br.set_handle_referer(True)
br.set_handle_robots(False)
formList=[]   #saves form names
fieldList=[] #saves field names
str_forms=""

att_count = 0
flag = 0

#print att_patterns


br.form = list(br.forms())[0]   #forms..(1 includes login)
print "=============form list============="
for form in br.forms():
   formList.append(form.name)

for i in formList:
   print i 
print '%d forms have been found!' % len(formList) 
print "===================================\n"

print "============field list============="
for control in br.form.controls:
   fieldList.append(control.name)

for i in fieldList:
   print i

print "===================================\n"

print '%d fields have been found!' % len(fieldList)
print "===============info================\n"
vul_count = 0
vul_title = []

print 'control name : ', control.name, control.type

for pattern in att_patterns.values():
	textControl = br.form.controls[0]
	#submitControl = br.form.controls[1]
	
	#print '1', submitControl.name, submitControl.type
	textControl.value = pattern
	print '1. attack query: ', textControl.value, '   type: ',textControl.type
	
	res = br.submit()
	res_add = res.geturl()

	html_code = str(res.read())
	if pattern in html_code:
		print 'true'
		vul_count += 1
		vul_title.append(att_patterns.get(pattern))

	else:
		print 'false'

	#test = re.compile(str(res.read()))
	#result = re.findall(patterns, str(html_code))
	#print result

	#print bad
	#print '2. HTTP header: ', res.info()
	#print '2. HTTP code: ', res.read()

 	print '3. HTTP response: ', urllib2.urlopen(res_add).getcode()
#   print '******', HTMLParser.HTMLParser().unescape(res.read()), '********'
	#print '******', html_code, '********'
	br.form = list(br.forms())[0]
 
print vul_count, ", " , len(att_patterns)
for i in vul_title:
	print i 
