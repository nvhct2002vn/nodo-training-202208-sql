<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
<form action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    Username: <input type="text" name="j_username">
    Password: <input type="password" name="j_password">
    <button>Login</button>
    <input type="submit" name="commit" value="Login">
</form>
</body>
</html>