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

<base href="<%=request.getContextPath()%>/">

 <!-- Using jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- include jquery autocomplete JS  -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.7/jquery.autocomplete.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/style.css" />

<div class="logo_area">
	<div style="float: left">

		<a href="mainpage"><img src="resources/logo.png" height="42" width="42" alt=""> EOfilm</a> 
		<a href="search">Advanced Search</a> 
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



<br>

<div class="search_bar_area" style="float: right">
<form name="search_bar_form" action="run_search" method="get">
	Search:<input id="autocomplete" type="text" autocomplete="off" name="title"></input>
	<input type="submit" name="submit"	value="Search">
	<script type="text/javascript" src="js/ajaxStuff.js"></script>
</form>
</div>



<div style="clear: both;"></div>
<hr>
