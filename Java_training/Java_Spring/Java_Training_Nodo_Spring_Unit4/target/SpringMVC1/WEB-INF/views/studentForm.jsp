<%--
  Created by IntelliJ IDEA.
  User: Nguyen Viet Hien
  Date: 25/08/2022
  Time: 16:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<form method="post" action="${pageContext.request.contextPath}/student/save">--%>
<%--    name: <input type="text" name="name">--%>
<%--    age: <input type="text" name="age">--%>
<%--    <button>Submit</button>--%>
<%--</form>--%>

<form:form method="post" action="${pageContext.request.contextPath}/student/save" modelAttribute="command">
    <form:hidden path="id"/>
    name: <form:input type="text" path="name"/>
    <form:errors path="name"/>
    age: <form:input type="text" path="age"/>
    <form:errors path="age"/>
    <button>Submit</button>
</form:form>

<c:if test="${id != null}">
    <h1>Please upload a image</h1>
    <form:form method="post" action="../avatar/save" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${id}">
        <input type="file" name="file">
        <input type="submit" value="upload">
    </form:form>
</c:if>


</body>
</html>
