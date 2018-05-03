<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<link type="text/css" rel="stylesheet" href="design/layerpopup.css" /> 
<script src="jsfile/layerpopup.js"></script>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn-example').click(function(){
	    var $href = $(this).attr('href');
	    layer_popup($href);
	});
});
</script>
<a href="#layer2" class="btn-example">
<font color="gray">X</font>
</a>
<div class="dim-layer">
    <div class="dimBg"></div>
    <div id="layer2" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts" width="100%">
            
                <!--content //-->
               	<div align="center">
                    <a href="/home/homepage.html" class="btn-layerbtn">
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
</body>
</html>