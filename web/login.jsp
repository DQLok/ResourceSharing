<%-- 
    Document   : login
    Created on : May 11, 2021, 8:58:04 AM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <!-- reCAPTCHA with Auto language -->
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head>
    <body>
        <h1>Welcome Login Page</h1>
        <h4 style="color: red">${requestScope.errorString}</h4><br/>
        <form action="DispatchServlet" method="POST">            
            UserID: <input type="text" name="userID" value="" required=""/>
            Password: <input type="password" name="password" value="" required=""/>
            <div class="g-recaptcha"
                 data-sitekey="6LeT3M8aAAAAAK-5nZuOHZydqVXZ0CnIukZTd39m"></div><br/>            
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />            
            <a href="insert.jsp">Register Account</a>
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/J3.L.P0016/LoginGoogleServlet&response_type=code
               &client_id=135679939540-eab6571ntj3v94bdvn9pd03mv29orrj1.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>              
        </form>
    </body>
</html>
