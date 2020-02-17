<%--
  Created by IntelliJ IDEA.
  User: anfe0908
  Date: 2/11/2020
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<div style="background: #E0E0E0; height: 65px; padding: 5px; ">
    <div style="float: left; " >
        <h1>YC Shop</h1>
    </div>

    <img src="img/app-icon.png" style="width: 55px; height: 55px; padding-bottom: inherit">
</div>

<head>
    <title>Order Details</title>


    <table border="2" cellpadding="5" align="center">
        <tr>
            <th>Product Name</th>
            <th>Price$</th>
            <th>Number of units</th>
        </tr>
        <c:forEach items="${orderDetailsList}" var="orderDetailsItems">
            <tr class = "js-product-from-cart">
                <td><c:out value="${orderDetailsItems.productName}" /></td>
                <td><c:out value="${orderDetailsItems.price}"/>$</td>
                <td> <c:out value="${orderDetailsItems.quantity}" /></td>
            </tr>
        </c:forEach>

    </table>

    <jsp:include page="_footer.jsp"></jsp:include>

</head>
<body>

</body>
</html>
