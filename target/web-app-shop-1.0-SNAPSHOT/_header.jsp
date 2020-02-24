<%--@elvariable id="" type="entity.UserDTO"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true" %>
<link href="css/background.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>--%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/jquery.easyPaginate.js"></script>
<script src="js/allJavaScripts.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
    @import "css/header.css";
</style>
<div class="topnav">

    <div>
        <a><h1 class="title-shop">YC Shop</h1></a>
        <a><img src="img/app-icon.png" style="width: 55px; height: 55px"></a>
    </div>

    <div class="topnav-right">
        <a class="shopping-item">
            <a href="${pageContext.request.contextPath}/cart">My Cart <i class="shopping-cart-view"></i>
                <p id="JS-cart-item-count" class="product-count"><b>${numberOfItems}</b>
                </p></a>
        </a>


        <c:if test="${currentUser.fullName != 'guest'}">

               <a href="home" id="user-name-label">Hello ${currentUser.userName}</a>
                    <a href="logout">Logout</a>

        </c:if>


        <a>
            <c:if test="${currentUser.userName == null || currentUser.fullName == 'guest'}">
                <a href="register">Register</a>
                /
                <a href="login">Login</a>
            </c:if>
        </a>

    </div>
</div>
