<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="EOfilm.*,java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse Movie </title>
<%@ include file="header.jsp" %>
</head>
<body>
	<%
	
		session.setAttribute("movie_list", null);
		session.setAttribute("curSearch", null);
	%>
	<p></p>
	<div class="atoz-content">
		Browse by Title
		<div class="atoz-letter">
			<%
				for (int i = 0; i < 10; i++) {
			%>
			<a href="run_search?browse=1&amp;title=<%=i%>"><%=i%></a>&nbsp;
			<%
				}
				for (char i = 'A'; i <= 'Z'; i++) {
			%>
			<a href="run_search?browse=1&amp;title=<%=i%>"><%=i%></a>&nbsp;
			<%
				}
			%>
		</div>
	</div>
	<p></p>
	<div class="genre_list-content">
		Browse by Genre
		<div class="genre_list">
			<%
				ArrayList<String> genreList = dbConnection.getGenreList();
				for (String currentGenre : genreList) {
			%>
			<a href="run_search?browse=1&amp;genre=<%=currentGenre%>"><%=currentGenre%></a>&nbsp;
			<%
				}
			%>
		</div>
	</div>
</body>

<%@ include file="footer.jsp" %>

</html>