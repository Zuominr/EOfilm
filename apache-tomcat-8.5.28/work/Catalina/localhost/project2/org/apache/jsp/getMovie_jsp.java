/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.28
 * Generated at: 2018-03-01 06:40:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import EOfilm.*;
import java.util.*;
import EOfilm.*;
import java.util.*;
import java.text.*;
import EOfilm.*;
import java.util.ArrayList;

public final class getMovie_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

 @SuppressWarnings("unchecked") 
  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/footer.jsp", Long.valueOf(1518221290000L));
    _jspx_dependants.put("/header.jsp", Long.valueOf(1518220234000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("java.text");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("EOfilm");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<title>Movie Details</title>\n");
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
      out.write("<div class=\"logo_area\">\n");
      out.write("\t<div style=\"float: left\">\n");
      out.write("\n");
      out.write("\t\t<a href=\"mainpage\"><img src=\"resources/logo.png\" height=\"42\" width=\"42\" alt=\"\"> EOfilm</a> \n");
      out.write("\t\t<a href=\"search\">Search</a> \n");
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
      out.write("<div style=\"clear: both;\"></div>\n");
      out.write("<hr>\n");
      out.write('\n');
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t");

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
	
      out.write("\n");
      out.write("\t<table border=\"2\" align=\"center\">\n");
      out.write("\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<td>Title:</td>\n");
      out.write("\t\t\t<td>");
      out.print(movieToDispay.getTitle());
      out.write("</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<td>Year:</td>\n");
      out.write("\t\t\t<td>");
      out.print(movieToDispay.getYear());
      out.write("</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<td>Director:</td>\n");
      out.write("\t\t\t<td>");
      out.print(movieToDispay.getDirector());
      out.write("</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<td>ID:</td>\n");
      out.write("\t\t\t<td>");
      out.print(movieid);
      out.write("</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<td>Stars:</td>\n");
      out.write("\t\t\t<td>\n");
      out.write("\t\t\t\t");

					int i = 1;
							for (String star : movieToDispay.getStars()) {
				
      out.write(" <a\n");
      out.write("\t\t\t\thref=\"getStar?movieid=");
      out.print(movieToDispay.getId());
      out.write("&amp;star_name=");
      out.print(star);
      out.write('"');
      out.write('>');
      out.print(star);
      out.write("</a>\n");
      out.write("\t\t\t\t");

					if (!(i == movieToDispay.getStars().size())) {
									out.print(", ");
								}
								++i;
							}
				
      out.write("\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<td>Genre:</td>\n");
      out.write("\t\t\t<td>\n");
      out.write("\t\t\t\t");

				int j = 1;
							for (String Genre : movieToDispay.getGenres()) {
				
      out.write("<a href=\"run_search?browse=1&amp;movieid=");
      out.print(movieToDispay.getId());
      out.write("&amp;genre=");
      out.print(Genre);
      out.write('"');
      out.write('>');
      out.print(Genre);
      out.write("</a>&nbsp;\n");
      out.write("\t\t\t\t");
 
				if (!(j == movieToDispay.getGenres().size())) {
					out.print(", ");
				}
				++j;
			}
							
				
      out.write("\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t<tr>\n");
      out.write("\t\t\t<td>Price:</td>\n");
      out.write("\t\t\t<td>$");
      out.print(dbConnection.getMoviePrice(movieid));
      out.write("</td>\n");
      out.write("\t\t\t<td><a href=\"cartservlet?movie_id=");
      out.print(movieid);
      out.write("\">Add To Cart</a></td>\n");
      out.write("\t\t</tr>\n");
      out.write("\n");
      out.write("\t</table>\n");
      out.write("\t");

		}

			else {
	
      out.write("\n");
      out.write("\t<h2>\n");
      out.write("\t\tNo Movie To Display with id =");
      out.print(movieid);
      out.write("</h2>\n");
      out.write("\t");

		}
		} else {
	
      out.write("\n");
      out.write("\t<h2>No movie id provided</h2>\n");
      out.write("\t");

		}
	
      out.write("\n");
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
      out.write("</html>");
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