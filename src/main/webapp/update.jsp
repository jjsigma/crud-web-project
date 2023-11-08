<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Contact</title>
    <link href="styles.css" rel="stylesheet">
</head>
<%
    int contactId = (int) request.getAttribute("contactId");
    String url = "?contactId="+contactId;
%>
<body>
<div id="root">
    <div class="container">
        <h1>Phone book</h1>
        <h2>Update contact</h2>
        <div class="container">
            <form action="${pageContext.request.contextPath}/update-contact<%=url%>" method="post">
                <fieldset>
                    <label>Name</label>
                    <label>
                        <input name="name" class="form-control" type="text" value="<%= request.getParameter("name") %>">
                    </label>
                </fieldset>
                <fieldset>
                    <label>Surname</label>
                    <label>
                        <input name="surname" class="form-control" type="text" value="<%= request.getParameter("surname") %>">
                    </label>
                </fieldset>
                <fieldset>
                    <label>Phone Number</label>
                    <label>
                        <input name="phone_number" class="form-control" type="text" value="+<%= request.getParameter("phone_number").replaceAll(" ", "") %>">
                    </label>
                </fieldset>
                <button class='btn btn-add' type='submit'>Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
