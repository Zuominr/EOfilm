<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="EOfilm.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Star Details</title>
<%@ include file="header.jsp" %>
</head>
<body>
	<%
		String movieid = null;
		String full_name = null;
		try {
			movieid = request.getParameter("movieid");
			full_name = request.getParameter("star_name");
		} catch (Exception e) {
			movieid = null;
			full_name = null;
		}

		if (movieid != null && full_name != null) {
			star star = dbConnection.getStarFromMovieIdAndName(movieid, full_name);

			String star_full_name = star.getName();
			Integer star_birthyear = star.getBirthYear();
	%>
	<table border="2">

		<tr>
			<td>Name:</td>
			<td><%=star_full_name%></td>

		</tr>
		<tr>
			<td>Date of Birth:</td>
			<td><%=star_birthyear%></td>
		</tr>
		<tr>
			<td>ID:</td>
			<td><%=star.getId()%></td>
		</tr>
		<tr>
			<td>Starred In:</td>
			<td>
				<%
					for (Map.Entry<String, String> pair : star.getMovies().entrySet()) {
				%> <a href="getMovie?movieid=<%=pair.getKey()%>"><%=pair.getValue()%></a><br>
				<%
					}
				%>
			</td>
		</tr>
	</table>
	<%
		}

		else {
	%>
	<h2>No Star Found</h2>
	<%
		}
	%>
</body>
<%@ include file="footer.jsp" %>
</html>