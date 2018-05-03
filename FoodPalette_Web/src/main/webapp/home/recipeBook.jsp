<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jsp_header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<!-- 내 스크랩북 -->
<table align="center" width="100%">
<tr style="border-top:1px solid lightgray;">
	<td align="left" colspan="3" style="padding-left:40px; padding-top:20px; padding-bottom:40px;">
		<font color="gray" style="font-weight:bold;">
			내가 스크랩한 레시피
		</font>
	</td>
</tr>
<c:choose>
<c:when test="${empty FIRST_IMGS[0]}">
<tr>
	<td align="center">
		<div style="margin-bottom:60px;">
			<font color="gray">내가 스크랩한 레시피가 없습니다.</font>
		</div>
	</td>
</tr>
</c:when>
<c:otherwise>
<c:forEach var="i" begin="0" end="${MAX-1}"><!-- 총 게시물갯수/3만큼 반복 -->
<tr border="0">
	<c:forEach var="num" begin="${TR_INDEX[i]*3-2}" end="${TR_INDEX[i]*3}"><!-- 첫번째게시물부터 3개씩 뿌려줌. 1줄*1번째,1*2,1*3 / 4,5,6 / 7,8,9 -->
	<c:if test="${!empty FIRST_IMGS[num-1]}">
	<td align="center" border="0">
		<a href="../list/recipe_selected.html?id=${LIST[num-1].recipe_id}">
				<!-- 게시물의 첫번째이미지 뿌려줌  -->
				<img src="${pageContext.request.contextPath}/upload/${FIRST_IMGS[num-1]}" width="400px" height="350px"/>
		</a>
	</td>
	</c:if>
	</c:forEach>
</tr>
</c:forEach>
</c:otherwise>
</c:choose>
</table>
</body>
</html>