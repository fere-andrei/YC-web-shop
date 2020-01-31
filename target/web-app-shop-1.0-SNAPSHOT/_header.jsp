<%@ page import="entity.UserEntity" %><%--@elvariable id="loginedUser" type="entity.UserEntity"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<link href="css/background.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-1.4.3.min.js"></script>
<script src="js/countCartItems.js"></script>
<div style="background: #E0E0E0; height: 65px; padding: 5px; ">
    <div style="float: left; " >
        <h1>YC Shop</h1>
    </div>

    <img src="img/app-icon.png" style="width: 55px; height: 55px; padding-bottom: inherit">


    <%
        UserEntity username = (UserEntity) session.getAttribute("loginedUser");
        if (username.getUserName() == null) {
    %>
            <a href="${pageContext.request.contextPath}/register">Register</a>
            <a href="${pageContext.request.contextPath}/login">Login</a>
    <%
    } else {
    %>
    <div style="float: right; padding: 10px; text-align: right;">

        Hello <b>${loginedUser.userName}</b>
        <br/>
        <a href="logout">Logout</a>

    </div>
    <%}%>


    <div style="float: right" class="shopping-item">
        <a href="${pageContext.request.contextPath}/cart">My Cart <i class="shopping-cart-view"></i>
            <p id="JS-cart-item-count" class="product-count"><b>0</b></p></a>
    </div>
</div>