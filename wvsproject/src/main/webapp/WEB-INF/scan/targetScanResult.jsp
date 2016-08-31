<%@page import="jhj.scanner.dto.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		scanInfoDTO result = (scanInfoDTO) request.getAttribute("targetResult");
		formInfoDTO forminfo = result.getForminfo();
		List<vulInfoDTO> vulinfo = result.getVulinfo();
		int vulsize = vulinfo.size();
	%>
	<div class="container">
		<h2>
			<strong><u>URL 취약점 진단 결과</u></strong>
		</h2>
		<h3>
			진단 도메인 명 :
			<%=result.getUrl()%></h3>
		<h3>
			진단 일시 :
			<%=result.getDate()%></h3>
		<h3>
			취약점 탐지 개수 :
			<%=vulsize%></h3>
		<hr color="black">
		<br />
		<table class="table table-hover">
		<tr>
			<th>Index</th>
			<th>취약점명</th>
			<th>취약 패턴</th>
			<th>취약점 패턴 설명</th>
		</tr>
		<%for(int i=0; i<vulsize; i++){
			vulInfoDTO info = vulinfo.get(i);
			
			%>
				
		
		<tr>
			<td></td>
			<td><%=result.getVul_type() %></td>
			<td><%=info.getPatterm_id() %></td>
			<td><%=info.getPattern_dspt() %></td>
		</tr>
		<%} %>
		
		</table>
	</div>

</body>
</html>