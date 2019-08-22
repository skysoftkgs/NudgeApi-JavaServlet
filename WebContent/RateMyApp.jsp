<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Rate My App</h3>

<form role="form" method="post" action="RateMyApp">

				<div class="box-body">


                
					<div class="form-group">
						<label for="exampleInputEmail1">user_id<font color="red">*</font></label> <input type="text"
							class="form-control" id="user_id" name="user_id"
							placeholder="Enter User Id" >
					</div><br>
					
                     <div  class="form-group">
						<label>push_not:<font color="red">*</font></label> <select class="form-control" name="push_not" id="push_not"
							>
							
							<option value="">Select Value</option>
							<option value="0">0</option>
							<option value="1">1</option>
							
						</select>
					</div><br>
					
					   <div  class="form-group">
						<label>email_not:<font color="red">*</font></label> <select class="form-control" name="email_not" id="email_not"
							>
							
							<option value="">Select Value</option>
							<option value="0">0</option>
							<option value="1">1</option>
							
						</select>
					</div><br>
					
                    <div class="form-group" >
						<label for="exampleInputEmail1">feedback</label> 
							<textarea rows="5" cols="10" id="feedback" name="feedback"></textarea>
					</div><br>
					
					   <div  class="form-group">
						<label>rating:</label> <select class="form-control" name="rating" id="rating"
							>
							
							<option value="">Select Value</option>
							<option value="0">1</option>
							<option value="1">2</option>
							<option value="1">3</option>
							<option value="1">4</option>
							<option value="1">5</option>
							
						</select>
					</div><br>
				
				
					
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit">
				</div>
				
												
				
			</form>
</body>
</html>