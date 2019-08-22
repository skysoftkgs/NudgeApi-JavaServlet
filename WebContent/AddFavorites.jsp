<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3>Add Favorites</h3>

<form role="form" method="post" action="AddFavorites" >

				<div class="box-body">


              
					<div class="form-group">
						<label for="exampleInputEmail1">user_id<font color="red">*</font></label> <input type="text"
							class="form-control" id="user_id" name="user_id"
							placeholder="Enter User Id" >
					</div><br>
					
                    <div class="form-group">
						<label for="exampleInputEmail1">friend_id<font color="red">*</font></label> <input type="text"
							class="form-control" id="friend_id" name="friend_id"
							placeholder="Enter Friend Id" >
					</div><br>
                    
                    <div class="form-group" >
						<label for="exampleInputEmail1">prod_id<font color="red">*</font></label> <input type="text"
							class="form-control" id="prod_id" name="prod_id"
							placeholder="Enter product id" >
					</div><br>
					
					  <div class="form-group" >
						<label for="exampleInputEmail1">prod_name</label> <input type="text"
							class="form-control" id="prod_name" name="prod_name"
							placeholder="Enter product name" >
					</div><br>
					
					  <div class="form-group" >
						<label for="exampleInputEmail1">price</label> <input type="text"
							class="form-control" id="price" name="price"
							placeholder="Enter price" >
					</div><br>
					
					  <div class="form-group" >
						<label for="exampleInputEmail1">prod_image</label> <input type="text"
							class="form-control" id="prod_image" name="prod_image"
							placeholder="Enter product image" >
					</div><br>
					 <div class="form-group" >
							<label for="exampleInputEmail1">slug</label> <input type="text"
							class="form-control" id="slug" name="slug"
							placeholder="Enter slug" >
					</div><br>
					 <div class="form-group" >
							<label for="exampleInputEmail1">description</label> <input type="text"
							class="form-control" id="description" name="description"
							placeholder="Enter description" >
					</div><br>
					 <div class="form-group" >
							<label for="exampleInputEmail1">short_description</label> <input type="text"
							class="form-control" id="short_description" name="short_description"
							placeholder="Enter short_description" >
					</div><br>
					 <div class="form-group" >
							<label for="exampleInputEmail1">regular_price</label> <input type="text"
							class="form-control" id="regular_price" name="regular_price"
							placeholder="Enter regular_price" >
					</div><br>
					 <div class="form-group" >
							<label for="exampleInputEmail1">sale_price</label> <input type="text"
							class="form-control" id="sale_price" name="sale_price"
							placeholder="Enter sale_price" >
					</div><br>
					 <div class="form-group" >
							<label for="exampleInputEmail1">total_sales</label> <input type="text"
							class="form-control" id="total_sales" name="total_sales"
							placeholder="Enter total_sales" >
					</div><br>
					
				 <div class="form-group" >
							<label for="exampleInputEmail1">external_url</label> <input type="text"
							class="form-control" id="external_url" name="external_url"
							placeholder="Enter external_url" >
					</div><br>
					
                      
				</div><br>
					
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit">
				</div>
				
												
				
			</form>

</body>
</html>