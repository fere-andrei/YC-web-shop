<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="_header.jsp"></jsp:include>
<%--<jsp:include page="_menu.jsp"></jsp:include>--%>

<div class="container">
    <div class="row">
        <div class="col-lg-12 col-xl-12 col-sm-12 col-xs-12">
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
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"></jsp:include>



