<%-- 
    Document   : verify
    Created on : May 15, 2021, 10:07:19 PM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.USER.role ne null}">
    <c:redirect url="LogoutServlet"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
    </head>
    <body>
        <h1>Page Verify </h1>
        <h2>Welcome, ${sessionScope.NEWUSER.name}</h2>
        <form action="DispatchServlet">
            Code Verify: <input type="text" name="CodeVerify" value="" required=""/>
            <input type="submit" value="CheckVerify" name="btAction" />
        </form>
        <p style="color: green">${requestScope.REPORT_VERIFY}</p>
        <p style="color: red">${requestScope.REPORT_VERIFY_ERROR}</p>
        <a href="login.jsp">Login Page</a>
    </body>
</html>
