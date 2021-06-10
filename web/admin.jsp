<%-- 
    Document   : admin
    Created on : May 15, 2021, 2:33:20 PM
    Author     : test
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.USER.role ne 'admin'}">
    <c:redirect url="LogoutServlet"></c:redirect>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Admin's Page</h1>
        <c:set var="user" value="${sessionScope.USER}"/>
        <h2>Hello Admin,${user.name}</h2>
        <h3>
            <a href="login.jsp">Logout</a> 
            <a href="ResourceServlet">Resource</a>
        </h3>
        <h4 style="color: blue">Total:  ${sessionScope.TOTAL}</h4>
        <form action="DispatchServlet">
            <select name="status">
                <option value="everything">everything</option>
                <c:forEach var="liststatus" items="${sessionScope.STATUSREQUEST}">
                    <c:if test="${liststatus.statusreqID ne 'sr4'}">
                    <option value="${liststatus.statusreqID eq sessionScope.STATUS ? sessionScope.STATUS :liststatus.statusreqID}">${liststatus.statusreqname}</option>
                    </c:if>
                </c:forEach>
            </select>
            Date:<input type="date" name="daterequest" value="${not empty sessionScope.DATEREQUEST ?sessionScope.DATEREQUEST : param.daterequest}"/>
            <input type="submit" value="SearchRequest" name="btAction"/> 
            <input type="hidden" name="role" value="${sessionScope.USER.role}" />
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Date Booking Request</th>
                    <th>Quantity</th>
                    <th>Status Request</th>
                    <th>User Book</th>
                    <th>Resource Book</th>
                    <th>Process</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach begin="1" end="${sessionScope.ENDPAGE_REQUEST}" var="i">
                <a href="ProcessServlet?indexr=${i}&daterequest=${sessionScope.DATEREQUEST}&status=${sessionScope.STATUS}&role=admin">${i}____</a>
            </c:forEach>
            <c:forEach var="list" items="${sessionScope.LIST_REQUEST}" varStatus="counters">
                <form action="DispatchServlet">                
                    <tr>
                        <td>${counters.count}</td>
                        <td>${list.datebook}</td>
                        <td>
                            ${list.quantityReq}
                            <input type="hidden" name="itemID" value="${list.item.itemID}" />
                            <input type="hidden" name="quantityreq" value="${list.quantityReq}" />
                        </td>
                        <c:choose>
                            <c:when test="${list.statusreqID eq 'new'}"> <td style="color: green">${list.statusreqID}</td></c:when>
                            <c:when test="${list.statusreqID eq 'accept'}"> <td style="color: orange">${list.statusreqID}</td></c:when>
                            <c:when test="${list.statusreqID eq 'inactive'}"><td style="color: gray">${list.statusreqID}</td></c:when>
                            <c:when test="${list.statusreqID eq 'delete'}"><td>${list.statusreqID}</td></c:when>
                        </c:choose>
                        <td>${list.userID}</td>
                        <td>${list.item.itemname}</td>
                        <td>
                            <c:if test="${list.statusreqID eq 'new' }">
                                <input type="submit" value="Accept" name="btAction"/>
                            </c:if>
                            <c:if test="${list.statusreqID ne 'delete'}">
                                <input type="submit" value="Delete" name="btAction"/>
                            </c:if>
                            <input type="hidden" name="daterequest" value="${sessionScope.DATEREQUEST}"/>
                            <input type="hidden" name="status" value="${sessionScope.STATUS}" />
                            <input type="hidden" name="requestID" value="${list.requestID}" />
                            <input type="hidden" name="role" value="${sessionScope.USER.role}" />
                            <input type="hidden" name="indexr" value="${sessionScope.INDEXR}" />

                        </td>
                        <c:if test="${requestScope.ERROR ne null && list.requestID eq requestScope.REQID}">
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
