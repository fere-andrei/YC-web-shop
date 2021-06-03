<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<jsp:include page="_header.jsp"></jsp:include>
<%--<jsp:include page="_menu.jsp"></jsp:include>--%>
<div class="container">
    <div class="row">
        <div class="col-lg-12 col-xl-12 col-sm-12 col-xs-12">
            <br>
            <br>
            <div class="container col-md-8 col-md-offset-3" style="overflow: auto">
                <div class="card card-body">
                    <h1>Login Form</h1>
                    <form action="<%=request.getContextPath()%>/login" method="post" id="loginForm">
                        <a style="color: green">${successMessage}</a>
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
        </div>
    </div>
</div>

<jsp:include page="_footer.jsp"></jsp:include>




