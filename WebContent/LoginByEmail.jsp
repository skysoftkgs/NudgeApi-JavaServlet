<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3>LOGIN</h3>

<form role="form" method="post" action="LoginByEmail">

                 
					
						<div class="form-group">
						<label for="exampleInputEmail1">email<font color="red">*</font></label> <input type="text"
							class="form-control" id="email" name="email"
							placeholder="Enter Email"  />
					</div><br>
					
						<div class="form-group">
						<label for="exampleInputEmail1">password<font color="red">*</font></label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="Enter Password" />
					</div><br>
					
					<div class="form-group">
						<label for="exampleInputEmail1">device_token</label> <input type="text"
							class="form-control" id="device_token" name="device_token"
							placeholder="Enter Device Token" />
					</div><br>

                    <div class="form-group">
						<label for="exampleInputEmail1">device_type</label> <input type="text"
							class="form-control" id="device_type" name="device_type"
							placeholder="Enter Device Type" />
					</div><br>


				<input type="submit" class="btn btn-primary" value="submit">

</body>
</html>