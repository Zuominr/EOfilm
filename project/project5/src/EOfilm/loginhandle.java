package EOfilm;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

/**
 * Servlet implementation class LoginHandler
 */

public class loginhandle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//public static final String SECRET_KEY = "6LdNXkcUAAAAAMOjY9jJ8qMjJWs5POqkeXnFUUCA";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginhandle() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{	
			
			dbFunctions dbConnection = new dbFunctions();
			
			dbConnection.make_connection("jdbc:mysql://localhost:3306/moviedb", "root", "123456");
			
			user userToLogin = (user) request.getSession().getAttribute("userToken");
			//String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		//	boolean notABot = verifyRecaptcha(gRecaptchaResponse);
			if (request.getParameter("logout") != null)
			{
				request.getSession().invalidate();
				response.sendRedirect("login");
			}
			//else if(!notABot)
			//{
			//	request.getSession().setAttribute("error", "reCAPTHAFailed");
			//	response.sendRedirect("login");
			//}
			
			else if (request.getParameter("username") != null) 
			{
				if(userToLogin != null)
				{
					request.getSession().invalidate();
				}
				userToLogin = dbConnection.loginToEOfilm(request.getParameter("username"),
						request.getParameter("pass"));
				
				if (userToLogin == null) {
					request.getSession().setAttribute("error", "loginerror");
					response.sendRedirect("login");
					 
				}else
				{
					request.getSession().setAttribute("userToken", userToLogin);
					response.sendRedirect("mainpage");
				}
			}
			
			dbConnection.close();
			
		}
		
		catch (Exception ex)
		{
			ex.printStackTrace();
			request.getSession().setAttribute("error", "loginerror");
			request.getSession().setAttribute("userToken", null);
			response.sendRedirect("login");
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/*
	public boolean verifyRecaptcha(String captchaResponse)
	{
		if (captchaResponse == null || captchaResponse.length() == 0) {
            return false;
        }
 
        try {
            URL verifyUrl = new URL("https://www.google.com/recaptcha/api/siteverify");
 
            // Open Connection to URL
			HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();
 
  
            // Add Request Header
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
 
            // Data will be sent to the server.
            String postParams = "secret=" + SECRET_KEY + "&response=" + captchaResponse;
 
            // Send Request
            conn.setDoOutput(true);
            
            // Get the output stream of Connection
            // Write data in this stream, which means to send data to Server.
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());
 
            outStream.flush();
            outStream.close();
 
            // Response code return from server.
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode=" + responseCode);           
 
  
            // Get the InputStream from Connection to read data sent from the server.
            InputStream is = conn.getInputStream();
 
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
 
            // ==> {"success": true}
            System.out.println("Response: " + jsonObject);
 
            boolean success = jsonObject.getBoolean("success");
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	*/
}