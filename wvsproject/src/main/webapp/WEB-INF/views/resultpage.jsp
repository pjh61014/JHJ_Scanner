<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JHJ PROJECT::::진단 결과</title>
<link href="res/common/css/resultpage_style.css" rel="stylesheet">
</head>
<body>
<div>
</div>
<div id = "result_body">
<table class="vul_result_table1">
  <tr>
    <td class = "td_title">진단도메인</td>
    <td>http://192.168.0.5/dvwa/</td>
  </tr>
  <tr>
    <td class = "td_title">진단일시</td>
    <td>2016-08-02 18:30:49</td>
  </tr>
  <tr>
    <td class = "td_title">검색된 form 개수</td>
    <td><span>%d</span> 개</td>
  </tr>
 <tr>
  <td class = "td_title">검색된 tag 개수</td>
    <td><span>%d</span> 개</td>
  </tr>
</table>
<hr>
<div class="vul_result_table2">
    <div class="table-head">
        <div class="column" data-label="vul_name_header">취약점 명</div>
        <div class="column" data-label="vul_patrn_header">취약점 패턴</div>
        <div class="column" data-label="vul_desc_header">패턴 설명</div>
    </div>
       <div class="row2">
        <div class="column" data-label="vul_name_header">SQL injection</div>
        <div class="column" data-label="vul_patrn_header">Syntax Error</div>
        <div class="column" data-label="vul_desc_header">Vulnerability description</div>
    </div>

</div>
</div>

</body>
</html>