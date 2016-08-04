import mechanize


def getBr(url, id,pwd): 
	cj = mechanize.CookieJar()
	br = mechanize.Browser()
	br.set_cookiejar(cj)
	br.open(url)
	br.select_form(nr=0)
	br.form['username']=id
	br.form['password']=pwd
	cookie1 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5',False,False,'/',True,False,None,True,None,None,{},False)
	cookie2 = mechanize.Cookie(0, 'PHPSESSID', 'k1c5fdp3vcbni48oh3tiug0ee2', None,False,'192.168.0.5',False,False,'/',True,False,None,True,None,None,{},False)
	cookie3 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5/dvwa',False,False,'',True,False,None,True,None,None,{},False)
	cookie4 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5/dvwa/vulnerabilities',False,False,'/',True,False,None,True,None,None,{},False)
	cookie5 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5/dvwa/vulnerabilities/xss_r',False,False,'',True,False,None,True,None,None,{},False)
	cookie6 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.5/dvwa',False,False,'/',True,False,None,True,None,None,{},False)

	cj.clear_session_cookies()
	cj.set_cookie(cookie1)
	cj.set_cookie(cookie2)
	cj.set_cookie(cookie3)
	cj.set_cookie(cookie4)
	cj.set_cookie(cookie5)

	cj.set_cookie(cookie6)
	


	br.submit()
	print 'did.'
	print cj


	#cookie = cookielib.Cookie(version=0, name='PON', value="xxx.xxx.xxx.111", expires=365, port=None, port_specified=False, domain='xxxx', domain_specified=True, domain_initial_dot=False, path='/', path_specified=True, secure=True, discard=False, comment=None, comment_url=None, rest={'HttpOnly': False}, rfc2109=False)
	# cookie3 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.27/dvwa',False,False,'',True,False,None,True,None,None,{},False)
	# cookie4 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.27/dvwa/vulnerabilities',False,False,'/',True,False,None,True,None,None,{},False)
	# cookie5 = mechanize.Cookie(0, 'security','low', None, False,'192.168.0.27/dvwa/vulnerabilities/xss_r',False,False,'',True,False,None,True,None,None,{},False)

	# cj.set_cookie(cookie3)
	# cj.set_cookie(cookie4)
	# cj.set_cookie(cookie5)

#	test = mechanize.build_opener(mechanize.HTTPCookieProcessor(cj))
	#print 'sssssssssssssssss', cj
#	print dir(mechanize.CookieJar())


	return [br, cj]




# url='http://192.168.0.5/dvwa/vulnerabilities/xss_s/'
# br = mechanize.Browser()
# br, cj = getBr(url,'admin','password')
# br.set_cookiejar(cj)
# a = br.open(url)
# print br.title()

