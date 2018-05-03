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
    <title>회원가입3_완료</title>
    <link type="text/css" rel="stylesheet" href="../design/m_design.css"/>
</head>
<body>

	<div data-role="page" id="page-1">
		<div data-role="header">
			<h1>Page Title</h1>
		</div>
		<div data-role="content" align="center" width="100%" style="background-color:white; padding:0; margin:0;">
			<table width="100%" style="border:1px solid lightgray;">
			<tr align="center">
				<td>
				<img src="../image/title.png"><br/>
				</td>
			</tr>
			<tr align="center">
				<td>
				<font color="gray">가입을 축하합니다!</font><br/>
				<font color="gray">다양한 레시피를 경험하고<br/>${NAME}님의 레시피도 공유해보세요!</font><br/>
				</td>
			</tr>
			<tr>
				<td>
				<a href="../mobile_Login/m_login.html">
					<input type="button" value="확인">
				</a>
				</td>
			</tr>
			</table>
		</div>
		<div data-role="footer">
			<h4>Page Footer</h4>
		</div>
	</div>
</body>
</html>