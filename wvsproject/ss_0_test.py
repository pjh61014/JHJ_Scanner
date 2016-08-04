#-*- coding: utf-8 -*-

import HTMLParser
import mechanize
import urllib2
#import dtos
import getSession
from mechanize._form import TextControl
import attp
import datetime, time
from py4j.java_gateway import JavaGateway, GatewayParameters

#print att_patterns
# with open('./xss_payloads.txt', 'r') as f:
#    att_patterns.append(f.readlines())
#    f.close()

# att_patterns = dtos.singlilist(att_patterns)

def xss_mechanize(url, formList, fieldList):
	#br = mechanize.Browser()
	br, ch = getSession.getBr(url,'admin','password')
	br.open(url)
	br.set_handle_equiv(True)
	br.set_handle_redirect(True)
	br.set_handle_referer(True)
	br.set_handle_robots(False)


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
	print '%d fields have been found!' % len(fieldList)
	print "===================================\n"
	print 'control name : ', control.name, control.type

	return formList, fieldList


def get_info(att_patterns, vul_count, vul_title):
	print "===============info================\n"


	for pattern in att_patterns.values():
		textControl = br.form.controls[0]
		#submitControl = br.form.controls[1]
		
		#print '1', submitControl.name, submitControl.type
		textControl.value = pattern
		print datetime.datetime.now()
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
	#	print '2. HTTP header: ', res.read()

	 	print '3. HTTP response: ', urllib2.urlopen(res_add).getcode()
	#   print '******', HTMLParser.HTMLParser().unescape(res.read()), '********'
		#print '******', html_code, '********'
		br.form = list(br.forms())[0]
	 
	print vul_count, ", " , len(att_patterns)
	for i in vul_title:
		print i 

def main(url):
	#url = 'http://192.168.0.5/dvwa/vulnerabilities/xss_r/'
	vul_count = 0
	vul_title = []
	formList=[]   #saves form names
	fieldList=[] #saves field names
	att_count = 0
	flag = 0

	print url, "**scanning start**"

	att_patterns = attp.getPatterns()
	print len(att_patterns)


	#get result
	formList, fieldList = xss_mechanize(url, formList, fieldList)

	return formList


if __name__ == "__main__":
#   main(sys.argv[1])
	#main("http://192.168.0.5/dvwa/vulnerabilities/xss_r/")
	#'http://192.168.0.5/dvwa/vulnerabilities/xss_r/' #reflected
	#'http://192.168.0.5/dvwa/vulnerabilities/xss_s/' #stored

	gateway = JavaGateway()

	print "python dfsaf 코드 실행중......"
	print "python py4j gateway start......"
	java_url = gateway.entry_point.getStack() # 브라우져
	print "python py4j getUrl 호출......"

	main(java_url.getUrl())
