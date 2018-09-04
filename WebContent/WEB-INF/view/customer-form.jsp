<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
	
		<title>Save Customer</title>
	
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">

	</head>
	
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>Customer Relationship Manager</h2>
			</div>
		</div>
		
		<div id="container">
			<h3>Save Customer</h3>
			<i>Kindly fill out the details. Asterisk (*) means required.</i>
			<form:form action ="saveCustomer" modelAttribute="customer" method="POST">
			
				<form:hidden path="id" /> 
				
				<table>
					<tbody>
						<tr>
							<td><label>First Name (*):</label></td>
							<td><form:input path="firstName" /></td>
							<form:errors style="color:red;" path="firstName" cssClass="error" /><br>
						</tr>
						
						<tr>
							<td><label>Last Name (*):</label></td>
							<td><form:input path="lastName" /></td>
							<form:errors style="color:red;" path="lastName" cssClass="error" /><br>
						</tr>
						
						<tr>
							<td><label>Email:</label></td>
							<td><form:input path="email" /></td>
							<form:errors style="color:red;" path="email" cssClass="error" /><br>
						</tr>
						
						<tr>
							<td><label>Phone Number:</label></td>
							<td><form:input path="phoneNumber" /></td>
							<form:errors style="color:red;" path="phoneNumber" cssClass="error" />
						</tr>
						
						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Submit" class="save" /></td>
						</tr>
					</tbody>
				</table>
			</form:form>
			
			<div style="clear; both;"></div>
			<p>
				<a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
			</p>
		</div>
	</body>
</html>