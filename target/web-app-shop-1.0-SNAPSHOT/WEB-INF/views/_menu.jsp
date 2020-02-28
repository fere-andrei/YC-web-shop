<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css"
      href="${contextPath}/resources/css/menu.css">

<div class="navbar-custom">
    <a href="${pageContext.request.contextPath}/home">Home</a>
    <a href="${pageContext.request.contextPath}/products">Product List</a>
    <div class="dropdown-custom">
        <button class="dropbtn-custom">${categoryDisplay}
            <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content-custom">
            <c:forEach items="${categoryList}" var="category">
                <tr class = "categoryItems">
                    <a class= "dropbtn-custom" ><c:out value="${category}"/></a>
                </tr>
            </c:forEach>
        </div>
    </div>
    <c:if test = "${currentUser.fullName != 'guest'}">
        <a href="order">My Orders</a>
    </c:if>

</div>


