/* A servlet to display the contents of the MySQL movieDB database */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieList extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getServletInfo() {
        return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    // Use http GET

    @SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String loginUser = "root";
        String loginPasswd = "123456";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        response.setContentType("text/html"); // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>Top 20 Movies</TITLE></HEAD>");
        out.println("<BODY><H1>Top 20 Movies</H1>");

        try {
            //Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
            // Declare our statement
            Statement statement = dbcon.createStatement();

            String query = "select title, year, director, group_concat(distinct genres.name) as list_of_genres, group_concat(distinct stars.name) as list_of_stars, rating from movies, genres_in_movies, genres, stars_in_movies, stars, ratings where movies.id = genres_in_movies.movieId and genres_in_movies.genreId = genres.id and movies.id = stars_in_movies.movieId and stars_in_movies.starId = stars.id and movies.id = ratings.movieId group by movies.id, movies.title, movies.year, movies.director, rating order by rating desc limit 20;";

            // Perform the query
            ResultSet rs = statement.executeQuery(query);

            out.println("<TABLE border>");
            out.println("<tr>" + "<td>" + "Movie Title" + "</td>" + "<td>" + "Year" + "</td>"
                    + "<td>" + "Director" + "</td>" + "<td>" + "Genres" + "</td>" + "<td>" + "Movie Stars" + "</td>"  
                    + "<td>" + "Rating" + "</td>" + "</tr>");
            // Iterate through each row of rs
            int i=0;
            while(i<20 && rs.next()) {
                String m_t = rs.getString("title");
                String m_y = rs.getString("year");
                String m_d = rs.getString("director");
                String m_log = rs.getString("list_of_genres");
                String m_los = rs.getString("list_of_stars");
                String m_r = rs.getString("rating");
                
                out.println("<tr>" +
                            "<td>" + m_t + "</td>" + "<td>" + m_y + "</td>" + "<td>" + m_d + "</td>"
                            + "<td>" + m_log + "</td>"+ "<td>" + m_los + "</td>"+ "<td>" + m_r + "</td>"
                            + "</tr>");
                i++;
            }

            out.println("</TABLE>");

            rs.close();
            statement.close();
            dbcon.close();
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQL Exception:  " + ex.getMessage());
                ex = ex.getNextException();
            } // end while
        } // end catch SQLException

        catch (java.lang.Exception ex) {
            out.println("<HTML>" + "<HEAD><TITLE>" + "MovieDB: Error" + "</TITLE></HEAD>\n<BODY>"
                    + "<P>SQL error in doGet: " + ex.getMessage() + "</P></BODY></HTML>");
            return;
        }
        out.close();
    }
}
