<%--
  Created by IntelliJ IDEA.
  User: Nguyen Viet Hien
  Date: 30/08/2022
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Welcome to spring java</h1>
<p>
    <a href="${pageContext.request.contextPath}/">Trang chu</a>

    <sec:authorize access="!hasAnyRole('ROLE_USER')">
        <a href="/dang-nhap">Dang nhap</a>
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_USER')">
        <a href="/nguoi-dung">Ca nhan</a>
        <a href="javascript:document.getElementById('logout').submit()">Logout</a>
    </sec:authorize>
    <tiles:insertAttribute name="body"/>
</p>
<form action="/j_logout" id="logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</body>
</html>
