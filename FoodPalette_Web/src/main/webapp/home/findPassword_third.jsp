<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jsp_header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>비밀번호찾기 마지막-비밀번호 변경</title>
<link type="text/css" rel="stylesheet" href="../design/design.css"/>
</head>
<body>

<div align="center">
<form:form modelAttribute="changePwd" action="../account/findPassword_third.html" method="post">
<input type="hidden" name="user_email" value="${changePwd.user_email}">


<table border="1" width="40%" style="margin-top:120px;">
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
		<font color="gray">${changePwd.user_email}으로 가입된</font><br/>
		<font color="gray">계정의 비밀번호를 새로 입력해주세요.</font><br/>
		</td>
	</tr>
	<tr align="center">
		<td>
		<form:password path="user_password" placeholder="새로운 비밀번호"/>
		</td>
	</tr>
	<tr align="center">
		<td>
		<form:password path="user_password_check" placeholder="비밀번호 확인"/>
		</td>
	</tr>
	<tr align="center">
		<td>
    	<input type="submit" value="확인">
		</td>
	</tr>
	<tr align="center">
		<td>
    	
    	<spring:hasBindErrors name="changePwd">
		<font color="red">
		<c:forEach var="error" items="${errors.globalErrors}">
		<spring:message code="${error.code}"/>
		</c:forEach>
		</font>
		</spring:hasBindErrors>
    	
    	<font color="red"><br/>
		<form:errors path="user_password"/><!-- 에러가 났을때 동작되는 태그 -->
		</font>
		
		<font color="red"><br/>
		<form:errors path="user_password_check"/><!-- 에러가 났을때 동작되는 태그 -->
		</font>
		
		</td>
	</tr>
	</table>
</td>
</tr>
</table>

</form:form>

<br/>
<table border="1" width="40%">
	<tr>
		<td align="center" >
		계정이 기억나셨나요? <a href="../login/login.html">로그인</a>
		</td>
	</tr>
</table>
</div>
</body>
</html>