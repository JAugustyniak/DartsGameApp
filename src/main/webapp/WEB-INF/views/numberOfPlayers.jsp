<%--
  Created by IntelliJ IDEA.
  User: joanna
  Date: 28.10.19
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
<h2>Fill the form</h2>
<form:form method="post" modelAttribute="game">
    Number of players: <form:input path="numberOfPlayers"/><br>
    <input type="submit" value="Submit">
</form:form>

</body>
</html>
