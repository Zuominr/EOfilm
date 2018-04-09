<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="EOfilm.*" import="java.util.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search for a Movie</title>
<%@ include file="header.jsp" %>
</head>

<body>
	<center>
		<h3>Shopping Cart</h3>
	</center>
				<%
				cart shopping_cart = (cart) session.getAttribute("shopping_cart");
				if (shopping_cart == null || shopping_cart.get_item_count() == 0) 
				{
					shopping_cart = new cart();
				%>
				<h3>No Items in Cart</h3>
				<a href="search" style="margin-right:10px;">Return to Search</a><a href="browse">Return to Browse</a>
				<%
					session.setAttribute("shopping_cart", shopping_cart);
				}
				else{
				%>
	<div class="form">
		<table>
			<tr>
				<th>Movie Title</th>
				<th>Price</th>
				<th>Quantity</th>
			</tr>
			<%
			    HashMap<String, cartitem> basket = shopping_cart.getBasket();
				for (cartitem item : basket.values()) {
			%>
			<tr>
				<td><%=item.getMovieTitle()%></td>
				<td><%=item.getPrice()%></td>
				<td>
					<form action="cartservlet" name="cartOperation">
						<input type="hidden" name="movie_id" value="<%=item.getMovieID()%>" /> 
						<input type="text" value="<%=item.getQty()%>" name="qty" /> 
						<input type="submit" value="Update" name="cartOp"> 
						<input type="submit" value="Remove" name="cartOp">
					</form>
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			NumberFormat formatter = new DecimalFormat("#0.00");
			String tots = formatter.format(shopping_cart.get_total());
		%>
		<h3>
			Total: $<%=tots%></h3>
	</div>
	<a href="checkout.jsp" style="margin-right: 10px;">Checkout </a>
	<a href="cartservlet?cartOp=empty_cart">Empty Cart</a>
	<% }%>
</body>


<%@ include file="footer.jsp" %>
</html>