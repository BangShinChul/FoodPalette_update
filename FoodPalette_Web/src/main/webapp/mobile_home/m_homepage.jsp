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
    <title>홈화면</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width">
    <link type="text/css" rel="stylesheet" href="../design/layerpopup.css" />
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="../jsfile/layerpopup_comment.js"></script>
	
   <style type="text/css">
	.profile {
	    object-fit: cover;
	    object-position: bottom;
	    border-radius: 50%;
	    border : 1px solid lightgray;
	}
	.ui-input-search.ui-custom { border: none; box-shadow: none; }
	</style>

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
				
				
				$( ".opensearch" ).on( 'click', tapHandler );
				
				function tapHandler( event ) {
				  $('.search_field').slideToggle();
				  setTimeout(function(){
						 $('#search').focus().tap();
						},0);
				}
				
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
			
				<table id="recipe_container${num}" align="center" style="background-color:white; border:1px solid lightgray; margin-top:50px;" class="ui-corner-all">
					<tr>
						<td width="35px" style="padding:5px; border-right:0px solid white;">
							<img src="${pageContext.request.contextPath}/upload/${recipe.user_profile_img}" width="35px" height="35px" class="profile"
							style="object-fit: cover; object-position: bottom; border-radius: 50%; border : 1px solid lightgray;"/>
						</td>
						<td width="60px" align="left" style=" margin:0;">
							<font style="font-weight:bold; font-size:11px;">${recipe.user_nick}</font>
						</td>		
						<!-- 메뉴아이콘(아래) -->
						<td align="right" style="padding-left:40px; margin:0;">
							<a class="ui-corner-all ui-shadow" href="#positionWindow${num}" data-rel="popup" data-position-to="window">
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
						<td align="right" style="padding-right:15px; border-left:0px solid white;">
							<font color="gray" style="font-size:11px;">${recipe.recipe_posttime_cal}</font>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<a href="../mobile_List/recipe_selected.html?id=${recipe.recipe_id}" rel="external"><!-- 외부페이지를 불러줄때 rel="external"사용 -->
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
							<font style="font-size:12px;">${recipe.recipe_content}</font>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="padding-top:5px; padding-bottom:10px; padding-left:15px;">
							<font color="gray" style="font-size:12px;">${recipe.recipe_hashtag}</font>
						</td>
					</tr>
				</table>
			
			<br/>
			</c:forEach>

		</div>
		
		<div data-role="footer" data-position="fixed" style="background-color:white;">
		<table width="100%" align="center" >
			<tr>
				<td align="center">
					<a href="../mobile_List/recipe_list.html">
						<img src="../image/home_icon.png" width="25px" height="25px" style="padding:5px;">
					</a>
				</td>
				<td align="center">
					<a href="../recipebook/my_clip_list.html">
						<img src="../image/book_icon.png" width="25px" height="25px" style="padding:5px;">
					</a>
				</td>
				<td align="center">
					<a href="../post/upload_start.html">
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