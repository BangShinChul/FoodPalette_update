<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jsp_header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../design/design.css"/>
<!-- 검색창 css를위해 필요한 link -->
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script src="../jsfile/facebook.login.js"></script>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	
});
function goSearch(){
	var href = "../list/recipe_search.html?FOCUS=null&FLAG=icon&SEARCH=";
	var search = document.getElementById("search");
}

</script>

<table width="100%" border="1">
<tr align="center">
<td align="left" style="padding-left:30px; border-right:0px solid white;">
<a href="../login/home.html">
<img src="../image/title.png" id="icon">
</a>
</td>
<td style="border-left:0px solid white; border-right:0px solid white;">
<form action="../list/recipe_search.html" method="get" name="searchform">
	<input type="hidden" name="FOCUS" value="${FOCUS}"/>
	<input type="hidden" name="FLAG" value="icon"/>
	<div class="box" id="search_div" style="padding-top : 0px;">
	  <div class="container-1" id="search_div">
	      <span class="icon">
	      	<a href="javascript:searchform.submit();">
	      		<i class="fa fa-search" style="display: none;"></i>
	      	</a>
	      </span>
	      <input type="search" id="search" name="search" placeholder="검색"/>
	  </div>
	</div>
</form>
</td>

<td align="right" style="border-left:0px solid white; border-right:0px solid white;">
	<a href="../recipebook/my_clip_list.html">
		<img src="../image/book_icon.png" width="25px" height="25px">
	</a>
</td>

<td style="border-left:0px solid white; border-right:0px solid white;">
	<a href="../post/upload_start.html">
		<img src="../image/upload_icon.png" width="25px" height="25px">
	</a>
</td>
<td align="left" style="padding-right:20px; border-left:0px solid white;">
<a href="../mypage/mypage.html">
<img src="../image/mypage_icon.png" width="25px" height="25px">
</a>
</td>
</tr>
</table>
<table width="100%" border="1">
<tr>
<td width="650" valign="top" colspan="5">
	<c:choose>
		<c:when test="${BODY!=null}">
			<jsp:include page="${BODY}"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="front.jsp"/>
		</c:otherwise>
	</c:choose>
	</td>
</tr>
</table>
</body>
</html>