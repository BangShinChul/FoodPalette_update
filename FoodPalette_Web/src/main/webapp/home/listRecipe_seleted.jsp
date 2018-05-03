<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="jsp_header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="http://code.jquery.com/jquery-1.11.3.min.js" ></script>

<!-- Swiper -->
<link rel="stylesheet" href="../jsfile/css/swiper.min.css">
<script src="../jsfile/js/swiper.min.js"></script>

<!-- Layer-popup menu -->
<link type="text/css" rel="stylesheet" href="../design/layerpopup.css" /> 
<!-- <script src="../jsfile/layerpopup.js"></script> -->

<!-- Layer-popup comment --> 
<script src="../jsfile/layerpopup_comment.js"></script>

<style type="text/css">
    body {
        background: #eee;
        font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
        font-size: 14px;
        color:#000;
        margin: 0;
        padding: 10px;
    }
    .swiper-container {
        width : 700px;
    }
    .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;
        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
    }
	.profile {
	    width: 50px; height: 50px;
	    object-fit: cover;
	    object-position: bottom;
	    border-radius: 50%;
	    border : 1px solid gray;
	}
	.comment_position{
	vertical-align:center
	}
	
</style>
<title>Insert title here</title>

</head>
<body>

<!--JQurey  -->
<script>
function updateDiv()
{ 
    $( "#rotation" ).load(window.location.href + " #rotation" );
}

$(document).ready(function(){
	$('.btn-layer').click(function(){
	    var $href = $(this).attr('href');
	    var id = $(this).attr('id');
	    layer_popup($href,id);
	});
	
	$('.btn-example').click(function(){
	    var $href = $(this).attr('href');
	    var id = $(this).attr('id');
	    layer_popup($href,id);
	});
	
	//jung
	$('#recipe_del').click(function(){
		   console.log("내레시피 삭제 클릭");
		   deleteCheck();
	});   
});

//jung
function deleteCheck(){ 	
	if (confirm("정말로 삭제 하시겠습니까?")){ 
	   console.log("정말로 삭제 클릭"); 
  	}else{  
	  $('#recipe_del').attr('href',' ');
	 } 
}


</script>

<br/>
<div id="recipe_container" align="center">
	<table border="1">
		<tr>
			<td width="60px" style="padding-top:5px; padding-left:15px; border-right:0px solid white;">
				<img src="${pageContext.request.contextPath}/upload/${recipe.user_profile_img}" class="profile"/>
			</td>
			<td align="left" style="border-left:0px solid white; border-right:0px solid white;">
				<font style="font-weight:bold;">${recipe.user_nick}</font>
			</td>
			<td align="right" style="border-left:0px solid white; border-right:0px solid white;">
	 			<a href="#layer_menu" class="btn-example" id="#dim_menu">
					<input type="image" name="more" src="../image/more.png" value="button" width="30px" height="30px">
				</a>		
				<div class="dim-layer" id="dim_menu">
	    			<div class="dimBg"></div>
			    	<div id="layer_menu" class="pop-layer">
				     	<div class="pop-container">
				           	<div class="pop-conts">
				           		<c:choose>
				           		<c:when test="${sessionScope.user_nick == recipe.user_nick}">
					            <div align="center" >
			  		                <a href="../post/modify_cotent.html?recipe_id=${recipe.recipe_id}&TOTAL=NO" class="btn-layerbtn">내 레시피 수정하기</a>
					            </div>
					            <div align="center">
					                <a href="../post/delete_recipe.html?recipe_id=${recipe.recipe_id}&TOTAL=NO" class="btn-layerbtn" id="recipe_del">내 레시피 삭제하기</a>
					                <%-- ../post/delete_recipe.html?recipe_id=${recipe.recipe_id} --%>
					            </div>
					            </c:when>
					           	<c:when test="${sessionScope.user_nick!=recipe.user_nick && recipe.clip_flag == 'NO'}">
					           	<div align="center" >
			  		                <a href="../recipebook/clip_recipe.html?recipe_id=${recipe.recipe_id}&TOTAL=NO" class="btn-layerbtn">레시피북에 스크랩하기</a>
					            </div>
					           	</c:when>
					           	<c:otherwise>
					           	<div align="center" >
					           		<a href="../recipebook/delete_clip_recipe.html?recipe_id=${recipe.recipe_id}&TOTAL=NO" class="btn-layerbtn" >레시피북에서 삭제하기</a>
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
			<td width="100px" align="right" style="padding-right:15px; border-left:0px solid white;">
				${recipe.recipe_posttime_cal}
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<div class="swiper-container">
        			<div class="swiper-wrapper">
        				<c:forEach var="i" begin="1" end="${recipe.recipe_img_order}">
            			<div class="swiper-slide">
							<img src="${pageContext.request.contextPath}/upload/${imgs[i-1]}" width="700px"/>
            				<div class="swiper-button-next"></div>
        					<div class="swiper-button-prev"></div>
            			</div>
            			</c:forEach>
            		</div>
            		     <!-- 프로그래스 바 -->
     					<div class="swiper-pagination"></div>
            	</div>
			</td>
		</tr>
		<tr style="border-bottom:0px solid white;">
			<td colspan="4" style="padding-top:5px; padding-left:12px;">
				<jsp:include page="../heart/go_heart.html">
					<jsp:param value="${recipe.recipe_id}" name="recipe_id"/>
					<jsp:param value="select" name="page"/>
				</jsp:include>	
			</td>
		</tr>
		<tr style="border-bottom:0px solid white;">
			<td colspan="4" style="padding-top:5px; padding-bottom:15px; padding-left:15px;">
				${recipe.recipe_content}
			</td>
		</tr>
		<tr style="border-bottom:0px solid white;">
			<td colspan="4" colspan="4" style="padding-top:5px; padding-bottom:10px; padding-left:15px;">
				<font color="gray">${recipe.recipe_hashtag}</font>
			</td>
		</tr>
		<tr style="border-bottom:1px solid lightgray;">
			<td colspan="4" style="padding-top:5px; padding-bottom:0px; padding-left:15px;">
				<div style="border-bottom:1px solid lightgray; padding-bottom:10px;">
				<font color="gray">댓글&nbsp;${comment_num}</font>
				</div>
				<c:set var="cnt" value="0"/>
				<c:forEach var="comment" items="${COMMENT}">
				<c:set var="cnt" value="${cnt+1}"/>
					<table width="100%">
					<tr style="border-bottom:1px solid lightgray;">
						<td width="60px">
							<img src="${pageContext.request.contextPath}/upload/${comment.user_profile_img}" class="profile"/>
						</td>
						<td width="80px">
							<font style="font-weight:bold;">${comment.user_nick}</font>
						</td>
						<td align="left">
							${comment.comment_text}
						</td>
						<td align="left" width="120px">
							<font color="gray">${comment.comment_posttime_cal}</font>
						</td>
							<c:if test="${comment.user_nick==sessionScope.user_nick}">
								<td>
									<a href="#layer${cnt}" class="btn-layer" id="#dim-layer${cnt}">
									<font color="gray">X</font>
									</a>
									
									<div class="dim-layer" id="dim-layer${cnt}" >
									    <div class="dimBg"></div>
									    <div id="layer${cnt}" class="pop-layer">
									        <div class="pop-container">
									            <div class="pop-conts" width="100%">
									            
									                <!--content //-->
									               	<div align="center">
									                    <a href="../comment/delete_Comment.html?recipe_id=${comment.recipe_id}
									                    &user_nick=${comment.user_nick}
									                    &comment_order=${comment.comment_order}" class="btn-layerbtn">
									                    	댓글 삭제
									                    </a>
									                </div>
									                <div align="center">
									                    <a href="#" class="btn-layerClose">취소</a>
									                </div>
									                <!--// content-->
									                
									            </div>
									        </div>
									    </div>
									</div>
								</td>
							</c:if>
					</tr>
					</table>
				</c:forEach>
				<form:form modelAttribute="comment" action="../comment/insert_Comment.html" method="post" name="commentForm">
				
				<input type="hidden" name="recipe_id" value="${recipe.recipe_id}">
				<input type="hidden" name="user_nick" value="${sessionScope.user_nick}">

				<div align="center" style="background-color:white; padding-bottom:0px;">
					<form:input path="comment_text" placeholder="댓글 달기" style="width : 95%; height:30px; margin-top:0; margin-bottom:0; margin-right:0; vertical-align:middle; border:none;"/>
					<input type="image" src="../image/btn.jpg" name="submit" value="submit" width="30px" height="30px" align="right" style="margin-left:0; vertical-align:middle;"/>
				</div>
				</form:form>
			</td>
		</tr>
	</table>
</div>
<br/>

  <script>
   		/* Swiper 생성 */
	    var swiper = new Swiper('.swiper-container', {
	    	spaceBetween: 30,
	        roundLengths : true,
	        nextButton : '.swiper-button-next',
	        prevButton : '.swiper-button-prev',
	        pagination : '.swiper-pagination',
	        paginationType: 'progress',
	        runCallbacksOnInit : true,
	        onSlideChangeStart : function(swiper){
	        	scrollTop();
	        }
	    }); 
    
	    /* 슬라이드시 top 이동 함수 */
	    var timeOut;
	    function scrollTop(){
	    	if(document.body.scrollTop!=0 || document.documentElement.scrollTop!=0){
	    		window.scrollBy(0,-50);
	    		timeOut = setTimeout('scrollTop()',10);
	    	}else {
	    		clearTimeout(timeOut); 
	    	}
	    }
    </script>
    

</body>
</html>