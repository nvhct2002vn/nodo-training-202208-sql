<%--
  Created by IntelliJ IDEA.
  User: Nguyen Viet Hien
  Date: 30/08/2022
  Time: 16:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="template">

    <tiles:putAttribute name="body">
        <h1>List</h1>
        <a href="/user/form">Create</a>
        <form action="/user/list" method="get">
            <input type="text" name="q">
        </form>
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Age</th>
                <th>Group Id</th>
                <th>Control</th>
            </tr>
            <c:forEach items="${listUser}" var="item">
                <tr>
                    <td>${item.username}</td>
                    <td>${item.password}</td>
                    <td>${item.email}</td>
                    <td>${item.age}</td>
                        <%--                    <td>${item.groupId}</td>--%>
                    <td>${item.group.name}</td>
                    <td><a href="/user/delete/${item.username}">Delete</a></td>
                    <td><a href="/user/edit/${item.username}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
        <p>diem tb: ${avg}</p>
    </tiles:putAttribute>

</tiles:insertDefinition>
