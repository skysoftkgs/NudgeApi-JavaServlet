<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>UPLOAD PROFILE THROUGH FB/CONTACTS</h3>

	<form role="form" method="post" action="UploadProfiles">

		<div class="box-body">

			<div class="form-group">
				<label for="exampleInputEmail1">body</label> 
				<textarea rows="5" cols="10" id="body" name="body"></textarea>
				<!-- <input type="text"
					class="form-control" id="body" name="body"
					placeholder="Enter body"> -->
			</div>
			<br>

		</div>

		<!-- /.box-body -->

		<div class="box-footer">
			<input type="submit" class="btn btn-primary" value="Submit">
		</div>



	</form>


</body>
</html>