<%@ 
page import="java.sql.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*"%>
    
<style type="text/css">
h2 {text-align:center;}
h3 {text-align:center;color:red;}
#form_div {
	text-align: center;
}

</style>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login to EOfilm</title>
<script src='https://www.google.com/recaptcha/api.js'></script>
<%@ include file="header.jsp" %>
</head>
<body>

<h2>EOfilm Login</h2>
	<%
		String error = (String) request.getSession().getAttribute("error");
		if ("loginerror".equals(error)) {
			session.setAttribute("error", null);
	%>
	<h3>Invalid User name or password</h3>
	<%
		} 
		else if ("mustlogin".equals(error)) {
			session.setAttribute("error", null);
	%>
	<h3>You must login to access this site.</h3>
	<%
		}
		else if ("reCAPTHAFailed".equals(error)) {
			session.setAttribute("error", null);
	%>
	<h3>You must verify that you are not a robot to login.</h3>
	<%
		}
	%>
	<div align="center">
		<fieldset style="width: 500px" class="field">
			<legend>Existing Member Sign In:</legend>

			<div id="form_div">
				<form action="loginhandle" method="POST">
					<table style="font-size: small">
						<tr>
							<td width="35">&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="35">&nbsp;</td>
							<td width="100">User name(email address): <input type="text"
								name="username" /></td>
						</tr>
						<tr>
							<td width="35"><div class="tinyspacer">&nbsp;</div></td>
							<td width="100"><div class="tinyspacer">&nbsp;</div></td>
							<td><div class="tinyspacer">&nbsp;</div></td>
						</tr>
						<tr>
							<td width="35">&nbsp;</td>
							<td width="100"><div align="left">
									Password: <input type="password" name="pass" />
								</div></td>
							<td></td>
						</tr>
						<tr>
							<td width="35">&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<input type="submit" name="login" value="login">
					<div class="g-recaptcha" data-sitekey="6LfajUcUAAAAAAfJaqgDdyY12qSjW9uIyV_XLWO2"></div>
				</form>
			</div>
		</fieldset>
		&nbsp;<br />
	</div>
</body>
<%@ include file="footer.jsp" %>
</html>