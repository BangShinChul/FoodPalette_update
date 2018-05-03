<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="../home/jsp_header.jsp" %> 
<!DOCTYPE html>
<html>
<head>    
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
   	<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
   	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <meta http-equiv="Content-Security-Policy" content="default-src 'self' data: gap: https://ssl.gstatic.com 'unsafe-eval'; style-src 'self' 'unsafe-inline'; media-src *">
    <meta name="format-detection" content="telephone=no">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width">
    <title>FB계정으로 첫 로그인 시_닉네임 설정</title>
    <link type="text/css" rel="stylesheet" href="../design/m_design.css"/>
</head>
<body>
	<div data-role="page" id="page-1">
		<div data-role="header">
			<h1>Page Title</h1>
		</div>
		
		<div data-role="content" align="center" style="background-color:white;">
			
			<form:form modelAttribute="userInfo" action="../mobile_Account/fb_nick.html" method="post">

			<input type="hidden" name="user_email" value="${userInfo.user_email}">
			<input type="hidden" name="user_name" value="${userInfo.user_name}">
				<table width="100%" style="border:1px solid lightgray;">
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
			</form:form>
			
		</div>
		
		<div data-role="footer">
			<h4>Page Footer</h4>
		</div>
	</div>
</body>
</html>