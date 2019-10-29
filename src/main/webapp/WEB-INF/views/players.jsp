<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: joanna
  Date: 28.10.19
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Players</title>
</head>
<body>
Add <c:out value="${game.numberOfPlayers}"></c:out> players
    <form:form method="post" modelAttribute="player">
        nickName: <form:input path="nickName"/><br>
        <input type="submit" value="Submit">
    </form:form>


</table>
</body>
</html>