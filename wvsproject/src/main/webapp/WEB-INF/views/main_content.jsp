<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">


#url {
	color: #606270;
}
#searchform {
	width: 900px;
	position: absolute;
	left: 50%;
	margin-left: -300px;
}



#btn_url_submit {
	display: inline-block;
/* 	background-color: #98D9DB ; */
	border-radius: 8px;
	color: #606270;
	text-align: center;
	font-size: 20px;
	padding: 10px;
	width: 50%;
	transition: all 0.5s;
	margin: 5px;
}

#btn-search {
	display: inline-block;
	background-color: #00AFAD;
	border-radius: 8px;
	color: #FFFFFF;
	text-align: center;
	font-size: 14px;
	width: 100px;
	margin: 5px;
}

#btn-search {
    -webkit-transition-duration: 0.4s; 
    transition-duration: 0.5s;
}

#btn-search:hover {
    background-color: #98D9DB; 
    color: white;
}

</style>


<title>main</title>
</head>
<body>
	<div id="searchform">
		<form name="searchUrl" method="post" action="/scanner/urlscan.do">
			<!--	 URL 진단 <input type="text" name="url" size="50">
			<input type="submit" value="진단">
 -->
			<div class="search">
				<label for="url">취약점 진단할 URL을 입력해주세요.</label><br> 
				<input type="text" class="form-control" id="btn_url_submit">
				<button type="submit" class="btn" id ="btn-search">진단하기</button>
			</div>

		</form>
	</div>
</body>
</html>