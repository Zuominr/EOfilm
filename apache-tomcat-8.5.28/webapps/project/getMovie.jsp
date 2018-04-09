<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="EOfilm.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie Details</title>
<%@ include file="header.jsp" %>
<%! @SuppressWarnings("unchecked") %>
</head>
<body>
	<%
		String movieid = null;
		try {
			
			movieid = request.getParameter("movieid");
		} catch (Exception e) {
			movieid = null;
		}

		if (movieid != null) {
			LinkedHashMap<String, movie> movie_list = (LinkedHashMap<String, movie>) session.getAttribute("movie_list");
			movie movieToDispay;
			if (movie_list != null && movie_list.containsKey(movieid)) {
				movieToDispay = movie_list.get(movieid);
			} else {
				movieToDispay = dbConnection.returnMovieFromID(movieid);
			}
			if (movieToDispay != null) {
	%>
	<table border="2" align="center">

		<tr>
			<td>Title:</td>
			<td><%=movieToDispay.getTitle()%></td>
		</tr>
		<tr>
			<td>Year:</td>
			<td><%=movieToDispay.getYear()%></td>
		</tr>
		<tr>
			<td>Director:</td>
			<td><%=movieToDispay.getDirector()%></td>
		</tr>
		<tr>
			<td>ID:</td>
			<td><%=movieid%></td>
		</tr>
		<tr>
			<td>Stars:</td>
			<td>
				<%
					int i = 1;
							for (String star : movieToDispay.getStars()) {
				%> <a
				href="getStar?movieid=<%=movieToDispay.getId()%>&amp;star_name=<%=star%>"><%=star%></a>
				<%
					if (!(i == movieToDispay.getStars().size())) {
									out.print(", ");
								}
								++i;
							}
				%>
			</td>
		</tr>
		<tr>
			<td>Genre:</td>
			<td>
				<%
				int j = 1;
							for (String Genre : movieToDispay.getGenres()) {
				%><a href="run_search?browse=1&amp;movieid=<%=movieToDispay.getId()%>&amp;genre=<%=Genre%>"><%=Genre%></a>&nbsp;
				<% 
				if (!(j == movieToDispay.getGenres().size())) {
					out.print(", ");
				}
				++j;
			}
							
				%>
				
			</td>
		</tr>
		<tr>
			<td>Price:</td>
			<td>$<%=dbConnection.getMoviePrice(movieid)%></td>
			<td><a href="cartservlet?movie_id=<%=movieid%>">Add To Cart</a></td>
		</tr>

	</table>
	<%
		}

			else {
	%>
	<h2>
		No Movie To Display with id =<%=movieid%></h2>
	<%
		}
		} else {
	%>
	<h2>No movie id provided</h2>
	<%
		}
	%>
</body>
<%@ include file="footer.jsp" %>
</html>