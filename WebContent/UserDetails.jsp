<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
</head>
<body>

<h3>Get User Details</h3>

<form role="form" method="post" action="UserDetails" >

			
					<div class="form-group">
						<label for="exampleInputEmail1">user_id<font color="red">*</font></label> <input type="text"
							class="form-control" id="user_id" name="user_id"
							placeholder="Enter User Id" >
					</div><br>
                    
               
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit">
				</div>
				
												
				
			</form>

</body>
</html>