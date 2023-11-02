<%@ page import="com.tylerpants.webproject.sql.ContactsSQLConnector" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tylerpants.webproject.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="refresh" content="60">  <%-- !!! --%>
    <title>Phone Book</title>
    <link rel="stylesheet" href="styles.css">
</head>
<%!
    private int userId;
    private String username;
    private String sessionID;
    private boolean loggedIn;
    private ContactsSQLConnector userSQLConnector;
    private List<Contact> contacts;
%>
<nav class="header">
    <%
        request.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userId")) userId = Integer.parseInt(cookie.getValue());
                else if(cookie.getName().equals("username")) username = cookie.getValue();
                else if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
                else if(cookie.getName().equals("logged")) loggedIn = cookie.getValue().equals("true");
            }
        }
        System.out.println(loggedIn);
        if(loggedIn) {
            out.println("<form method=\"post\" action=\"/logout\"><button class ='btn btn-add' type = 'submit'> Logout </button></form>");
            out.print("<h3>"+ username +" | "+ userId +"</h3>");
            out.print("<h3>JSESSIONID: "+ sessionID+"</h3>");
            userSQLConnector = new ContactsSQLConnector(userId);
            contacts = userSQLConnector.getContactsById();
        } else {
            response.addCookie(new Cookie("logged", "false"));
            out.println("<form method=\"post\" action=\"login.jsp\"><button class ='btn btn-add' type = 'submit'> Login </button></form>");
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
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Phone number</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <% if(!loggedIn || contacts == null) { %>
                <tr>
                    <td>John</td>
                    <td>Doe</td>
                    <td>+12345678910</td>
                    <td>
                        <button class="btn btn-update" onclick="location.href= '/update'">Update</button>
                    </td>
                    <td>
                        <button class="btn btn-delete" onclick="location.href= '/delete'">Delete</button>
                    </td>
                </tr>
                <%} else {
                    List<Contact> contacts = userSQLConnector.getContactsById();
                    for(Contact c : contacts) {
                        out.println("<tr>");
                        out.println("<td>"+c.getName()+"</td>");
                        out.println("<td>"+c.getSurname()+"</td>");
                        out.println("<td>"+c.getPhoneNumber()+"</td>");
                        out.println("<td><button class=\"btn btn-update\" onclick=\"location.href= '/update'\">Update</button></td>");
                        out.println("<td><button class=\"btn btn-delete\" onclick=\"location.href= '/delete'\">Delete</button></td>");
                        out.println("</tr>");
                    }
                }%>
                </tbody>
            </table>
            <form method="get" action="${pageContext.request.contextPath}/add">
                <div class="container">
                    <button class="btn btn-add" type="submit">Add</button>
                </div>
                <%
                    if(request.getAttribute("notLogged") != null && request.getAttribute("notLogged").equals("true")) {
                        out.println("<div class='error-message'>Login to use it</div>");
                        request.setAttribute("notLogged", null);
                    }
                %>
            </form>
        </div>
    </div>
</body>
</html>
