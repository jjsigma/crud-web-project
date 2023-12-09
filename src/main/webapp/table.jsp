<%@ page import="com.tylerpants.webproject.sql.ContactsSQLConnectorOld" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tylerpants.webproject.Contact" %>
<%@ page import="com.tylerpants.webproject.sql.ContactsDao" %>
<%@ page import="com.tylerpants.webproject.User" %>
<%@ page import="com.tylerpants.webproject.sql.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <noscript>Enable JavaScript !</noscript>
    <meta charset="utf-8">
    <meta http-equiv="refresh" content="60">
    <%-- !!! --%>
    <title>Phone Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
</head>
<%!
    private int userId;
    private String username;
    private String sessionID;
    private boolean loggedIn;
    private ContactsDao contactsDao;
    private List<Contact> contacts;
    private User user;
%>
<nav class="header">
    <%
        request.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) userId = Integer.parseInt(cookie.getValue());
                else if (cookie.getName().equals("username")) username = cookie.getValue();
                else if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
                else if (cookie.getName().equals("logged")) loggedIn = cookie.getValue().equals("true");
            }
        }
        if (loggedIn) {
            out.println("<form method=\"post\" action=\"/logout\"><button class ='btn btn-add' type = 'submit'> Logout </button></form>");
            out.print("<h3>" + username + " | " + userId + "</h3>");
            out.print("<h3>JSESSIONID: " + sessionID + "</h3>");
            if(user == null) {
                user = new UserDao().getUserById(userId);
            }
            if(contactsDao == null) {
                contactsDao = new ContactsDao();
            }
            contacts = contactsDao.getAllContacts(user);
        } else {
            response.addCookie(new Cookie("logged", "false"));
            user = null;
            contactsDao = null;
            contacts = null;
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
            <% if (!loggedIn || contacts == null) { %>
            <tr>
                <td>John</td>
                <td>Doe</td>
                <td>+12345678910</td>
                <td>
                    <button class="btn btn-update" onclick="buttonNoLogin()">Update</button>
                </td>
                <td>
                    <button class="btn btn-delete" onclick="buttonNoLogin()">Delete</button>
                </td>
            </tr>
            <%
            } else {
                for (int i = 0; i < contacts.size(); i++) {
                    out.println("<tr>");
                    out.println("<td id = \"name" + i + "\">" + contacts.get(i).getName() + "</td>");
                    out.println("<td id = \"surname" + i + "\">" + contacts.get(i).getSurname() + "</td>");
                    out.println("<td id = \"phone_number" + i + "\">" + contacts.get(i).getPhoneNumber() + "</td>");
            %>
            <td>
                <button id="update<%= i %>" class="btn btn-update" onclick="updateContact(<%= i %>)">Update</button>
            </td>
            <td>
                <button id="delete<%= i %>" class="btn btn-delete" onclick="deleteContact(<%= i %>)" >Delete</button>
            </td>
            <%
                    out.println("</tr>");
                }
            }%>
            </tbody>
        </table>
        <div class="container">
            <button class="btn btn-add" type="submit" onclick="checkLoggedCookie()">Add</button>
        </div>
    </div>
</div>
</body>
<script>
    function updateContact(i) {
        const text = '?name=' + document.getElementById("name" + i).innerText + '&surname=' + document.getElementById("surname" + i).innerText + '&phone_number=' + document.getElementById("phone_number" + i).innerText;
        location.href = '/update' + text;
    }

    function deleteContact(i) {
        const text = '?name=' + document.getElementById("name" + i).innerText + '&surname=' + document.getElementById("surname" + i).innerText + '&phone_number=' + document.getElementById("phone_number" + i).innerText;
        let choice = confirm('Delete contact?');
        if(choice === true) {
            location.href = '/delete'+text
        }
    }

    function buttonNoLogin() {
        <%
        if(loggedIn) {%>
        alert('You cant redact John Doe!');
        <% } else { %>
        alert('Login to use it');
        <% } %>
    }

    function getCookie(cname) {
        let name = cname + "=";
        let ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    function checkLoggedCookie() {
        let cname = getCookie("logged");
        if (cname === "" || cname === null || cname === 'false') {
            buttonNoLogin();
        } else {
            location.href = "/add";
        }
    }
</script>
</html>
