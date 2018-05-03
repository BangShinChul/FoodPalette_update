<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jsp_header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입 완료</title>
<link type="text/css" rel="stylesheet" href="../design/design.css"/>
</head>
<body>

<div align="center">
<table border="1" width="40%" style="margin-top:240px;">
<tr align="center">
<td>
	<table class="loginwindow">
	<tr align="center">
		<td>
		<img src="../image/title.png"><br/>
		</td>
	</tr>
	<tr align="center">
		<td>
		<font color="gray">가입을 축하합니다!</font><br/>
		<font color="gray">다양한 레시피를 경험하고 ${NAME}님의 레시피도 공유해보세요!</font><br/>
		</td>
	</tr>
	<tr>
		<td>
		<a href="../login/login.html">
		<input type="button" value="확인">
		</a>
		</td>
	</tr>
	</table>
</td>
</tr>
</table>
</div>


</body>
</html>