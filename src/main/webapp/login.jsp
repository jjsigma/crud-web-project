<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h1>Log in</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <fieldset>
            <label>Username</label>
            <label>
                <input name ="username" class="form-control" type="text" value>
            </label>
        </fieldset>
        <fieldset>
            <label>Password</label>
            <label>
                <input name ="password" class="form-control" type="password" value>
            </label>
        </fieldset>
        <button class="btn btn-add" type="submit">Log in</button>
        <%
            if(request.getAttribute("usernameError") != null && (boolean) request.getAttribute("usernameError")) {
                out.println("<div class='error-message'>Username is invalid</div>");
            } else if(request.getAttribute("passwordError") != null && (boolean) request.getAttribute("passwordError")) {
                out.println("<div class='error-message'>Password is invalid</div>");
            }
        %>
    </form>
</div>
</body>
</html>
