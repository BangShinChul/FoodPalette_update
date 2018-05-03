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
<link href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
<style type="text/css">
	.profile {
	    object-fit: cover;
	    object-position: bottom;
	    border-radius: 50%;
	    border : 1px solid lightgray;
	}
	.comment_position{
	vertical-align:center
	}
	.ui-input-text.ui-custom { border: none; box-shadow: none; } 
	.ui-button.ui-custom { border: none; box-shadow: none; } 
	table {
	table-layout:fixed;
	word-break:break-all;
	}
</style>
<title>Insert title here</title>

</head>
<body>
	<div data-role="page" id="page1" style="background-color:MintCream;">
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
		</div>
		
		<div data-role="content" style="margin:0; padding:0;">	
			<!--JQurey  -->
			<script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
			
			<script>
			function updateDiv()
			{ 
			    $( "#rotation" ).load(window.location.href + " #rotation" );
			}
			
			//jung
			function deleteCheck(){ 	
				if (confirm("정말로 삭제 하시겠습니까?")){ 
				   console.log("정말로 삭제 클릭"); 
			  	}else{  
				  $('#recipe_del').attr('href',' ');
				} 
			}
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
			<table id="recipe_container" align="center" style="background-color:white; border:1px solid lightgray; margin-top:15px; margin-bottom:15px;" class="ui-corner-all">
				<tr>
					<td width="35px" style="padding:5px; margin:0; border-right:0px solid white;">
						<img src="${pageContext.request.contextPath}/upload/${recipe.user_profile_img}" width="35px" height="35px" class="profile"/>
					</td>
					<td width="60px" align="left" style=" margin:0;">
						<font style="font-weight:bold; font-size:11px;">${recipe.user_nick}</font>
					</td>
					<td align="right" style=" padding-left:40px; margin:0;">
			 			<a class="ui-corner-all ui-shadow" href="#positionWindow${num}" data-rel="popup" data-position-to="window" data-wrapper-class="ui-custom">
							<img src="../image/more.png" width="25px" height="25px"/>
						</a>
							
						<c:choose>
							<c:when test="${sessionScope.user_nick == recipe.user_nick}">
								<div align="center" class="ui-content" id="positionWindow${num}" data-role="popup" data-theme="a" data-overlay-theme="b" data-transition="fade" style="padding:10px;">
									<a style="border-bottom:1px solid lightgray;" href="../post/modify_cotent.html?recipe_id=${recipe.recipe_id}&TOTAL=YES&FOCUS=recipe_container${num}">
									내 레시피 수정하기</a><br>
									<a style="border-bottom:1px solid lightgray;" href="../post/delete_recipe.html?recipe_id=${recipe.recipe_id}&TOTAL=YES">
									내 레시피 삭제하기</a><br>
									<a href="#" data-rel="back">취소</a>
								</div>
							</c:when>
								
							<c:when test="${sessionScope.user_nick != recipe.user_nick}">
								<div align="center" class="ui-content" id="positionWindow${num}" data-role="popup" data-theme="a" data-overlay-theme="b" data-transition="fade" style="padding:10px;">
									<a style="border-bottom:1px solid lightgray;" href="../recipebook/clip_recipe.html?recipe_id=${recipe.recipe_id}&TOTAL=YES&FOCUS=recipe_container${num}">
									레시피북에 스크랩하기</a><br>
									<a style="border-bottom:1px solid lightgray;" href="../recipebook/delete_clip_recipe.html?recipe_id=${recipe.recipe_id}&TOTAL=YES">
									레시피북에서 삭제하기</a><br>
									<a href="#" data-rel="back">취소</a>
								</div>
							</c:when>			
						</c:choose>
					</td>
					<td width="50px" align="right" style=" padding-right:15px; margin:0; border-left:0px solid white;">
						<font color="gray" style="font-size:11px;">${recipe.recipe_posttime_cal}</font>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="center" class="fotorama" data-allowfullscreen="true" data-width="260">
						  	<c:forEach var="i" begin="1" end="${recipe.recipe_img_order}">
								<img src="${pageContext.request.contextPath}/upload/${imgs[i-1]}" width="auto"/>
			           		</c:forEach>
						</div>			           	
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
						<font style="font-size:12px;">${recipe.recipe_content}</font>
					</td>
				</tr>
				<tr style="border-bottom:0px solid white;">
					<td colspan="4" style="padding-top:5px; padding-bottom:10px; padding-left:15px;">
						<font color="gray" style="font-size:12px;">${recipe.recipe_hashtag}</font>
					</td>
				</tr>
				<tr style="border-bottom:1px solid lightgray;">
					<td colspan="4" style="margin:0; padding:0;">
						<div style="border-bottom:1px solid lightgray; padding-bottom:10px;">
							<font color="gray" style="font-size:11px;">댓글&nbsp;${comment_num}</font>
						</div>
						<c:set var="cnt" value="0"/>
						<c:forEach var="comment" items="${COMMENT}">
						<c:set var="cnt" value="${cnt+1}"/>
							<table width="100%">
								<tr style="border-bottom:1px solid lightgray;">
								<td width="35px" style="margin:0; padding-top:5px; ">
									<img src="${pageContext.request.contextPath}/upload/${comment.user_profile_img}" width="35px" height="35px" class="profile"/>
								</td>
								<td style="margin:0; padding:0;">
									<font style="font-weight:bold; font-size:11px;">${comment.user_nick}</font><br>
									<font color="gray" style="font-size:11px;">${comment.comment_posttime_cal}</font>
								</td>
								<td align="left" width="150px">
									<font style="font-size:11px;">${comment.comment_text}</font>
								</td>
									<c:if test="${comment.user_nick==sessionScope.user_nick}">
										<td width="10px" align="right" style="padding-right:5px; margin:0;">
										<a class="ui-corner-all ui-shadow" href="#comment${cnt}" data-rel="popup" data-position-to="window" style="padding-left:2px;">
											<font color="gray" style="font-weight:bold; font-size:11px;">X</font>
										</a>
										
										<div align="center" class="ui-content" id="comment${cnt}" data-role="popup" data-theme="a" data-overlay-theme="b" data-transition="fade" style="padding:10px;">
											<a style="border-bottom:1px solid lightgray;" href="../comment/delete_Comment.html?recipe_id=${comment.recipe_id}&user_nick=${comment.user_nick}&comment_order=${comment.comment_order}">
											댓글 삭제하기</a><br>
											<a href="#" data-rel="back">취소</a>
										</div>
										</td>
									</c:if>
								</tr>
								</table>
								</c:forEach>
								<form:form modelAttribute="comment" action="../comment/insert_Comment.html" method="post" name="commentForm">
								<input type="hidden" name="recipe_id" value="${recipe.recipe_id}">
								<input type="hidden" name="user_nick" value="${sessionScope.user_nick}">
									
								<div style="float: left; width: 80%; margin-top:0; padding:0;">
									<input name="comment_text" id="comment_text" type="text" value="" data-clear-btn="true" placeholder="댓글 달기" data-wrapper-class="ui-custom" width="250px" style="font-size:11px; margin:0; padding:0;">
								</div>	
								<div style="float: left; width: 20%; margin:0; padding:0;">
									<input type="image" src="../image/btn.jpg" name="submit" value="submit" width="30px" height="30px" align="right" style="margin-left:0; vertical-align:middle; margin:0; padding:0;"/>
								</div>
								</form:form>

					</td>
				</tr>
			</table>
			
			 
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