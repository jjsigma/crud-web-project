<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add contact</title>
    <link href="styles.css" rel="stylesheet">
</head>
<body>
<div id="root">
    <div class="container">
        <h1>Phone book</h1>
        <h2>Add contact</h2>
        <div class="container">
            <form action="${pageContext.request.contextPath}/add-contact" method="post">
                <fieldset>
                    <label>Name</label>
                    <label>
                        <input name="name" class="form-control" type="text" value>
                    </label>
                </fieldset>
                <fieldset>
                    <label>Surname</label>
                    <label>
                        <input name="surname" class="form-control" type="text" value>
                    </label>
                </fieldset>
                <fieldset>
                    <label>Phone Number</label>
                    <label>
                        <input name="phone_number" class="form-control" type="text" value>
                    </label>
                </fieldset>
                <button class="btn btn-add" type="submit">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
