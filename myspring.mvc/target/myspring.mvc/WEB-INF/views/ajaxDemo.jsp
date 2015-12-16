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
				
				$.post("/myspring.mvc/handleAJAXRequest", data, function(json){
					if(json.result == "true"){
						alert("name : " + json.name + ", age : " + json.age);
					}
				});
			});
		</script>
	</body>
</html>