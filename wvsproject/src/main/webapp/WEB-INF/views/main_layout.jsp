<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JHJ PROJECT</title>
</head>

<style type = "text/css" >
	#header{
		height: 20%;

		
	}
	#content {
		height : 80%;
		/* float : right; */
	}

</style>

<body>
	<div id = "header">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
	<div id = "content">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
	</div>
</body>
</html>