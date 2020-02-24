<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anfe0908
  Date: 1/29/2020
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    @import "css/displayProducts.css";
    @import "css/paging.css";
</style>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>


<caption><h2>List of products</h2></caption>
<br>
<br>
<div id = "div-container">
    <div class="grid-container" id="div-pag">
        <c:forEach items="${products}" var="item">
            <div class="grid-item">
                <tr class="js-product-details">
                    <td><img SRC= <c:out value="${item.imageUrl}"/> width="200" height="200"></td>
                    <li>Name: <c:out value="${item.productName}"/></li>
                    <li>Price: <c:out value="${item.price}"/>$</li>
                    <li>Category: <c:out value="${item.category}"/></li>
                    <li>Quantity: <c:out value="${item.stockNumber}"/></li>
                    <td><input class="js-quantity-class" id="js-quantity-id" type="number" name="quantity" value="1"
                               min="1" max="${item.stockNumber}"></td>
                    <td>
                        <button id="js-button-id" type="submit" name="productId" value="${item.id}"
                                class="js-add-to-cart-button">Add to cart
                        </button>
                    </td>
                </tr>
            </div>
        </c:forEach>
    </div>
</div>

<div>
    <script>
        $('#div-pag').easyPaginate({
            paginateElement: 'div',
            elementsPerPage: 3
        });
    </script>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
