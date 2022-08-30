<%--
  Created by IntelliJ IDEA.
  User: Nguyen Viet Hien
  Date: 30/08/2022
  Time: 9:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h2>${message}</h2>
    </tiles:putAttribute>
</tiles:insertDefinition>
