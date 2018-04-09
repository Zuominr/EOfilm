<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="EOfilm.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search for a movie</title>
<%@ include file="header.jsp" %>
</head>
<body>
	<center>
		<h3>Search</h3>
	</center>

	<%
		session.setAttribute("movie_list", null);
		session.setAttribute("curSearch", null);
	%>

	<form action="run_search" method="get">
		Title: <input type="text" name="title"/><br> 
		Year: <input type="text" name="year"/><br> 
		Director: <input type="text" name="director"/><br> 
		Star's name: <input type="text" name="starName"/><br> 
		<input type="submit" name="submit"value="Search">
	</form>
</body>
<%@ include file="footer.jsp" %>
</html>