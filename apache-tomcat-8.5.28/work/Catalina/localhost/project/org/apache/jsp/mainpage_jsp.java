/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.28
 * Generated at: 2018-03-01 06:46:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import EOfilm.*;
import EOfilm.*;
import java.util.*;
import java.text.*;
import EOfilm.*;
import java.util.ArrayList;

public final class mainpage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/footer.jsp", Long.valueOf(1518496920000L));
    _jspx_dependants.put("/header.jsp", Long.valueOf(1518496920000L));
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

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Welcome to EOfilm</title>\r\n");
      out.write('\r');
      out.write('\n');

	dbFunctions dbConnection = new dbFunctions();
	dbConnection.make_connection("jdbc:mysql://localhost:3306/moviedb", "root", "123456");

      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("body{\r\n");
      out.write("background-image:url(resources/backg.jpg);\r\n");
      out.write("}\r\n");
      out.write("a:link {color:#990099;}\r\n");
      out.write("a:visited {color:red;}\r\n");
      out.write("a:hover {background:#FFDD55;}\r\n");
      out.write("a:hover {font-size:130%;}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<div class=\"logo_area\">\r\n");
      out.write("\t<div style=\"float: left\">\r\n");
      out.write("\r\n");
      out.write("\t\t<a href=\"mainpage\"><img src=\"resources/logo.png\" height=\"42\" width=\"42\" alt=\"\"> EOfilm</a> \r\n");
      out.write("\t\t<a href=\"search\">Search</a> \r\n");
      out.write("\t\t<a href=\"browse\">Browse</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div style=\"float: right\">\r\n");
      out.write("\t\t");

			if (session.getAttribute("userToken") == null) {
		
      out.write("\r\n");
      out.write("\t\t<a href=\"login\">Login</a>\r\n");
      out.write("\t\t");

			String url = request.getRequestURL().toString();
				if (!url.toLowerCase().contains("login")) {
					request.getSession().setAttribute("error", "mustlogin");
					response.sendRedirect("login");
				}
			} 
			else {
				user currentUser = (user) session.getAttribute("userToken");
		
      out.write("\r\n");
      out.write("\t\t");
      out.print(currentUser.getFirst_name() + "  " + currentUser.getLast_name());
      out.write("\r\n");
      out.write("\t\t( <a href=\"loginhandle?logout=1\"> Logout </a> ) <a href=\"cart\">\r\n");
      out.write("\t\t\tCart </a> (<a href=\"checkout\">Checkout</a>)\r\n");
      out.write("\t\t");

			}
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div style=\"clear: both;\"></div>\r\n");
      out.write("<hr>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");

		user userToLogin = (user) session.getAttribute("userToken");
		if (userToLogin == null) {
			userToLogin = new user();
		}
	
      out.write("\r\n");
      out.write("\t<div align=\"center\">\r\n");
      out.write("\t\t<table>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=\"35\">&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t<div align=\"center\">\r\n");
      out.write("\t\t\t\t\t\tHello <b>");
      out.print(userToLogin.getFirst_name());
      out.write("!</b> <br>Welcome to\r\n");
      out.write("\t\t\t\t\t\t\tEOfilm!\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=\"35\">&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t<form action=\"search\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"Search the movie Database\">\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=\"35\">&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>\r\n");
      out.write("\t\t\t\t\t<form action=\"browse\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"submit\" value=\"Browse the movie Database\">\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<hr>\r\n");
      out.write("<div id=\"footer\" align=\"right\">\r\n");
      out.write("My CS122B Web Project.   Designed by Zuomin Ren and Rui Cao.\r\n");
      out.write("</div>\r\n");

	//dbConnection.close();

      out.write("\r\n");
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