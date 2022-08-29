<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>Hello Nguyen Viet Hien</h1>
<p>${ message }</p>
<tr>
    <td>Trang chu</td>
    <sec:authorize access="!hasAnyRole('ROLE_USER')">
        <td><a href="/login">Dang nhap</a></td>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_USER')">
        <td><a href="/nguoi-dung">Ca nhan</a></td>
        <td><a href="/logout">Logout</a></td>
    </sec:authorize>
</tr>
</body>
</html>