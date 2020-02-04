<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anfe0908
  Date: 1/29/2020
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
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
<div>
    <table border="2" cellpadding="5" align="center">
        <tr>
            <th>Product Name</th>
            <th>Price$</th>
            <th>Category</th>
            <th>Stock</th>
            <th>Number of units</th>
            <th>Add to cart</th>
        </tr>
           <c:forEach items="${products}" var="item">
               <tr>
                   <td><c:out value="${item.productName}" /></td>
                   <td><c:out value="${item.price}"/>$</td>
                   <td><c:out value="${item.category}" /></td>
                   <td><c:out value="${item.stockNumber}" /></td>
                   <td><input id = "stockValue" type = "number" name = "quantity" value="1" min="1" max="${item.stockNumber}"></td>

                   <td>
                       <form action="<%=request.getContextPath()%>/cart" method="post">
                           <button id="productId" type="submit" name="productId" value = "${item.id}" class="add-btn">Add to cart</button>
                          <%-- <button id="productId" name="productId" value = "${item.id}" >Add to cart</button>--%>
                       </form>
                   </td>

               </tr>
           </c:forEach>
    </table>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
