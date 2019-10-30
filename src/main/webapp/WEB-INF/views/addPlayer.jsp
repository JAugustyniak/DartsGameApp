<%--
  Created by IntelliJ IDEA.
  User: Groniu
  Date: 10/30/2019
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="player">
    Podaj nickName : <form:input path="nickName"/><br>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
