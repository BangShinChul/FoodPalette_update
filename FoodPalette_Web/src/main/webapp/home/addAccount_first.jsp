<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jsp_header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>
<link type="text/css" rel="stylesheet" href="../design/design.css"/>
<script src="../jsfile/facebook.login.js"></script>
</head>
<body>

<div align="center">
<table border="1" width="40%" style="margin-top:120px;">
<tr align="center">
<td>
	<table class="loginwindow">
	<tr align="center">
		<td>
		<img src="../image/title.png"><br/>
		<font color="gray">전 세계의 다양한 레시피를 경험하려면 가입하세요.</font><br/>
		<input id="fb_login" type="button" value="Facebook으로 로그인" onClick="javascript:loginFB()"/><br/>
		<img src="../image/dd.png">
		</td>
	</tr>
	<form:form modelAttribute="userInfo" action="../account/input_first.html" method="post">
	<tr align="center">
		<td>
		<form:input path="user_email" placeholder="이메일 주소"/>
		</td>
	</tr>
	<tr align="center">
		<td>
		<form:input path="user_name" placeholder="성명"/>
		</td>
	</tr>
	<tr align="center">
		<td>
		<form:input path="user_nick" placeholder="사용자 이름"/>
		</td>
	</tr>
	<tr align="center">
		<td>
		<form:password path="user_password" placeholder="비밀번호"/>
		</td>
	</tr>
	<tr align="center">
		<td>
    	<input type="submit" value="다음">
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
		<form:errors path="user_email"/><!-- 에러가 났을때 동작되는 태그 -->
		</font>
		<font color="red"><br/>
		<form:errors path="user_name"/><!-- 에러가 났을때 동작되는 태그 -->
		</font>
		<font color="red"><br/>
		<form:errors path="user_nick"/><!-- 에러가 났을때 동작되는 태그 -->
		</font>
		<font color="red"><br/>
		<form:errors path="user_password"/><!-- 에러가 났을때 동작되는 태그 -->
		</font>
		
		
    	<h5><font color="gray">가입하면 FOOD PALETTE의 약관 및 개인정보처리방침에 동의하게 됩니다.</font>
		</h5>
		</td>
	</tr>
	</form:form>
	</table>
</td>
</tr>
</table>

<!-- facebook login용 from -->
	<form:form id="fb_form" action="../login/fb_login.html" method="post" modelAtrribute="UserInfo">
		<input type="hidden" id="fb_name" name="user_name"/>
		<input type="hidden" id="fb_email" name="user_email"/>
	</form:form>

<br/>
<table border="1" width="40%">
	<tr>
		<td align="center" >
		계정이 있으신가요? <a href="../login/login.html">로그인</a>
		</td>
	</tr>
</table>
</div>
</body>
</html>