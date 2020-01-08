<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<myTags:template>
    <jsp:attribute name="header">
        <h1>Error details</h1>
    </jsp:attribute>
    <jsp:body>
        <c:if test="${statusCode != 500}">
            <strong>Status Code</strong>: ${statusCode}<br/>
            <strong>Requested URI</strong>: ${requestUri}
        </c:if>
        <c:if test="${statusCode == 500}">
            <ul>
                <li>Servlet Name: ${servletName} </li>
                <li>Exception Name: ${throwableName} </li>
                <li>Requested URI: ${requestUri} </li>
                <li>Exception Message: ${throwableMessage} </li>
            </ul>
            <br><br>
            <a href="/mobiles/">Main page</a>
        </c:if>
    </jsp:body>
</myTags:template>