<%--
  Created by IntelliJ IDEA.
  User: Nguyen Viet Hien
  Date: 25/08/2022
  Time: 16:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" action="add">
    name: <form:input path="name"/>
    age: <form:input path="age"/>
    <button>Submit</button>
</form:form>
</body>
</html>
