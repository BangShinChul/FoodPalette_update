<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	div {margin-bottom:1000px;}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<link type="text/css" rel="stylesheet" href="design/image_test.css" /> 
</head>
<body>
<script type="text/javascript">
function fnMove(seq){
	var offset = $("#div" + seq).offset();
    $('html, body').animate({scrollTop : offset.top}, 5);
}
</script>
<button onclick="fnMove('1')">div1로 이동</button>
    <button onclick="fnMove('2')">div2로 이동</button>
    <button onclick="fnMove('3')">div3로 이동</button>
    <div id="div1">div1</div>
    <div id="div2">div2</div>
    <div id="div3">div3</div>
</body>
</html>