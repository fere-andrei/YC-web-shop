<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%--
  Created by IntelliJ IDEA.
  User: anfe0908
  Date: 1/31/2020
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Cart</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div>
    <table border="2" cellpadding="5" align="center">
        <tr>
            <th>Product Name</th>
            <th>Price$</th>
            <th>Category</th>
            <th>Number of units</th>
            <th>Remove</th>
        </tr>
        <c:forEach items="${myCartProducts}" var="cartItem">
            <tr>
                <td><c:out value="${cartItem.productName}" /></td>
                <td><c:out value="${cartItem.price}" /></td>
                <td><c:out value="${cartItem.category}" /></td>
                <td><c:out value="${cartItem.stockNumber}" /></td>
                <td><button id="js-remove-from-cart" > Remove from cart</button> </td>
            </tr>
        </c:forEach>
        <button id="js-place-order">Place Order</button>
    </table>
</div>


<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
