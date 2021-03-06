<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anfe0908
  Date: 1/31/2020
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css"
      href="${contextPath}/resources/css/loading.css">
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
                <td><a class="js-price-of-item" id = "js-item-price" data-item-price="${cartItem.pricePerUnit}"><c:out value="${cartItem.price}" /></a></td>
                <td><input class = "js-quantity-to-update" id = "js-quantity-from-cart" type = "number" name = "newQuantity" value="${cartItem.quantity}" min="0"></td>
                <td>
                    <button type = "submit" class="js-update-cart-button" id="js-update-cart" name="itemId" value="${cartItem.id}">Update Cart</button>
                </td>
            </tr>
            </c:forEach>

    </table>
</div>
You have to pay : <li id = "js-total-cost">${totalCost}</li>
<br/>

<c:if test = "${currentUser.fullName != 'guest'}">
    <button type = "submit" id="js-place-order" name="orderButton" value="${myCartItems}">Place Order</button>
</c:if>

<div id = "hidden-message" style="visibility: hidden">
    Your order is COMPLETED, Thank you for using this shop!
    <div class="loader"></div>
</div>

<c:if test = "${currentUser.fullName == 'guest'}">
    Please <a href="login">Login</a> to buy!
</c:if>


<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
