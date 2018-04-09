package EOfilm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DashboardFunctions
 */
public class dashboardFunctions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dashboardFunctions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();
		dbFunctions db = new dbFunctions();
		try {
			db.make_connection("jdbc:mysql://localhost:3306/moviedb", "root", "123456");

			String submitValue = request.getParameter("submit");
			if ("Add Star".equals(submitValue)) {
				star starToInsert = new star();
				starToInsert.setBirthYear(Integer.parseInt(request.getParameter("birthYear").trim()));
				String fullName = request.getParameter("name").trim();
				starToInsert.setName(fullName);
				sess.setAttribute("StarAdded", starToInsert);
				sess.setAttribute("fromStarAdd", db.insertStar(starToInsert));
			}
			else if ("Provide the metadata of the database".equals(submitValue)) {
				sess.setAttribute("metaData", db.get_metadata());
			}
			else if ("Add Movie".equals(submitValue)) {
				movie newMovie = new movie();
				newMovie.setTitle(request.getParameter("title"));
				newMovie.setDirector(request.getParameter("director"));
				newMovie.setYear(Integer.parseInt(request.getParameter("year")));
				star starToInsert = new star();
				starToInsert.setBirthYear(Integer.parseInt(request.getParameter("star_birthYear").trim()));				 			
				starToInsert.setName(request.getParameter("star_name").trim());
				String genre = request.getParameter("genre");
				String outmsg = db.addMovieViaStoredProceduce(newMovie, starToInsert, genre);
				sess.setAttribute("AddResult", outmsg);
			}
		} catch (Exception e) {
			sess.setAttribute("AddResult", "No changes made. Please Verify your input.");
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/_dashboard/Main");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		db.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}