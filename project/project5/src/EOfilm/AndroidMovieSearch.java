package EOfilm;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONObject;

import java.util.*;


@SuppressWarnings("serial")
public class AndroidMovieSearch extends HttpServlet 
{
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
    {
	  dbFunctions movie_search = new dbFunctions();
	  try {
		movie_search.make_connection("jdbc:mysql://localhost:3306/moviedb", "root", "123456");
		//searchparameters curSearch = new searchparameters();

		JSONObject json = new JSONObject();
		//ArrayList<Movie> search_res = movie_search.getmovieByTilte(0, 1000, request.getParameter("title"));
		Integer counter= 0;
		
		List<String> search_res = movie_search.getbyoffset(request.getParameter("title"), Integer.parseInt(request.getParameter("pagenum")));
		String key = null;
		for(String mov: search_res)
		  {
		   movie movie_res = movie_search.getmovieinfo(0, 1000, mov);
		   String movieinfo = movie_res.getTitle() + " || " + movie_res.getYear() + " || " + movie_res.getDirector() + " || ";
		            for(String genre : movie_res.getGenres()) {
		             movieinfo += genre + ", ";
		            }
		            movieinfo += " || ";
		            for(String star : movie_res.getStars()) {
		             movieinfo += star + ", ";
		            }
			key = "movie" + counter.toString();
			json.put(key, movieinfo);
			++counter;
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    }
}