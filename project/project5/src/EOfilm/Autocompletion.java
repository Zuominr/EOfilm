package EOfilm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Autocompletion
 */
public class Autocompletion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autocompletion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		dbFunctions conn = new dbFunctions();
		List<String> m_tiles = new ArrayList<String>();
		List<String> s_tiles = new ArrayList<String>();
		
		try {
			JsonArray jsonArray = new JsonArray();
			conn.make_connection("jdbc:mysql://localhost:3306/moviedb", "root", "123456");
			String query = (String)request.getParameter("query");
			
			if (query == null || query.trim().isEmpty()) {
				response.getWriter().write(jsonArray.toString());
				return;
			}	
			if (query.length() < 3 || query.trim().length() < 3) {
				response.getWriter().write(jsonArray.toString());
				return;
			}	
			
			m_tiles =  conn.getTtiles(query);
			s_tiles =  conn.getStarNames(query);
			int i=1;
			int j=1;
				for(String mtitle: m_tiles )
			    { 
					jsonArray.add(generateJsonObject(i,mtitle, "Movie"));
					i++;
			    }
	
	
				for(String stitle: s_tiles )
			    { 
					jsonArray.add(generateJsonObject(j,stitle, "Star"));
					j++;
			    }
			
			response.getWriter().write(jsonArray.toString());
			return;
		} catch (Exception e) {
			System.out.println(e);
			response.sendError(500, e.getMessage());
		}
		
		  conn.close();
	}
	
	/*
	 * Generate the JSON Object from hero and category to be like this format:
	 * {
	 *   "value": "Iron Man",
	 *   "data": { "category": "marvel", "heroID": 11 }
	 * }
	 * 
	 */
	private static JsonObject generateJsonObject(Integer heroID, String heroName, String categoryName) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("value", heroName);
		
		JsonObject additionalDataJsonObject = new JsonObject();
		additionalDataJsonObject.addProperty("category", categoryName);
		additionalDataJsonObject.addProperty("heroID", heroID);
		
		jsonObject.add("data", additionalDataJsonObject);
		return jsonObject;
	}


}
