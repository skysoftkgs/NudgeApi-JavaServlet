<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3>UPLOAD PROFILE MANUAL</h3>

<form role="form" method="post" action="UploadProfileManual" enctype="multipart/form-data">

				<div class="box-body">


					<div class="form-group">
						<label for="exampleInputEmail1">user_id<font color="red">*</font></label> <input type="text"
							class="form-control" id="user_id" name="user_id"
							placeholder="Enter User Id" >
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
                    
                    <div class="form-group" >
						<label for="exampleInputEmail1">relationship<font color="red">*</font></label> <input type="text"
							class="form-control" id="relationship" name="relationship"
							placeholder="Enter Relation" >
					</div><br>
					
					 <div class="form-group" >
						<label for="exampleInputEmail1">gender<font color="red">*</font></label> <select
							type="text" class="form-control" id="gender"
							name="gender" placeholder="Enter Gender " >
							
							<option>Select Gender</option>
							<option value="male">Male</option>
							<option value="female">Female</option>
							</select>
					</div><br> 
				
				 <!--  <div class="form-group" >
						<label for="exampleInputEmail1">email<font color="red">*</font></label> <input type="text"
							class="form-control" id="email" name="email"
							placeholder="Enter Email" >
					</div><br>
					 -->
					</div>
                     <!--    <div class="form-group">
						<label for="exampleInputEmail1">dob<font color="red">*</font></label> <input
							type="text" class="form-control" id="dob"
							name="dob" placeholder="Enter Date Of Birth " >
					</div><br>
					 -->
                    
					<div class="form-group" >
						<label for="exampleInputEmail1">Isimage<font color="red">*</font></label> <select
							type="text" class="form-control" id="isimage"
							name="isimage">
							
							<option>Select IsImage</option>
							<option value="yes">Yes</option>
							<option value="no">No</option>
							</select>
					</div><br>

						<div class="form-group">
						<div class="btn btn-default btn-file">
							<i class="fa fa-paperclip"></i>image<input type="file"
								name="image" id="image" multiple />
						</div>
				</div><br>
				
				
			
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit">
				</div>
				
												
				
			</form>



</body>
</html>