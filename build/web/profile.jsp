<%-- 
    Document   : profile
    Created on : May 18, 2021, 1:57:13 PM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.USER.role ne 'user'}">
    <c:redirect url="LogoutServlet"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
    </head>
    <body>
        <h1>User's Profile</h1>
        <h2>Welcome, ${sessionScope.USER.name}</h2>
        <h3> 
            <a href="DispatchServlet?btAction=Logout">Logout</a> 
            <a href="ResourceServlet">Resource</a>
        </h3>
        <h4 style="color: blue">Total:  ${sessionScope.TOTAL}</h4>
        <form action="DispatchServlet">
            <select name="status">
                <option value="everything">everything</option>
                <c:forEach var="liststatus" items="${sessionScope.STATUSREQUEST}">
                    <c:if test="${liststatus.statusreqID ne 'sr3'}">
                    <option value="${liststatus.statusreqID}"${liststatus.statusreqID eq requestScope.STATUS ? "selected" : ""}>${liststatus.statusreqname}</option>
                    </c:if>
                </c:forEach>
            </select>
            Date:<input type="date" name="daterequest" value="${requestScope.DATEREQUEST}"/>
            Resource_Name: <input type="text" name="Searchvalue" value="${requestScope.SEARCH}" />
            <input type="hidden" name="role" value="${sessionScope.USER.role}" />
            <input type="hidden" name="userID" value="${sessionScope.USER.userID}" />
            <input type="submit" value="SearchRequest" name="btAction"/>
        </form>         
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Date Booking Request</th>
                    <th>Quantity</th>
                    <th>Status Request</th>
                    <th>Resource Book</th>
                    <th>Process</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach begin="1" end="${sessionScope.ENDPAGE_REQUEST}" var="i">
                <a href="ProcessServlet?indexr=${i}&daterequest=${requestScope.DATEREQUEST}&status=${requestScope.STATUS}&role=user&userID=${sessionScope.USER.userID}&Searchvalue=${requestScope.SEARCH}">${i}____</a>
            </c:forEach>
            <c:forEach var="list" items="${sessionScope.LIST_REQUEST}" varStatus="counters">
                <form action="DispatchServlet">                
                    <tr>
                        <td>${counters.count}</td>
                        <td>${list.datebook}</td>
                        <td>
                            ${list.quantityReq}
                            <input type="hidden" name="quantityreq" value="${list.quantityReq}" />
                            <input type="hidden" name="itemID" value="${list.item.itemID}" />
                        </td>                        
                        <c:choose>
                            <c:when test="${list.statusreqID eq 'new'}"> <td style="color: green">processing...</td></c:when>
                            <c:when test="${list.statusreqID eq 'accept'}"> <td style="color: orange">${list.statusreqID}</td></c:when>
                            <c:when test="${list.statusreqID eq 'inactive'}"><td style="color: gray">${list.statusreqID}</td></c:when>
                        </c:choose>
                        <td>
                            ${list.item.itemname}
                        </td>
                        <td>
                            <c:if test="${list.statusreqID eq 'new' }">
                            <input type="submit" value="Delete" name="btAction"/>                            
                            <input type="hidden" name="indexr" value="${sessionScope.INDEXR}" />
                            <input type="hidden" name="role" value="${sessionScope.USER.role}" />
                            <input type="hidden" name="userID" value="${sessionScope.USER.userID}" />
                            <input type="hidden" name="requestID" value="${list.requestID}" />
                            <input type="hidden" name="status" value="${requestScope.STATUS}"/>
                            <input type="hidden" name="Searchvalue" value="${requestScope.SEARCH}" />
                            <input type="hidden" name="daterequest" value="${requestScope.DATEREQUEST}"/>
                            </c:if>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
