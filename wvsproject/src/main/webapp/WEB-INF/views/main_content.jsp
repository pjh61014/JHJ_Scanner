<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style type="text/css">
#searchform {
	width: 900px;
	position: absolute;
	left: 50%;
	margin-left: -300px;
}

</style>
<title>main</title>
</head>
<body>
	<div id="searchform">
		<form name="searchUrl" method="post" action="/scanner/urlscan.do">
			URL 진단 <input type="text" name="url" size="50">
			<input type="submit" value="진단">
		</form>
	</div>
</body>
</html>