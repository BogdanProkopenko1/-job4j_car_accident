<%@ page import="ru.job4j.accident.model.Accident" %>
<%@ page import="ru.job4j.accident.model.AccidentType" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.accident.model.Rule" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: pvzar
  Date: 23.09.2021
  Time: 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <title>Accident</title>
</head>
<%
    Accident accident = (Accident) request.getAttribute("accident");
    AccidentType type = (AccidentType) request.getAttribute("type");
    Rule rule = (Rule) request.getAttribute("rule");
%>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Edit Accident
            </div>
            <div class="card-body">
                <form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" value="<%= accident.getName() %>" name="name">
                        <label>Description</label>
                        <input type="text" class="form-control" value="<%= accident.getDescription() %>" name="description">
                        <label>Address</label>
                        <input type="text" class="form-control" value="<%= accident.getAddress() %>" name="address">
                        <br>
                        <label>Type</label>
                        <br>
                        <select required name="type.id">
                            <option selected value="<%=type.getId()%>"><%=type.getName()%></option>
                            <% for (AccidentType ac : (Collection<AccidentType>) request.getAttribute("types")) { %>
                            <option value="<%=ac.getId()%>"><%=ac.getName()%></option>
                            <% } %>
                        </select>
                        <br>
                        <br>
                        <label>Article</label>
                        <select required name="rIds" multiple>
                            <<% for (Rule r : (Collection<Rule>) request.getAttribute("rulesSelected")) { %>
                            <option selected value="<%=r.getId()%>"><%=r.getName()%></option>
                            <% } %>
                            <<% for (Rule r : (Collection<Rule>) request.getAttribute("rules")) { %>
                            <option value="<%=r.getId()%>"><%=r.getName()%></option>
                            <% } %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>