<%--
  Created by IntelliJ IDEA.
  User: pvzar
  Date: 21.09.2021
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<c:forEach items="${list}" var="el">
    <c:out value="${el}"/>
</c:forEach>
</body>
</html>
