<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-8">
            <form action="<%=request.getContextPath()%>/registration" method="post">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" class="form-control" name="name">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" id="email" class="form-control" name="email">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="text" id="password" class="form-control" name="password">
                </div>
                <button type="submit" class="btn btn-primary">Sign Up</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
