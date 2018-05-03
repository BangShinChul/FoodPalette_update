<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jsp_header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>비밀번호찾기-첫번째</title>
<link type="text/css" rel="stylesheet" href="../design/design.css"/>
</head>
<body>
<div align="center">
<form:form modelAttribute="userInfo" action="../account/findPassword_first.html" method="post">


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
		<font color="gray">비밀번호를 찾으실 계정의 이메일을 입력해주세요.</font><br/>
		</td>
	</tr>
	<tr align="center">
		<td>
		<form:input path="user_email" placeholder="이메일 주소"/>
		</td>
	</tr>
	<tr align="center">
		<td>
    	<input type="submit" value="확인">
		</td>
	</tr>
	<tr align="center">
		<td>
    	
    	<spring:hasBindErrors name="userInfo">
		<font color="red">
		<c:forEach var="error" items="${errors.globalErrors}">
		<spring:message code="${error.code}"/>
		</c:forEach>
		</font>
		</spring:hasBindErrors>
    	
    	<font color="red"><br/>
		<form:errors path="user_email"/><!-- 에러가 났을때 동작되는 태그 -->
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