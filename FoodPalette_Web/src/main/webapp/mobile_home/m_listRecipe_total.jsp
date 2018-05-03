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
    <title>total_list</title>
    <link type="text/css" rel="stylesheet" href="../design/m_design.css"/>
	<style type="text/css">
	.profile {
	    object-fit: cover;
	    object-position: bottom;
	    border-radius: 50%;
	    border : 1px solid lightgray;
	}
	</style>
	<script src="../jsfile/layerpopup_comment.js"></script>
	<link type="text/css" rel="stylesheet" href="../design/layerpopup.css" /> 
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<script>
$(document).ready(function(){
	var seq = '<c:out value="${FOCUS}"/>';
	console.log("focus:"+seq);
	
	if(seq){
		fnMove(seq);
	}
	function fnMove(seq){
		var offset = $("#" + seq).offset();
        $('html, body').animate({scrollTop : offset.top}, 5);
    }

	$('.btn-example').click(function(){
		console.log("레이어팝업 클릭");
		var $href = $(this).attr('href');
		var id = $(this).attr('id');
		layer_popup($href,id);
	});

	$('#recipe_del').click(function(){
		console.log("내레시피 삭제 클릭");
		deleteCheck();	 
	});
	
});

function deleteCheck(){ 
	if (confirm("정말로 삭제 하시겠습니까?")){ 
		 console.log("정말로 삭제 클릭"); 
	}else{  
		 $('#recipe_del').attr('href',' ');
	}  
}
</script>
<c:set var="num" value="0"/>

<c:if test="${empty LIST}">
	<table width="100%" style="border:1px solid lightgray;">
	<tr>
		<td align="center" style="padding-top:5px; padding-bottom:5px;">
		<font color="gray" style="font-size:10px;">
			게시글이 없습니다.<br>
			${sessionScope.user_nick}님의 레시피를 공유해보세요!
		</font>
		</td>
	</tr>
	</table>
</c:if>

<c:if test="${SEARCH_CONTENT != null && !empty SEARCH_CONTENT}">
	<div align="center" style="margin-top:20px; margin-bottom:20px;">
		검색어&nbsp; <font color="gray">${SEARCH_CONTENT}</font> &nbsp;의 검색결과
	</div>
</c:if>

<c:set var="num" value="0"/>
<c:forEach var="recipe" items="${LIST}">
<c:set var="num" value="${num+1}"/>

	<table id="recipe_container${num}" align="center" style="border:1px solid lightgray; margin-top:50px; background-color:gray;">
		<tr>
			<td width="35px" style="padding:5px; border-right:0px solid white;">
				<img src="${pageContext.request.contextPath}/upload/${recipe.user_profile_img}" width="35px" height="35px" class="profile"/>
			</td>
			<td width="60px" align="left" style=" margin:0;">
				<font style="font-weight:bold;">${recipe.user_nick}</font>
			</td>		
			<!-- 메뉴아이콘(아래) -->
			<td align="right" style="padding-left:40px; margin:0;">
	
   			<a href="#layer${num}" class="btn-example" id="#dimId${num}">
				<input type="image" name="more" src="../image/more.png" value="button" width="25px" height="25px" >
			</a>
					
			<div class="dim-layer" id="dimId${num}">
    			<div class="dimBg"></div>
		    	<div id="layer${num}" class="pop-layer">
			     	<div class="pop-container">
			           	<div class="pop-conts">
			           		<c:choose>
			           		<c:when test="${sessionScope.user_nick == recipe.user_nick}">
					            <div align="center" >
			  		         		<a href="../post/modify_cotent.html?recipe_id=${recipe.recipe_id}&TOTAL=YES&FOCUS=recipe_container${num}" class="btn-layerbtn" id="modi">내 레시피 수정하기</a>
			  		         		<%-- ../post/modify_cotent.html?recipe_id=${recipe.recipe_id}&TOTAL=YES --%>
					            </div>
					            <div align="center">
					                <a href="../post/delete_recipe.html?recipe_id=${recipe.recipe_id}&TOTAL=YES" class="btn-layerbtn" id="recipe_del">내 레시피 삭제하기</a>
					                <%-- ../post/delete_recipe.html?recipe_id=${recipe.recipe_id} --%>
					            </div>
				            </c:when>
				           	<c:when test="${sessionScope.user_nick != recipe.user_nick && recipe.clip_flag == 'NO'}">
					           	<div align="center" >
			  		                <a href="../recipebook/clip_recipe.html?recipe_id=${recipe.recipe_id}&TOTAL=YES&FOCUS=recipe_container${num}" class="btn-layerbtn">레시피북에 스크랩하기</a>
					            </div>
					        </c:when>
				           	<c:otherwise>
					           	<div align="center" >
					           		<a href="../recipebook/delete_clip_recipe.html?recipe_id=${recipe.recipe_id}&TOTAL=YES" class="btn-layerbtn" >레시피북에서 삭제하기</a>
					           	</div>
				           	</c:otherwise>
				            </c:choose>
				            <div align="center">
				            	<a href="#" class="btn-layerClose">취소</a>
	                		</div>     
            			</div>
        			</div>
    			</div>
			</div>			
			</td>
			<td align="right" style="padding-right:15px; border-left:0px solid white;">
				<font color="gray" style="font-size:11px;">${recipe.recipe_posttime_cal}</font>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<a href="../mobile_List/recipe_selected.html?id=${recipe.recipe_id}" data-rel="external">
					<img src="${pageContext.request.contextPath}/upload/${recipe.recipe_img1}" width="250px"/>
				</a>
			</td>
		</tr>
		<tr style="border-bottom:0px solid white;">
			<td colspan="4" style="padding-top:5px; padding-left:12px;">
				<jsp:include page="../heart/go_heart.html">
					<jsp:param value="${recipe.recipe_id}" name="recipe_id"/>
					<jsp:param value="${PAGE}" name="page"/>
					<jsp:param value="recipe_container${num}" name="focus"/>
					<jsp:param value="${SEARCH_CONTENT}" name="search_content"/>
				</jsp:include>
			</td>
		</tr>
		<tr style="border-bottom:0px solid white;">
			<td colspan="4" style="padding-top:5px; padding-bottom:15px; padding-left:15px;">
				${recipe.recipe_content}
			</td>
		</tr>
		<tr>
			<td colspan="4" style="padding-top:5px; padding-bottom:10px; padding-left:15px;">
				<font color="gray">${recipe.recipe_hashtag}</font>
			</td>
		</tr>
	</table>

<br/>
</c:forEach>


</body>
</html>