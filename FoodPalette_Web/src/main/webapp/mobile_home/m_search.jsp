<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>    
<title>검색페이지</title>
   <style type="text/css">
	.profile {
	    object-fit: cover;
	    object-position: bottom;
	    border-radius: 50%;
	    border : 1px solid gray;
	}
	</style>
</head>
<body>
<div data-role="page" style="background-color:MintCream;">
	<div data-role="header" data-position="fixed">
	
	</div>
	<div data-role="content">
		<table width="100%" align="center" style="border:0px solid lightgray;">
			<tr>
				<td align="center">
					<a href="../mobile_List/recipe_list.html">
						<img src="../image/m_title2.png" id="icon" style="margin-top:5px; margin-bottom:0px;">
					</a>
				</td>
			</tr>
		</table>
	</div>
	<div data-role="footer" data-position="fixed">
	<table width="100%" align="center" >
		<tr>
			<td align="center">
				<a href="../mobile_List/recipe_list.html">
					<img src="../image/home_icon.png" width="25px" height="25px">
				</a>
			</td>
			<td align="center">
				<a href="">
					<img src="../image/search_icon.png" width="25px" height="25px">
				</a>
			</td>
			<td align="center">
				<a href="../recipebook/my_clip_list.html">
					<img src="../image/book_icon.png" width="25px" height="25px">
				</a>
			</td>
			<td align="center">
				<a href="../post/upload_start.html">
					<img src="../image/upload_icon.png" width="25px" height="25px">
				</a>
			</td>
			<td align="center">
				<a href="../mypage/mypage.html">
					<img src="../image/mypage_icon.png" width="25px" height="25px">
				</a>
			</td>
		</tr>
	</table>
	</div>
</div>
</body>
</html>