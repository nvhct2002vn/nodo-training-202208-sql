<%--
  Created by IntelliJ IDEA.
  User: Nguyen Viet Hien
  Date: 31/08/2022
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Add user</h1>
        <form:form method="post" action="/user/add">
            <p>username:
                <form:input path="username"/>
            </p>
            <p>password:
                <form:password path="password"/>
            </p>
            <p>email:
                <form:input path="email"/>
            </p>
            <p>age:
                <form:input path="age"/>
            </p>
            <p>group:
                <form:select id="group" name="group" path="groupId">
                    <form:options items="${groups}"/>
                </form:select>
            </p>
            <input type="submit" value="add">
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>
