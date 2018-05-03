<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function change(recipe_id){
	var id = recipe_id;
	if($('#heart'+id).attr('src') == "../image/non_heart.png"){
		$('#heart'+id).attr('src',"../image/in_heart.png");	
		$.ajax({
			url:"http://localhost:8080/FoodPalette_Web/heart/update_heart_ajax.html?h_check=OK&recipe_id="+id,
			type:"get",
			dataType:"text",
			success:function(data){
				$('#heart_cnt'+id).html(data);
			},
			error:function(request,status,error){
				alert("error\request : " + request + "\n\n" + "status : " + request.status + "\n\n" + "error : " + request.error);
			}
		});
		
	}else{
		$('#heart'+id).attr('src',"../image/non_heart.png");
		$.ajax({
			url:"http://localhost:8080/FoodPalette_Web/heart/update_heart_ajax.html?h_check=NOK&recipe_id="+id,
			type:"get",
			dataType:"text",
			success:function(data){
				$('#heart_cnt'+id).html(data);
			},
			error:function(request,status,error){
				alert("error\request : " + request + "\n\n" + "status : " + request.status + "\n\n" + "error : " + request.error);
			}
		});
		
	}
	
}
</script>

</head>
<body>
	<table border="0">
		<tr>
			<td>
			<a href="javascript:change('${recipe_id}')">
				<img id="heart${recipe_id}" src="../image/${heart_img}"/>
			</a>
			</td>
			<td>
			<font color="gray" style="font-size:12px;">
				<span id="heart_cnt${recipe_id}">${heart_count}</span> heart
			</font>
			</td>
		</tr>
	</table>
	
	
</body>
</html>