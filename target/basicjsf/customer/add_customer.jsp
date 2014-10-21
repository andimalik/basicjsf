<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="java.util.List, java.text.SimpleDateFormat, id.co.skyforce.basicjsf.domain.*"%>

<%
	List<Customer> customerList = (List<Customer>) request
			.getAttribute("customerList");
	SimpleDateFormat dateFormatter = (SimpleDateFormat) request
			.getAttribute("dateFormatter");
%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Management</title>
</head>
<body>
	<h2>Add New Customer</h2>
	<p>
		&raquo; <a href="<%=request.getContextPath() + "/customer"%>">Customer
			List</a>
	</p>
	<form action="<%=request.getContextPath() + "/customer"%>"
		method="post">
		<input type="hidden" name="action" value="add">
		<p>
			<input maxlength="50" type="text" name="email"
				placeholder="E-mail..." value="name@domain.tld"> <input
				maxlength="50" type="password" name="password"
				placeholder="Password..."><br> <input maxlength="50"
				type="text" name="first_name" placeholder="First name..."> <input
				maxlength="50" type="text" name="last_name"
				placeholder="Last name..."><br> <input type="text"
				name="birth_date" placeholder="Birth date (yyyy-MM-dd)..."><br>
			<input maxlength="50" type="text" name="mobile_phone"
				placeholder="Mobile phone..."> <input maxlength="50"
				type="text" name="home_phone" placeholder="Home phone...">
		</p>
		<p>
			<input maxlength="50" type="text" name="street"
				placeholder="Street..."><br> <input maxlength="50"
				type="text" name="city" placeholder="City..."><br> <input
				maxlength="5" type="text" name="postal_code"
				placeholder="Postal code...">
		</p>
		<button type="submit">Save</button>
	</form>
</body>
</html>