<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="EOfilm.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search for a Movie</title>
<%@ include file="header.jsp" %>
</head>
<body>
	<center>
		<h3>Checkout</h3>
	</center>


<%
cart shopping_cart = (cart) session.getAttribute("shopping_cart");
if (shopping_cart == null || shopping_cart.get_item_count() == 0) {
	shopping_cart = new cart();
%>
	<h3>Nothing to checkout.  Your cart is empty.</h3>
	<a href="search" style="margin-right:10px;">Return to Search</a><a href="browse">Return to Browse</a>
<% } else {%>
	<h1>Enter Credit Card Information</h1>
	<form action="checkoutservlet" method="post">
		First Name: <input type="text" name="first_name" /><br> 
		Last Name: <input type="text" name="last_name" /><br> 
		CreditCard Number: <input type="text" name="cc" /><br> 
		Expiration Date: <input type="text" name="exp" /><br> <input type="submit" name="submit">
	</form>
<% }%>
</body>
<%@ include file="footer.jsp" %>
</html>