<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Login</h1>
        <c:if test="${not empty error}">
            <p>Sai tai hoan hoac mat khau</p>
        </c:if>
        <form action="/login" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            Username: <input type="text" name="username">
            Password: <input type="password" name="password">
            <button>Login</button>
            <input type="submit" name="commit" value="Login">
        </form>
    </tiles:putAttribute>
</tiles:insertDefinition>
</body>
</html>