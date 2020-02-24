<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body background="img/back.jpg" >


<h3>Home Page</h3>


<c:if test="${currentUser.fullName != 'guest'}">

    <li id="user-name-label">Hello ${currentUser.fullName}</li>
    <br>
    <li>Register date: ${currentUser.registerDate}</li>
    <br>
</c:if>


<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>