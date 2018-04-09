<%@page
	import="java.sql.*,
 javax.sql.*,
 java.util.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="EOfilm.*"%>

<style type="text/css">
#form_div {
	text-align: center;
}
</style>
<html>
<head>
<title>Display Movie Titles</title>
<%@ include file="header.jsp" %>
<%!@SuppressWarnings("unchecked")%>
</head>
<body>
	<h3>Movie List</h3>
	<%
		searchparameters curSearch = (searchparameters) session.getAttribute("curSearch");
		if (curSearch.getFromBrowse()) {
			if (curSearch.getByTitle()) {
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
	<p>
		<%
			} else {
		%>

	</p>
	<p></p>
	<div class="genre_list-content">
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
	<%
		}
		}
	%>
	Results shown per page:
	<%=curSearch.getMoviePerPage()%>
	<form action="run_search" method="get">
		Select Amount of results shown per page: <select
			name="movies_per_page" onchange="this.form.submit()">
			<option selected="selected" disabled="disabled">Select a
				value</option>
			<option value="10">10</option>
			<option value="25">25</option>
			<option value="50">50</option>
			<option value="100">100</option>
		</select>
	</form>

	<table border="1">
		<tr>
			<th>Id</th>
			<th><a href="run_search?sort_type=title">Title</a></th>
			<th><a href="run_search?sort_type=year">Year</a></th>
			<th>Director</th>
			<th>Genres</th>
			<th>Stars</th>
			<th>Add</th>
		</tr>

		<%
			LinkedHashMap<String, movie> movie_list = (LinkedHashMap<String, movie>) session
					.getAttribute("movie_list");

			for (String id : movie_list.keySet()) {
				movie movie = movie_list.get(id);
		%>
		<tr>
			<td><%=id%></td>
			<td><a href="getMovie?movieid=<%=id%>"><%=movie.getTitle()%></a></td>
			<td><%=movie.getYear()%></td>
			<td><%=movie.getDirector()%></td>
			<td>
				<%
					String outputString = "";
						for (String genre : movie.getGenres()) {
							outputString += genre + ", ";
						}
						if (outputString.contains(","))
							outputString = outputString.substring(0, outputString.length() - 2);
						out.print(outputString);
				%>
			</td>
			<td>
				<%
					outputString = "";

						int i = 1;
						for (String star : movie.getStars()) {
				%> <a href="getStar?movieid=<%=id%>&amp;star_name=<%=star%>"><%=star%></a>
				<%
					if (!(i == movie.getStars().size())) {
								out.print(", ");
							}
							++i;
						}
				%>
			</td>
			<td><a href="cartservlet?movie_id=<%=id%>">Add To Cart</a></td>
		</tr>

		<%
			}
		%>
	</table>

	<%
		int page_number = Integer.parseInt(curSearch.getCurrentPage());
		if (page_number > 0) {
	%>
	<a href="run_search?page_number=<%=page_number - 1%>">Prev</a>
	<%
		}
	%>
	<p>
		Current Page :<%=page_number + 1%></p>
	<p>
		<%
			if (movie_list.size() >= Integer.parseInt(curSearch.getMoviePerPage())) {
		%>
		<a href="run_search?page_number=<%=page_number + 1%>">Next</a>
		<%
			}
		%>
	</p>
</body>
<%@ include file="footer.jsp" %>
</html>
