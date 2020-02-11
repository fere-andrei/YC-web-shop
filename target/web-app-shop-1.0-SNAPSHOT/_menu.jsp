<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
    @import "css/menu.css";
</style>

<div class="navbar-custom">
    <a href="${pageContext.request.contextPath}/home">Home</a>
    <a href="${pageContext.request.contextPath}/products">Product List</a>
    <c:if test = "${currentUser.userName != null}">
        <a href="order">Order</a>
    </c:if>
</div>


