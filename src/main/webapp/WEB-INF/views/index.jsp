<%--
  Created by IntelliJ IDEA.
  User: pvzar
  Date: 21.09.2021
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<style>
    table.iksweb{text-decoration: none;border-collapse:collapse;width:100%;text-align:center;}
    table.iksweb th{font-weight:normal;font-size:18px; color:#ffffff;background-color:#354251;}
    table.iksweb td{font-size:14px;color:#354251;}
    table.iksweb td,table.iksweb th{white-space:pre-wrap;padding:20px 20px;line-height:14px;vertical-align: middle;border: 2px solid #354251;}	table.iksweb tr:hover{background-color:#f9fafb}
    table.iksweb tr:hover td{color:#354251;cursor:default;}
</style>
<table class="iksweb">
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Address</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${accidents}" var="accident">
        <tr>
            <td><c:out value="${accident.name}"/></td>
            <td><c:out value="${accident.text}"/></td>
            <td><c:out value="${accident.address}"/></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
