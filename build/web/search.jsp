<%-- 
    Document   : search
    Created on : May 11, 2021, 9:40:10 AM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.USER eq null }">
    <c:redirect url="LogoutServlet"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <h1>List Resource Sharing </h1>
        <h2>Welcome,
            <c:choose>
                <c:when test="${user.role eq 'admin'}">
                    Admin <a href="AdminServlet">${user.name }</a> 
                </c:when>
                <c:when test="${user.role eq 'user'}">
                    <a href="ProfileServlet?userID=${user.userID}">${user.name }</a> 
                </c:when>
            </c:choose>
        </h2>        
        <h3> <a href="DispatchServlet?btAction=Logout">Logout</a> </h3>
        <h4 style="color: blue">Total: ${sessionScope.COUNTRESOURCE}</h4>
        <form action="DispatchServlet">
            Category:<select name="category">
                <option value="everything">Everything</option>
                <c:forEach var="listcategory" items="${sessionScope.LISTCATEGORY}">
                    <option value="${listcategory.categoryID}"${requestScope.CATEGORY eq listcategory.categoryID ? "selected":""}>${listcategory.categoryname}</option>                   
                </c:forEach>                
            </select>
            Date: <input type="date" name="dateresoure" value="${requestScope.DATE}"/>
            Search: <input type="text" name="txtSearchValue" value="${requestScope.SEARCHVALUE}" />
            <input type="submit" value="Search" name="btAction"/>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Color</th>
                    <th>Category Name</th>
                    <th>Quantity</th>
                    <th>Date Up Resource</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach begin="1" end="${sessionScope.ENDPAGE}" var="i">
                <a href="SearchServlet?index=${i}&category=${requestScope.CATEGORY}&dateresoure=${requestScope.DATE}&txtSearchValue=${requestScope.SEARCHVALUE}">${i}_____</a>

            </c:forEach>
            <c:forEach var="list" items="${sessionScope.LISTRESOURCE}" varStatus="counters">              
                <form action="DispatchServlet">
                    <tr>
                        <td>
                            ${counters.count}
                        </td>
                        <td>
                            ${list.itemname}
                        </td>
                        <td>
                            ${list.color}
                        </td>
                        <td>
                            ${list.categoryname}
                        </td>
                        <td> 
                            <input type="text" name="tmp" value="${requestScope.QUANTITY ne null ? requestScope.QUANTITY : list.quantity}"/>
                            <input type="hidden" name="quantity" value="${list.quantity}" />                
                        </td>
                        <td>
                            ${list.dateresoure}
                        </td>
                        <td>
                            <c:if test="${user.role eq 'user'}">
                                <input type="submit" value="Booking" name="btAction" />
                                <input type="hidden" name="userID" value="${user.userID}" />
                                <input type="hidden" name="itemID" value="${list.itemID}" />                                
                            </c:if>
                        </td>
                        <c:if test="${requestScope.ERROR ne null && list.itemID eq RESID}">
                            <td style="color: red">
                                ${requestScope.ERROR}
                            </td>
                        </c:if>
                    </tr>

                </form>
            </c:forEach>
        </tbody>
    </table>



</body>
</html>
