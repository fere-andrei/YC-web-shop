<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-12 col-xl-12 col-sm-12 col-xs-12">
            <div>
                <meta charset="UTF-8">
                <title>Home Page</title>
            </div>
            <%--<div class="container">
                <div class="row">
                    <div class="col-lg-12 col-xl-12 col-sm-12 col-xs-12">
                        <div class="col-sm-12"><a><img src="${contextPath}/resources/img/banner-homepage.png"></a></div>
                    </div>
                </div>
            </div>--%>

            <c:if test="${currentUser.fullName != 'guest'}">

                <li id="user-name-label">Hello ${currentUser.fullName}</li>
                <br>
                <li>Register date: ${currentUser.registerDate}</li>
                <br>
            </c:if>
            <jsp:include page="_footer.jsp"></jsp:include>
        </div>
    </div>
</div>
