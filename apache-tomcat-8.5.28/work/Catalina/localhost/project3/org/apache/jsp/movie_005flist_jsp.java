/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.28
 * Generated at: 2018-03-10 19:34:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.*;
import EOfilm.*;
import EOfilm.*;
import java.util.*;
import java.text.*;
import EOfilm.*;
import java.util.ArrayList;

public final class movie_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

@SuppressWarnings("unchecked")
  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/footer.jsp", Long.valueOf(1519933646000L));
    _jspx_dependants.put("/header.jsp", Long.valueOf(1520642020000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("java.text");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.sql");
    _jspx_imports_packages.add("EOfilm");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.io.IOException");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<style type=\"text/css\">\n");
      out.write("#form_div {\n");
      out.write("\ttext-align: center;\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Display Movie Titles</title>\n");
      out.write('\n');

	dbFunctions dbConnection = new dbFunctions();
	dbConnection.make_connection("jdbc:mysql://localhost:3306/moviedb", "root", "123456");

      out.write("\n");
      out.write("<style>\n");
      out.write("body{\n");
      out.write("background-image:url(resources/backg.jpg);\n");
      out.write("}\n");
      out.write("a:link {color:#990099;}\n");
      out.write("a:visited {color:red;}\n");
      out.write("a:hover {background:#FFDD55;}\n");
      out.write("a:hover {font-size:130%;}\n");
      out.write("</style>\n");
      out.write("\n");
      out.write("<base href=\"");
      out.print(request.getContextPath());
      out.write("/\">\n");
      out.write("\n");
      out.write(" <!-- Using jQuery -->\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("\n");
      out.write("    <!-- include jquery autocomplete JS  -->\n");
      out.write("    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.7/jquery.autocomplete.min.js\"></script>\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />\n");
      out.write("\n");
      out.write("<div class=\"logo_area\">\n");
      out.write("\t<div style=\"float: left\">\n");
      out.write("\n");
      out.write("\t\t<a href=\"mainpage\"><img src=\"resources/logo.png\" height=\"42\" width=\"42\" alt=\"\"> EOfilm</a> \n");
      out.write("\t\t<a href=\"search\">Advanced Search</a> \n");
      out.write("\t\t<a href=\"browse\">Browse</a>\n");
      out.write("\t</div>\n");
      out.write("\t<div style=\"float: right\">\n");
      out.write("\t\t");

			if (session.getAttribute("userToken") == null) {
		
      out.write("\n");
      out.write("\t\t<a href=\"login\">Login</a>\n");
      out.write("\t\t");

			String url = request.getRequestURL().toString();
				if (!url.toLowerCase().contains("login")) {
					request.getSession().setAttribute("error", "mustlogin");
					response.sendRedirect("login");
				}
			} 
			else {
				user currentUser = (user) session.getAttribute("userToken");
		
      out.write('\n');
      out.write('	');
      out.write('	');
      out.print(currentUser.getFirst_name() + "  " + currentUser.getLast_name());
      out.write("\n");
      out.write("\t\t( <a href=\"loginhandle?logout=1\"> Logout </a> ) <a href=\"cart\">\n");
      out.write("\t\t\tCart </a> (<a href=\"checkout\">Checkout</a>)\n");
      out.write("\t\t");

			}
		
      out.write("\n");
      out.write("\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<br>\n");
      out.write("\n");
      out.write("<div class=\"search_bar_area\" style=\"float: right\">\n");
      out.write("<form name=\"search_bar_form\" action=\"run_search\" method=\"get\">\n");
      out.write("\tSearch:<input id=\"autocomplete\" type=\"text\" autocomplete=\"off\" name=\"title\"></input>\n");
      out.write("\t<input type=\"submit\" name=\"submit\"\tvalue=\"Search\">\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/ajaxStuff.js\"></script>\n");
      out.write("</form>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div style=\"clear: both;\"></div>\n");
      out.write("<hr>\n");
      out.write('\n');
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<h3>Movie List</h3>\n");
      out.write("\t");

		searchparameters curSearch = (searchparameters) session.getAttribute("curSearch");
		if (curSearch.getFromBrowse()) {
			if (curSearch.getByTitle()) {
	
      out.write("\n");
      out.write("\t<p></p>\n");
      out.write("\t<div class=\"atoz-content\">\n");
      out.write("\t\tBrowse by Title\n");
      out.write("\t\t<div class=\"atoz-letter\">\n");
      out.write("\t\t\t");

				for (int i = 0; i < 10; i++) {
			
      out.write("\n");
      out.write("\t\t\t<a href=\"run_search?browse=1&amp;title=");
      out.print(i);
      out.write('"');
      out.write('>');
      out.print(i);
      out.write("</a>&nbsp;\n");
      out.write("\t\t\t");

				}
						for (char i = 'A'; i <= 'Z'; i++) {
			
      out.write("\n");
      out.write("\t\t\t<a href=\"run_search?browse=1&amp;title=");
      out.print(i);
      out.write('"');
      out.write('>');
      out.print(i);
      out.write("</a>&nbsp;\n");
      out.write("\t\t\t");

				}
			
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<p>\n");
      out.write("\t\t");

			} else {
		
      out.write("\n");
      out.write("\n");
      out.write("\t</p>\n");
      out.write("\t<p></p>\n");
      out.write("\t<div class=\"genre_list-content\">\n");
      out.write("\t\t<div class=\"genre_list\">\n");
      out.write("\t\t\t");

			ArrayList<String> genreList = dbConnection.getGenreList();
						for (String currentGenre : genreList) {
			
      out.write("\n");
      out.write("\t\t\t<a href=\"run_search?browse=1&amp;genre=");
      out.print(currentGenre);
      out.write('"');
      out.write('>');
      out.print(currentGenre);
      out.write("</a>&nbsp;\n");
      out.write("\t\t\t");

				}
			
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t");

		}
		}
	
      out.write("\n");
      out.write("\tResults shown per page:\n");
      out.write("\t");
      out.print(curSearch.getMoviePerPage());
      out.write("\n");
      out.write("\t<form action=\"run_search\" method=\"get\">\n");
      out.write("\t\tSelect Amount of results shown per page: <select\n");
      out.write("\t\t\tname=\"movies_per_page\" onchange=\"this.form.submit()\">\n");
      out.write("\t\t\t<option selected=\"selected\" disabled=\"disabled\">Select a\n");
      out.write("\t\t\t\tvalue</option>\n");
      out.write("\t\t\t<option value=\"10\">10</option>\n");
      out.write("\t\t\t<option value=\"25\">25</option>\n");
      out.write("\t\t\t<option value=\"50\">50</option>\n");
      out.write("\t\t\t<option value=\"100\">100</option>\n");
      out.write("\t\t</select>\n");
      out.write("\t</form>\n");
      out.write("\n");
      out.write("\t<table border=\"1\">\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<th>Id</th>\n");
      out.write("\t\t\t<th><a href=\"run_search?sort_type=title\">Title</a></th>\n");
      out.write("\t\t\t<th><a href=\"run_search?sort_type=year\">Year</a></th>\n");
      out.write("\t\t\t<th>Director</th>\n");
      out.write("\t\t\t<th>Genres</th>\n");
      out.write("\t\t\t<th>Stars</th>\n");
      out.write("\t\t\t<th>Add</th>\n");
      out.write("\t\t</tr>\n");
      out.write("\n");
      out.write("\t\t");

			LinkedHashMap<String, movie> movie_list = (LinkedHashMap<String, movie>) session
					.getAttribute("movie_list");

			for (String id : movie_list.keySet()) {
				movie movie = movie_list.get(id);
		
      out.write("\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<td>");
      out.print(id);
      out.write("</td>\n");
      out.write("\t\t\t<td><a href=\"getMovie?movieid=");
      out.print(id);
      out.write('"');
      out.write('>');
      out.print(movie.getTitle());
      out.write("</a></td>\n");
      out.write("\t\t\t<td>");
      out.print(movie.getYear());
      out.write("</td>\n");
      out.write("\t\t\t<td>");
      out.print(movie.getDirector());
      out.write("</td>\n");
      out.write("\t\t\t<td>\n");
      out.write("\t\t\t\t");

					String outputString = "";
						for (String genre : movie.getGenres()) {
							outputString += genre + ", ";
						}
						if (outputString.contains(","))
							outputString = outputString.substring(0, outputString.length() - 2);
						out.print(outputString);
				
      out.write("\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t\t<td>\n");
      out.write("\t\t\t\t");

					outputString = "";

						int i = 1;
						for (String star : movie.getStars()) {
				
      out.write(" <a href=\"getStar?movieid=");
      out.print(id);
      out.write("&amp;star_name=");
      out.print(star);
      out.write('"');
      out.write('>');
      out.print(star);
      out.write("</a>\n");
      out.write("\t\t\t\t");

					if (!(i == movie.getStars().size())) {
								out.print(", ");
							}
							++i;
						}
				
      out.write("\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t\t<td><a href=\"cartservlet?movie_id=");
      out.print(id);
      out.write("\">Add To Cart</a></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\n");
      out.write("\t\t");

			}
		
      out.write("\n");
      out.write("\t</table>\n");
      out.write("\n");
      out.write("\t");

		int page_number = Integer.parseInt(curSearch.getCurrentPage());
		if (page_number > 0) {
	
      out.write("\n");
      out.write("\t<a href=\"run_search?page_number=");
      out.print(page_number - 1);
      out.write("\">Prev</a>\n");
      out.write("\t");

		}
	
      out.write("\n");
      out.write("\t<p>\n");
      out.write("\t\tCurrent Page :");
      out.print(page_number + 1);
      out.write("</p>\n");
      out.write("\t<p>\n");
      out.write("\t\t");

			if (movie_list.size() >= Integer.parseInt(curSearch.getMoviePerPage())) {
		
      out.write("\n");
      out.write("\t\t<a href=\"run_search?page_number=");
      out.print(page_number + 1);
      out.write("\">Next</a>\n");
      out.write("\t\t");

			}
		
      out.write("\n");
      out.write("\t</p>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<hr>\n");
      out.write("<div id=\"footer\" align=\"right\">\n");
      out.write("My CS122B Web Project.   Designed by Zuomin Ren and Rui Cao.\n");
      out.write("</div>\n");

	//dbConnection.close();

      out.write("\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
