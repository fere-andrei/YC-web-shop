<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <th>Number of units</th>
            <th>Remove</th>
        </tr>
        <c:set var ="index" value="0"></c:set>
        <c:forEach items="${myCartItems}" var="cartItem">
            <tr class = "js-product-from-cart">
                <td><c:out value="${cartItem.productName}" /></td>
                <td class="js-price-of-item"><c:out value="${cartItem.price}" /></td>
                <td><input class = "js-quantity-to-update" id = "js-quantity-from-cart" type = "number" name = "newQuantity" value="${cartItem.quantity}" min="0"></td>
                <td>
                    <button type = "submit" class="js-update-cart-button" id="js-update-cart" name="itemId" value="${cartItem.id}">Update Cart</button>
                </td>
            </tr>
            </c:forEach>

    </table>
</div>
You have to pay : <b id = "js-total-cost">${totalCost}</b> $
<br/>

    <button type = "submit" id="js-place-order" name="orderButton" value="${myCartItems}">Place Order</button>


<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
