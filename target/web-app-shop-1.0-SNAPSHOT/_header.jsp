<%--@elvariable id="" type="entity.UserDTO"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<link href="css/background.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-1.4.3.min.js"></script>
<script src="js/allJavaScripts.js"></script>

<style type="text/css">
    @import "css/header.css";
</style>

<div style="background: #E0E0E0; height: 65px; padding: 5px; ">
    <div style="float: left; " >
        <h1>YC Shop</h1>
    </div>

    <img src="img/app-icon.png" style="width: 55px; height: 55px; padding-bottom: inherit">

    <div class = "header-user-panel">


        <br/>
        <div>
            <c:if test = "${currentUser.userName != null}">
                Hello! <b id="user-name-label">${currentUser.userName}</b>
                <br/>
                <a href="logout">Logout</a>
            </c:if>
        </div>

        <div>
        <c:if test = "${currentUser.userName == null}">
            <a href="register">Register</a>
            /
            <a href="login">Login</a>
        </c:if>
        </div>

    </div>

    <div style="float: right" class="shopping-item">
        <a href="${pageContext.request.contextPath}/cart">My Cart <i class="shopping-cart-view"></i>
            <p id="JS-cart-item-count" class="product-count" data-no-of-items="${numberOfItems}"><b>${numberOfItems}</b></p></a>
    </div>
</div>