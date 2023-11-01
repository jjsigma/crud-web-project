<%@ page import="com.tylerpants.webproject.sql.SQLConnector" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="refresh" content="30">  <%-- !!! --%>
    <title>Phone Book</title>
    <link rel="stylesheet" href="styles.css">
</head>
<%!
//    SQLConnector connector = new SQLConnector();
    String username;
    String sessionID;

%>
<nav class="header">
    <%
        Cookie[] cookies = request.getCookies();
        if(request.getAttribute("logged") != null && (boolean) request.getAttribute("logged")) {
            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("username")) username = cookie.getValue();
                    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
                }
            }
            out.println("<button class ='btn btn-add' type = 'button' onclick = 'location.href = \"/logout\"' > Logout </button>");
        } else if(request.getAttribute("logged") == null || !(boolean) request.getAttribute("logged")) {
            out.println("<button class ='btn btn-add' type = 'button' onclick = 'location.href = \"login.jsp\"' > Login </button>");
       }
    %>

</nav>
<body>
    <div id="root">
        <div class="container">
            <h1>Phone Book</h1>
            <h2>All contacts</h2>
            <table class="table">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Phone number</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>0</td>
                    <td>John</td>
                    <td>Doe</td>
                    <td>+12345678910</td>
                    <td>
                        <button class="btn btn-update" onclick="location.href= '/update'">Update</button>
                    </td>
                    <td>
                        <button class="btn btn-delete" onclick="location.href = '/delete'">Delete</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <form method="get" action="${pageContext.request.contextPath}/add">
                <div class="container">
                    <button class="btn btn-add" type="submit">Add</button>
                </div>
            </form>
        </div>
    </div>
</body>
<%--<script type="text/javascript">--%>
<%--    document.getElementById("update").onclick = function () {--%>
<%--        location.href = --%>
<%--    }--%>
<%--</script>--%>
</html>
