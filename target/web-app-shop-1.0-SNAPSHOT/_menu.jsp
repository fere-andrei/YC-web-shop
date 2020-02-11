<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div style="padding: 5px;">

    <a href="${pageContext.request.contextPath}/home">Home</a>
    |
    <a href="${pageContext.request.contextPath}/products">Product List</a>
    |
    <c:if test = "${currentUser.userName != null}">
    <a href="order">Order</a>
    </c:if>


</div>