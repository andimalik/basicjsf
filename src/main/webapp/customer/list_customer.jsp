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
<style type="text/css">
table td {
	margin: 0px;
	padding: 5px;
}
</style>
</head>
<body>
	<h2>Customer List</h2>
	<p>
		&raquo; <a
			href="<%=request.getContextPath() + "/customer/add_customer.jsp"%>">Add
			Customer</a>
	</p>
	<table border="1">
		<thead>
			<tr>
				<!--th rowspan="2">No.</th-->
				<th rowspan="2">ID</th>
				<th rowspan="2">Name</th>
				<th rowspan="2">E-mail</th>
				<th rowspan="2">Birth Date</th>
				<th colspan="2">Phone</th>
				<th rowspan="2">Address</th>
				<th colspan="2" rowspan="2">Action</th>
			</tr>
			<tr>
				<th>Home</th>
				<th>Mobile</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<td colspan="9">&nbsp;</td>
			</tr>
		</tfoot>
		<tbody>
			<%
				//int number = 1;
				for (Customer customer : customerList) {
					out.println("<tr>");

					//out.println("<td>" + (number++) + "</td>");
					out.println("<td>" + customer.getId() + "</td>");
					out.println("<td>" + customer.getFirstName() + " "
							+ customer.getLastName() + "</td>");
					out.println("<td>" + customer.getEmail() + "</td>");
					out.println("<td>"
							+ dateFormatter.format(customer.getBirthDate())
							+ "</td>");
					out.println("<td>" + customer.getHomePhone() + "</td>");
					out.println("<td>" + customer.getMobileNo() + "</td>");
					out.println("<td>" + customer.getAddress().getStreet() + "<br>"
							+ customer.getAddress().getCity() + ", "
							+ customer.getAddress().getPostalCode() + "</td>");

					out.println("<td>");
					out.println("<form action=\"\" method=\"get\">");
					out.println("<input type=\"hidden\" name=\"customer_id\" value=\""
							+ customer.getId() + "\">");
					out.println("<input type=\"hidden\" name=\"action\" value=\"edit\">");
					out.println("<input type=\"submit\" value=\"Edit\">");
					out.println("</form>");
					out.println("</td>");

					out.println("<td>");
					out.println("<form action=\"\" method=\"post\">");
					out.println("<input type=\"hidden\" name=\"customer_id\" value=\""
							+ customer.getId() + "\">");
					out.println("<input type=\"hidden\" name=\"action\" value=\"delete\">");
					out.println("<input type=\"submit\" value=\"Delete\">");
					out.println("</form>");
					out.println("</td>");

					out.println("</tr>");
				}
			%>
		</tbody>
	</table>
	<p>
		&raquo; <a
			href="<%=request.getContextPath() + "/customer/add_customer.jsp"%>">Add
			Customer</a>
	</p>
</body>
</html>