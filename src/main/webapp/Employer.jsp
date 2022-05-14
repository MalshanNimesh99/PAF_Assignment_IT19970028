<%@page import="com.Employer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" 
	crossorigin="anonymous">
<script src="Components/jquery-3.6.0.js" type="text/javascript"></script>
<script src="Components/employer.js" type="text/javascript"></script>
<style type="text/css">
#divEmployersGrid{   padding-top:20px; padding-left: 50px; background-color:#b2b9d1;}
.frm{ padding-top:20px; padding-right: 50px; background-color:#a1abc9; border-right: 1px solid grey; }
</style>
<title>Employer Management</title>
</head>
<body style="background-color:#c3c9e3;">
	<div class="container" style="box-shadow: 1px 15px 30px #404037; background-color: #dcdee3;" >
	<h1 style="margin:20px 0px 20px 400px; padding-top: 10px;">Employer Management</h1>
	<hr>
	<div class="row">
			<div class="col-4 frm">				
				<form id="formEmployer" name="formEmployer" action="">
					Employer NIC: <input id="nic" name="nic" type="text"
						class="form-control form-control-sm"> <br> 
						
					Employer Name: <input id="name" name="name" type="text"
						class="form-control form-control-sm"> <br> 
						
					Employer Address: <input id="address" name="address" type="text"
						class="form-control form-control-sm"> <br> 
						
					Employer Department: <input id="dept" name="dept" type="text"
						class="form-control form-control-sm"> <br>
						
					Employer Contact: <input id="contact" name="contact" type="text"
						class="form-control form-control-sm"> <br> 
						
						<input style="width: 50%;" id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> 
						
						<input type="hidden" id="hidEmployerIDSave" name="hidEmployerIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				</div>
				<div class="col-8"  id="divEmployersGrid">
					<%
					Employer empObj = new Employer();
					out.print(empObj.readEmployers());
					%>
				</div>
		</div>
		</div>
</body>
</html>
