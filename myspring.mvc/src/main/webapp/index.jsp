<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
		<title>Spring MVC</title>
	</head>
	<body>
		Name <input type = "text" id = "name" value=""/><br/>
		Age <input type = "text" id = "age" value=""/><br/>
		<input type = "button" id="submit" value="Submit" />
		<script type="text/javascript">
			$("#submit").click(function(){
				var data = {
					"name" : $("#name").val(),
					"age" : $("#age").val()
				};
				
				$.ajax({
					type: "POST",
					contentType : "application/json",
					url : "/myspring.mvc/views/handleAJAXRequest",
					data: JSON.stringify(data),
					dataType: 'json',
					timeout: 100000,
					success: function(data){
						console.log("SUCESS: " , data);
						alert(JSON.stringify(data));
					},
				    error: function(e){
				    	console.log("ERROR", e);
				    	alert(JSON.stringify(e));
				    }  ,
					done: function(e){
						console.log("DONE");
					}
				});
			});
		</script>
	</body>
</html>