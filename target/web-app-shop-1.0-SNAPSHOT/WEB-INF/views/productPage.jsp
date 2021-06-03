<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
      href="${contextPath}/resources/css/displayProducts.css">
<link rel="stylesheet" type="text/css"
      href="${contextPath}/resources/css/paging.css">
<jsp:include page="_header.jsp"></jsp:include>
<%--<jsp:include page="_menu.jsp"></jsp:include>--%>

<div class="container">
    <div class="row">
        <div class="col-lg-12 col-xl-12 col-sm-12 col-xs-12">
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
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
