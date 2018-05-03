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
    <meta name="format-detection" content="telephone=no">
    <meta name="msapplication-tap-highlight" content="no">
    <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width">
    <title>login</title>
    <script src="../jsfile/facebook.login.js"></script>
	<link type="text/css" rel="stylesheet" href="../design/m_design.css"/>
	
	<style type="text/css">
	::-webkit-input-placeholder { /* WebKit browsers */
    color:    #aaa; font-size:10px;
	}
	::-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	    color:    #aaa; font-size:10px;
	}
	::-moz-placeholder { /* Mozilla Firefox 19+ */
	    color:    #aaa; font-size:10px;
	}
	::-ms-input-placeholder { /* Internet Explorer 10+ */
	    color:    #aaa; font-size:10px;
	}
	</style>
	
</head>
<body>

	<div data-role="page" id="page-login" style=" margin:0; padding:0;">
		<div data-role="header">
		</div>
		
		<div data-role="content" align="center" style="background-color:white; margin:0; padding:0;">
			<form:form modelAttribute="userInfo" action="../mobile_Login/do_login.html" method="post">
			<table width="100%" style="border:1px solid lightgray;" rel="external">
			<tr align="center">
			<td>
				<table>
				<tr align="center">
					<td>
					<img src="../image/title.png">
					</td>
				</tr>
				<tr align="center">
					<td>
					<input name="user_email" id="text-3" type="text" value="" data-clear-btn="true" placeholder="이메일 주소">
<%-- 					<form:input path="user_email" placeholder="이메일 주소"/> --%>
					</td>
				</tr>
				<tr align="center">
					<td>
					<input name="user_password" id="text-3" type="password" value="" data-clear-btn="true" placeholder="비밀번호">
<%-- 					<form:password path="user_password" placeholder="비밀번호"/><br/> --%>
					<a href="../mobile_Account/findPassword.html">비밀번호를 잊으셨나요?</a>	
					</td>
				</tr>
				<tr align="center">
					<td>
					<input type="submit" value="로그인"/><br/>
					</td>
				</tr>
				<tr align="center">
					<td>
					<img src="../image/dd.png">
					</td>
				</tr>
				<tr align="center">
					<td>
					<input id="fb_login" type="button" value="Facebook으로 로그인" onClick="javascript:loginFB()" /><br/>
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
					<font color="red"><br/>
					<form:errors path="user_password"/><!-- 에러가 났을때 동작되는 태그 -->
					</font>
					
					</td>
				</tr>
			</table>
			</td>
			</tr>
			</table>
			</form:form>
			
			<!-- facebook login용 from -->
			<form:form id="fb_form" action="../mobile_Login/fb_login.html" method="post" modelAtrribute="UserInfo">
				<input type="hidden" id="fb_name" name="user_name"/>
				<input type="hidden" id="fb_email" name="user_email"/>
			</form:form>			
		
			<table width="100%" style="margin-top:10px; border:1px solid lightgray;">
				<tr>
					<td align="center" style="padding:10px;">
						계정이 없으신가요? <a href="../mobile_Account/input.html">가입하기</a>
					</td>
				</tr>
			</table>
		</div>
		
		<div data-role="footer">
		</div>
	</div>
</body>
</html>