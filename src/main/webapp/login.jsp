<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<br>
<br>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <div class="card card-body">
        <h1>Login Form</h1>
        <form action="<%=request.getContextPath()%>/login" method="post" id="loginForm">
            <a style="color: green">${succesMessage}</a>
            <%--@declare id="username"--%><%--@declare id="password"--%>
                <div class="form-group">
                    <label for="username">User Name:</label> <input type="text"
                                                                 class="form-control" id="username" placeholder="User Name"
                                                                 name="username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label> <input type="password"
                                                                class="form-control" id="password" placeholder="Password"
                                                                name="password" required>
                </div>

            <br><br>
            <button type="submit" class="btn btn-primary">Login</button>

                <a style="color: red">${errMsg}</a>
        </form>
    </div>
</div>

<jsp:include page="_footer.jsp"></jsp:include>

</body>

<script type="text/javascript">

    $(document).ready(function() {
        $("#loginForm").validate({
            rules: {
                username: {
                    required: true,
                    username: true
                },

                password: "required",
            },

            messages: {
                username: {
                    required: "Please enter username",
                    username: "Please enter a valid username "
                },

                password: "Please enter password"
            }
        });

    });
</script>

</html>