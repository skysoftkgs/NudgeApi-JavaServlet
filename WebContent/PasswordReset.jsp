<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<!-- <section class="content-header">
		<h1>
			Page Header <small>Optional description</small>
		</h1> 
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
			<li class="active">Here</li>
		</ol>
	</section> -->

	<!-- Main content -->
	
	<script>
	
	function CheckPassword(){

		 var password=document.getElementById('password').value;  	
		 var confirm_password=document.getElementById('confirm_password').value;  
		
		 if(confirm_password!=password) {
		
		 alert("Password And Confirm Password Must Be Same");
		 document.getElementById('confirm_password').focus();  
		 return false;
		 }	
		
		 else{
			 
		 
		 return true;
		 }  
		}
	
	</script>
	<section class="content">

		<!-- Your Page Content Here -->
		<h3>NUDGE</h3>
		<div class="box box-primary" style="width: 50%; margin-left: 25%;">
			<div class="box-header">
			<h3>Reset Password</h3>
				
			</div>
			
				
						
			<!-- /.box-header -->
			<!-- form start -->
			<!-- <form role="form" method="post" action="InsertCar" > -->
			<form role="form" method="post" action="PasswordUpdate" onsubmit="return CheckPassword()" >
			
				<div class="box-body">
				
				<%
				String email=request.getParameter("email");
				%>
				
				<div class="form-group">
						 <input
							type="hidden" class="form-control" id="email" name="email" value="<%=email %>"
							>
					</div>
				
				<div class="form-group">
						<label for="exampleInputEmail1">Password</label> <input
							type="password" class="form-control" id="password" name="password"
							placeholder="Enter Password" required>
					</div><br>
					
					<div class="form-group">
						<label for="exampleInputEmail1">Confirm Password</label> <input
							type="password" class="form-control" id="confirm_password" name="confirm_password"
							placeholder="Confirm Password" required>
					</div><br>
					
				
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>
		<!-- /.box -->


	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->



