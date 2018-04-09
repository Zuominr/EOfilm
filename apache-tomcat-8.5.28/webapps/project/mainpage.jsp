<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="EOfilm.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to EOfilm</title>
<%@ include file="header.jsp" %>
</head>
<body>
	<%
		user userToLogin = (user) session.getAttribute("userToken");
		if (userToLogin == null) {
			userToLogin = new user();
		}
	%>
	<div align="center">
		<table>
			<tr>
				<td width="35">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>
					<div align="center">
						Hello <b><%=userToLogin.getFirst_name()%>!</b> <br>Welcome to
							EOfilm!
					</div>
				</th>
			</tr>
			<tr>
				<td width="35">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>
					<form action="search">
						<input type="submit" value="Search the movie Database">
					</form>
				</th>
			</tr>
			<tr>
				<td width="35">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>
					<form action="browse">
						<input type="submit" value="Browse the movie Database">
					</form>
				</th>
			</tr>
		</table>
	</div>
</body>
<%@ include file="footer.jsp" %>
</html>