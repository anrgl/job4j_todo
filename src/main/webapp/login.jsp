<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-8 mt-5">
            <form action="<%=request.getContextPath()%>/login" method="post">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" id="email" class="form-control" name="email">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" class="form-control" name="password">
                </div>
                <p>No account? Sign up <a href="<%= request.getContextPath() %>/registration">here</a></p>
                <button type="submit" class="btn btn-primary">Sign In</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
