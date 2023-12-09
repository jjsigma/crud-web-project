<%@ page import="com.tylerpants.webproject.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Contact</title>
    <link href="styles.css" rel="stylesheet">
</head>
<%
    Contact contact = (Contact) request.getAttribute("contact");
%>
<body>
<div id="root">
    <div class="container">
        <h1>Phone book</h1>
        <h2>Update contact</h2>
        <div class="container">
            <form action="${pageContext.request.contextPath}/update-contact" method="post">
                <fieldset>
                    <label>Name</label>
                    <label>
                        <input name="name" class="form-control" type="text" value="<%= contact.getName() %>">
                    </label>
                </fieldset>
                <fieldset>
                    <label>Surname</label>
                    <label>
                        <input name="surname" class="form-control" type="text" value="<%= contact.getSurname() %>">
                    </label>
                </fieldset>
                <fieldset>
                    <label>Phone Number</label>
                    <label>
                        <input name="phone_number" class="form-control" type="text" value="+<%= contact.getPhoneNumber() %>">
                    </label>
                </fieldset>
                <button class='btn btn-add' type='submit'>Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
