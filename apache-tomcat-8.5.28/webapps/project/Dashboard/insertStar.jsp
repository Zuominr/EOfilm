<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="EOfilm.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Star</title>
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
		Star Name: <input type="text" name="name" required><br> BirthYear(xxxx): <input type="number" name="birthYear" required><br>
		 <input type="submit" name="submit" value="Add Star">
	</form>

</body>
</html>