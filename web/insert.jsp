<%-- 
    Document   : insert
    Created on : May 12, 2021, 5:05:03 PM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.USER ne null }">
    <c:redirect url="LogoutServlet"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
    </head>
    <body>
        <h1>Register</h1>
        <c:set var="error" value="${requestScope.UsersError}"/>
        <form action="DispatchServlet">
            UserID: <input type="text" name="userID" value="" required=""/><br/> 
            <p style="color: red">${error.userIDError}</p><br/>
            Password: <input type="password" name="password" value="" required=""/><br/>
            ${error.passwordError}<br/>
            Confirm: <input type="password" name="confirm" value="" required=""/><br/>
            ${error.confirm}<br/>
            Phone: <input type="text" name="phone" value="" required=""/><br/>
            ${error.phoneError}<br/>
            Name: <input type="text" name="name" value="" required=""/><br/>
            ${error.nameError}<br/>
            Address: <input type="text" name="address" value="" required=""/><br/>
            ${error.addressError}<br/>
            <input type="submit" value="Insert" name="btAction"/>
            <input type="reset" value="Reset" /><br/>
            <a href="login.jsp">Login Page</a>
        </form>
    </body>
</html>
