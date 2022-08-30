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
        <table border="1">
            <tr>
                <th>Name</th>
                <th>#</th>
            </tr>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td><a href="/group/delete/${item.id}">Delete</a></td>
                    <td><a href="/group/edit/${item.id}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </tiles:putAttribute>

</tiles:insertDefinition>
