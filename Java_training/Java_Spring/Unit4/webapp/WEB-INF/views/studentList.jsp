<%--
  Created by IntelliJ IDEA.
  User: Nguyen Viet Hien
  Date: 26/08/2022
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <!-- CSS only -->
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"--%>
    <%--          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">--%>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/student/list">
    <table class="table">
        <thead>
        <tr>
            <td colspan="4"><input type="text" name="q" size="30"></td>
            <td><input type="submit" value="Submit"></td>
        </tr>
        <tr>
            <th scope="col">id</th>
            <th scope="col">Name</th>
            <th scope="col">Age</th>
            <th scope="col">Control</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${studentList}" var="std">
            <tr>
                <td>${std.id}</td>
                <td>${std.name}</td>
                <td>${std.age}</td>
                <td><a href="/student/delete/${std.id}">Delete</a></td>
                <td><a href="/student/edit/${std.id}">Edit</a></td>
                <td><a href="javascript:view(${std.id})">${std.name}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<dialog id="viewStudent" style="width:50%;border: 1px dotted black;">
    <div id="content"></div>
    <button id="hide">Close</button>
</dialog>
<!-- JavaScript Bundle with Popper -->
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"--%>
<%--        crossorigin="anonymous"></script>--%>

<script>
    function view(id) {
        const xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", "json/" + id, true);
        xmlHttp.onload = function () {
            console.log(this);
            if (this.status !== 200) {
                return;
            }
            console.log(this.responseText);
            var student = JSON.parse(this.responseText);
            document.getElementById('content').innerHTML = 'Name: ' + student.name + '<img src="/student/avatar/' + student.id + '"/>';
            var dialog = document.getElementById('viewStudent');
            dialog.show();
        };
        xmlHttp.send();
    }

    var dialog = document.getElementById('viewStudent');
    document.getElementById('hide').onclick = function () {
        dialog.close();
    };

    // var student = JSON.parse(this.responseText);
    // document.getElementById('content').innerHTML = 'Name: ' + student.name + '<img src=/student/avatar/' + student.id + "'/>';
</script>
</body>
</html>
