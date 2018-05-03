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
<meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width">
<title>마이페이지</title>
<style type="text/css">
	.profile {
	    object-fit: cover;
	    object-position: bottom;
	    border-radius: 50%;
	    border : 1px solid lightgray;
	}
	.div {display:table;}
	.p {display:table-cell; text-align:center; vertical-align:middle;}
</style>

</head>
<body>

<div data-role="page" id="page1" style="background-color:white;">
	<div data-role="header"  data-position="fixed" style="background-color:white;">
		<table width="100%" align="center" style="border:0px solid lightgray;">
		<tr>
			<td width="33%">
			</td>
			<td width="33%" align="center">
				<img src="../image/m_title2.png" style="padding:5px;"/>
			</td>
			<td width="33%" align="right">
				<img align="right" src="../image/search_icon.png" class="opensearch" width="15px" height="15px" style="padding:5px; padding-right:10px; "/>	
			</td>
		</tr>
		</table>
		<div class="search_field" style="display:none; background-color:white;">
			<input name="search" id="search" value="" placeholder="검색" type="search" data-theme="b" style="font-size:11px;"/>
		</div>
		
		<script type="text/javascript">
		$(document).ready(function(){
			$( ".opensearch" ).on( 'click', tapHandler );
			
			function tapHandler( event ) {
				$('.search_field').slideToggle();
				setTimeout(function(){
					$('#search').focus().tap();
				},0);
			}
		});
		</script>
	</div>

	<div data-role="content" style="margin:0; padding:0;">
		<script type="text/javascript">
		var file;
		function checkProfile(){
			file = document.getElementById("profile_upload");
			console.log("file : "+file.value);
			if(file.value!=null){
				uploadProfile();	
			}
		}
		function uploadProfile(){
			console.log("업로드한 프로필이미지명 : "+file.value);
			var profile_form = document.getElementById("profile_form");
			profile_form.submit();
		}
		</script>
		<form:form id="profile_form" action="../mobile_Mypage/update_profile.html" method="post" modelAtrribute="profileUtil" enctype="multipart/form-data" data-ajax="false">
			<input type="file" id="profile_upload" name="user_profile_img" style="display: none; border:1px solid white;" onChange="javascript:checkProfile()"> 
		</form:form>
		
		<div class="div">
			<img src="${pageContext.request.contextPath}/upload/${sessionScope.profile}" width="50px" height="50px" style="margin:5px; padding:0;" class="profile"/>
			<font class="p" style="font-weight:bold; font-size:15px; margin:5px; valign:middle; ">${sessionScope.user_nick}</font>
			<spring:hasBindErrors name="profileUtil">
				<font color="red">
				<c:forEach var="error" items="${errors.globalErrors}">
				<spring:message code="${error.code}"/>
				</c:forEach>
				</font>
			</spring:hasBindErrors>
		</div>
		<div>
			
			<a class="ui-btn ui-mini ui-corner-all" style="margin:0; padding:5;" href="#" onClick="document.all.profile_upload.click();">프로필사진 변경</a>
			<a class="ui-btn ui-mini ui-corner-all" style="margin:0; padding:5;" href="#">비밀번호 변경</a>
			<a class="ui-btn ui-mini ui-corner-all" style="margin:0; padding:5;" href="../mobile_Mypage/logout.html">로그아웃</a>
<!-- 			<button class="ui-btn ui-mini ui-corner-all" style="margin:0; padding:5;" onClick="document.all.profile_upload.click();">프로필사진 변경</button> -->
<!-- 			<button class="ui-btn ui-mini ui-corner-all" style="margin:0; padding:5;" onClick="">비밀번호 변경</button> -->
<!-- 			<button class="ui-btn ui-mini ui-corner-all" style="margin:0; padding:5;">로그아웃</button> -->
		</div>
		<div style="margin-top:10px;">
			<font align="left" color="gray" style="font-weight:bold; font-size:11px; margin:5px;">나의 게시물&nbsp;${MAXPID}개</font>
		</div>
		<div>
		<c:choose>
		<c:when test="${empty FIRST_IMGS[0]}">
				<div align="center" style="margin-top:15px;">
					<font color="gray" style="font-weight:bold; font-size:11px;">내가 올린 게시물이 없습니다.</font>
				</div>
		</c:when>
		<c:otherwise>
		<table width="100%" border="0" style="margin-top:15px;">
		<c:forEach var="i" begin="0" end="${MAX-1}"><!-- 총 게시물갯수/3만큼 반복 -->
		<tr border="0">
			<c:forEach var="num" begin="${TR_INDEX[i]*3-2}" end="${TR_INDEX[i]*3}"><!-- 첫번째게시물부터 3개씩 뿌려줌. 1줄*1번째,1*2,1*3 / 4,5,6 / 7,8,9 -->
			<c:if test="${!empty FIRST_IMGS[num-1]}">
			<td align="center" border="0" style="padding:0; margin:0;">
				<a href="../list/recipe_selected.html?id=${RECIPE_ID[num-1]}">
						<!-- 게시물의 첫번째이미지 뿌려줌  -->
						<img src="${pageContext.request.contextPath}/upload/${FIRST_IMGS[num-1]}" width="100%" height="100px" style="border-radius: 5%;"/>
				</a>
			</td>
			</c:if>
			</c:forEach>
		</tr>
		</c:forEach>
		</table>
		</c:otherwise>
		</c:choose>
		</div>
	</div>
	
	<div data-role="footer" data-position="fixed" style="background-color:white;">
		<table width="100%" align="center" >
			<tr>
				<td align="center">
					<a href="../mobile_List/recipe_list.html" rel="external">
						<img src="../image/home_icon.png" width="25px" height="25px" style="padding:5px;">
					</a>
				</td>
				<td align="center">
					<a href="../recipebook/my_clip_list.html" rel="external">
						<img src="../image/book_icon.png" width="25px" height="25px" style="padding:5px;">
					</a>
				</td>
				<td align="center">
					<a href="../post/upload_start.html" rel="external">
						<img src="../image/upload_icon.png" width="25px" height="25px" style="padding:5px;">
					</a>
				</td>
				<td align="center">
					<a href="../mobile_Mypage/mypage.html" rel="external">
						<img src="../image/mypage_icon.png" width="25px" height="25px" style="padding:5px;">
					</a>
				</td>
			</tr>
		</table>
	</div>
		
		
</div>

</body>
</html>