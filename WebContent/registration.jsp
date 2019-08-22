 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
    
  <%--   <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
 <!--  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> -->
<title>Insert title here</title>
</head>
<body>

<h3>USER REGISTRATION</h3>

<form role="form" method="post" action="RegistrationV1" enctype="multipart/form-data">

				<div class="box-body">


                   <div  class="form-group">
						<label>login_type:<font color="red">*</font></label> <select class="form-control" name="login_type" id="login_type"
							>
							
							<option value="">Select Login Type</option>
							<option value="fb">Facebook</option>
							<option value="email">Email</option>
							
						</select>
					</div><br>
					 

					<div class="form-group">
						<label for="exampleInputEmail1">name<font color="red">*</font></label> <input type="text"
							class="form-control" id="name" name="name"
							placeholder="Enter First Name" >
					</div><br>
					
                    <div class="form-group">
						<label for="exampleInputEmail1">last_name<font color="red">*</font></label> <input type="text"
							class="form-control" id="last_name" name="last_name"
							placeholder="Enter Last Name" >
					</div><br>
                    
                     <div  class="form-group">
						<label>gender:<font color="red">*</font></label> <select class="form-control" name="gender" id="gender"
							>
							
							<option value="">Select Gender</option>
							<option value="Male">Male</option>
							<option value="Female">Female</option>
							
						</select>
					</div><br>
					
                    <div class="form-group" >
						<label for="exampleInputEmail1">email<font color="red">*</font></label> <input type="text"
							class="form-control" id="email" name="email"
							placeholder="Enter Email" >
					</div><br>
					
					<div class="form-group" >
						<label for="exampleInputEmail1">unique_id<font color="red">*</font></label> <input
							type="text" class="form-control" id="unique_id"
							name="unique_id" placeholder="Enter Unique_id " >
					</div><br>
				
					
                        <div class="form-group">
						<label for="exampleInputEmail1">password<font color="red">*</font></label> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="Enter Password" >
					</div><br>
                      
					</div>
                        <div class="form-group">
						<label for="exampleInputEmail1">dob<font color="red">*</font></label> <input
							type="text" class="form-control" id="dob"
							name="dob" placeholder="Enter Date Of Birth " >
					</div><br>

						<div class="form-group">
						<div class="btn btn-default btn-file">
							<i class="fa fa-paperclip"></i>image<input type="file"
								name="image" id="image" multiple />
						</div>
				</div><br>
				
				
				 	<div class="form-group" >
						<label for="exampleInputEmail1">location<font color="red">*</font></label> <input
							type="text" class="form-control" id="location"
							name="location" placeholder="Enter Location " >
					</div><br>
				
				<div class="form-group" >
						<label for="exampleInputEmail1">device_token<font color="red">*</font></label> <input
							type="text" class="form-control" id="device_token"
							name="device_token" placeholder="Enter Device Token " >
					</div><br>
				 
				 <div class="form-group" >
						<label for="exampleInputEmail1">device_type<font color="red">*</font></label> <input
							type="text" class="form-control" id="device_type"
							name="device_type" placeholder="Enter Device Type " >
					</div><br>
				
					
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit">
				</div>
				
												
				
			</form>

</body>
</html>