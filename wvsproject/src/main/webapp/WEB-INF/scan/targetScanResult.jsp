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
			<strong><u>URL ����� ���� ���</u></strong>
		</h2>
		<h3>
			���� ������ �� :
			<%=result.getUrl()%></h3>
		<h3>
			���� �Ͻ� :
			<%=result.getDate()%></h3>
		<h3>
			����� Ž�� ���� :
			<%=vulsize%></h3>
		<hr color="black">
		<br />
		<table class="table table-hover">
		<tr>
			<th>Index</th>
			<th>�������</th>
			<th>��� ����</th>
			<th>����� ���� ����</th>
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