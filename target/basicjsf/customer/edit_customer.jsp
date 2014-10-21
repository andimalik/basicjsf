<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.text.SimpleDateFormat, id.co.skyforce.basicjsf.domain.*"%>

<%
	Customer customer = (Customer) request.getAttribute("customer");
	SimpleDateFormat dateFormatter = (SimpleDateFormat) request
			.getAttribute("dateFormatter");
%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Customer</title>
</head>
<body>
	<h2>Edit Customer</h2>
	<p>
		&raquo; <a href="<%=request.getContextPath() + "/customer"%>">Customer
			List</a>
	</p>
	<%
		String errorMessage = (String) request.getAttribute("errorMessage");

		if (errorMessage != null) {
			out.println("<p>" + errorMessage + "</p>");
		}
	%>
	<form action="<%=request.getContextPath() + "/customer"%>"
		method="post">
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="customer_id" value="<%=customer.getId()%>">
		<p>
			<input maxlength="50" type="text" name="email"
				placeholder="E-mail..." value="<%=customer.getEmail()%>"> <input
				maxlength="50" type="password" name="password"
				placeholder="Password..." value="<%=customer.getPassword()%>"><br>
			<input maxlength="50" type="text" name="first_name"
				placeholder="First name..." value="<%=customer.getFirstName()%>">
			<input maxlength="50" type="text" name="last_name"
				placeholder="Last name..." value="<%=customer.getLastName()%>"><br>
			<input type="text" name="birth_date"
				placeholder="Birth date (yyyy-MM-dd)..."
				value="<%=dateFormatter.format(customer.getBirthDate())%>"><br>
			<input maxlength="50" type="text" name="mobile_phone"
				placeholder="Mobile phone..." value="<%=customer.getMobileNo()%>">
			<input maxlength="50" type="text" name="home_phone"
				placeholder="Home phone..." value="<%=customer.getHomePhone()%>">
		</p>
		<p>
			<input maxlength="50" type="text" name="street"
				placeholder="Street..."
				value="<%=customer.getAddress().getStreet()%>"><br> <input
				maxlength="50" type="text" name="city" placeholder="City..."
				value="<%=customer.getAddress().getCity()%>"><br> <input
				maxlength="5" type="text" name="postal_code"
				placeholder="Postal code..."
				value="<%=customer.getAddress().getPostalCode()%>">
		</p>
		<button type="submit">Save</button>
	</form>
</body>
</html>