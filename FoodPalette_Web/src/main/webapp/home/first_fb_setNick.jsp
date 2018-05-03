<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jsp_header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>처음 로그인한 페이스북 계정이 사용자이름 정하는 페이지</title>
<link type="text/css" rel="stylesheet" href="../design/design.css"/>
</head>
<body>

<form:form modelAttribute="userInfo" action="../account/fb_nick.html" method="post">

<input type="hidden" name="user_email" value="${userInfo.user_email}">
<input type="hidden" name="user_name" value="${userInfo.user_name}">
<div align="center">
<table border="1" width="40%">
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
		<font color="gray">FOOD PALETTE에서 사용할 닉네임을 입력해주세요.</font><br/>
		</td>
	</tr>
	<tr align="center">
		<td>
		<form:input path="user_nick" placeholder="사용자 이름"/>
		</td>
	</tr>
	<tr align="center">
		<td>
    	<input type="submit" value="확인">
		</td>
	</tr>
	<tr align="center">
		<td>
    	
    	<spring:hasBindErrors name="user">
		<font color="red">
		<c:forEach var="error" items="${errors.globalErrors}">
		<spring:message code="${error.code}"/>
		</c:forEach>
		</font>
		</spring:hasBindErrors>
    	
    	<font color="red"><br/>
		<form:errors path="user_nick"/><!-- 에러가 났을때 동작되는 태그 -->
		</font>
		
		</td>
	</tr>
	</table>
</td>
</tr>
</table>
</div>
</form:form>

</body>
</html>