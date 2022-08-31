<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Add group</h1>
        <form:form action="${pageContext.request.contextPath}/group/add" method="post">
            <form:hidden path="id"/>

            <p>
                <label>
                    <form:input type="text" path="name" placeholder="Name"/>
                </label>
            </p>
            <p>
                <input type="submit" name="add" value="Add">
            </p>
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>