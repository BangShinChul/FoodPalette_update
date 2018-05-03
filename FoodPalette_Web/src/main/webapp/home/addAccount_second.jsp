<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jsp_header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입 두번째 페이지</title>
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
	<form:form modelAttribute="userInfo" action="../account/input_second.html" method="post">
	<input type="hidden" name="user_email" value="${userInfo.user_email}">
	<input type="hidden" name="user_name" value="${userInfo.user_name}">
	<input type="hidden" name="user_nick" value="${userInfo.user_nick}">
	<input type="hidden" name="user_password" value="${userInfo.user_password}">
	<tr align="center">
		<td>
		<form:input path="user_question" placeholder="비밀번호 찾기 시 사용할 질문을 입력해주세요."/>
		</td>
	</tr>
	<tr align="center">
		<td>
		<form:input path="user_answer" placeholder="질문의 답을 입력해주세요."/>
		</td>
	</tr>
	<tr align="center">
		<td>
    	<input type="submit" value="완료">
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
		<form:errors path="user_question"/><!-- 에러가 났을때 동작되는 태그 -->
		</font>
		<font color="red"><br/>
		<form:errors path="user_answer"/><!-- 에러가 났을때 동작되는 태그 -->
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