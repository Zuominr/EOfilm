<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="EOfilm.*"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Movie</title>
</head>
<body>

	<%
		user userToLogin = (user) session.getAttribute("empToken");
		if (userToLogin == null) {
			response.sendRedirect( request.getContextPath()+ "/_dashboard");
			userToLogin = new user();
		}
	%>
	<form action="dashboardFunctions" method="post">
		<table>
			<tr>
				<td>Movie Title(required):</td>
				<td><input type="text" name="title" required><br></td>
			</tr>
			<tr>
				<td>Director(required):</td>
				<td><input type="text" name="director" required><br></td>
			</tr>
			<tr>
				<td>Year(required):</td>
				<td><input type="number" name="year" required><br></td>
			</tr>
			<tr>
				<td>Star Name:</td>
				<td><input type="text" name="star_name" /><br></td>
			</tr>
			<tr>
				<td>Star BirthYear(xxxx):</td>
				<td><input type="number" name="star_birtYear" /><br></td>
			</tr>
			<tr>
				<td>Movie Genre:</td>
				<td><input type="text" name="genre" /><br></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" name="submit" value="Add Movie"></td>
			</tr>
		</table>
	</form>

</body>
</html>