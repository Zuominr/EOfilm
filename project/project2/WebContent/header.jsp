<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="EOfilm.*, java.util.*, java.text.*"%>
<%
	dbFunctions dbConnection = new dbFunctions();
	dbConnection.make_connection("jdbc:mysql://localhost:3306/moviedb", "root", "123456");
%>
<style>
body{
background-image:url(resources/backg.jpg);
}
a:link {color:#990099;}
a:visited {color:red;}
a:hover {background:#FFDD55;}
a:hover {font-size:130%;}
</style>

<div class="logo_area">
	<div style="float: left">

		<a href="mainpage"><img src="resources/logo.png" height="42" width="42" alt=""> EOfilm</a> 
		<a href="search">Search</a> 
		<a href="browse">Browse</a>
	</div>
	<div style="float: right">
		<%
			if (session.getAttribute("userToken") == null) {
		%>
		<a href="login">Login</a>
		<%
			String url = request.getRequestURL().toString();
				if (!url.toLowerCase().contains("login")) {
					request.getSession().setAttribute("error", "mustlogin");
					response.sendRedirect("login");
				}
			} 
			else {
				user currentUser = (user) session.getAttribute("userToken");
		%>
		<%=currentUser.getFirst_name() + "  " + currentUser.getLast_name()%>
		( <a href="loginhandle?logout=1"> Logout </a> ) <a href="cart">
			Cart </a> (<a href="checkout">Checkout</a>)
		<%
			}
		%>

	</div>
</div>

<div style="clear: both;"></div>
<hr>
