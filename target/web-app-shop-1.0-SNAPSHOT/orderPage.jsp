<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anfe0908
  Date: 2/7/2020
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>List of orders</h3>
    <br>
    <b>ALL ORDERS</b>
    <br>
    <br>
    <br>
    <div>
        <table border="1" style="width:90%" align="center">
            <tr>
                <th>Order number</th>
                <th>User Name</th>
                <th>Address</th>
                <th>Order Date</th>
                <th>Total Cost</th>
                <th>Order Details</th>
            </tr>
            <c:forEach items="${orderItems}" var="item">
                <tr>
                    <td><c:out value="${item.orderNumber}" /></td>
                    <td><c:out value="${currentUser.fullName}"/></td>
                    <td><c:out value="${item.address}" /></td>
                    <td><c:out value="${item.orderDate}" /></td>
                    <td><c:out value="${item.totalCost}"/>$</td>
                   <%-- <td>
                        <a href='#' onclick='javascript:window.open("orderDetails.jsp", "_blank", "scrollbars=1,resizable=1,height=300,width=450");' title='Order Details'>Show details</a>
                    </td>--%>

                    <td>
                        <button type = "submit" class="js-show-order-details" id="js-show-details" name="orderNUmber" value="${item.orderNumber}">Show details</button>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>




    <jsp:include page="_footer.jsp"></jsp:include>
</head>
<body>

</body>
</html>
