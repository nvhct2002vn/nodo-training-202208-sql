<%--
  Created by IntelliJ IDEA.
  User: Nguyen Viet Hien
  Date: 25/08/2022
  Time: 16:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    name: <form:input type="text" path="name"/>
    <form:errors path="name"/>
    age: <form:input type="text" path="age"/>
    <form:errors path="age"/>
    <button>Submit</button>
</form:form>

</body>
</html>
