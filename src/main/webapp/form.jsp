<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="mobile" class="com.inno.pojo.Mobile" />
<%--Начальные значения--%>
<c:set target="${mobile}" property="model" value="${editingMobile==null?'model_A':editingMobile.model}" />
<jsp:setProperty name="mobile" property="price" value="${editingMobile==null?'0':editingMobile.price}" />
<jsp:setProperty name="mobile" property="manufacturer" value="${editingMobile==null?'manufacturer_A':editingMobile.manufacturer}" />

<c:if test="${editingMobile == null}">
    <h1>Adding a new phone</h1>
    <form method="post" action="${pageContext.request.contextPath}/addmobile" autocomplete="off">
</c:if>
<c:if test="${editingMobile != null}">
    <h1>Editing phone</h1>
    <form method="post" action="${pageContext.request.contextPath}/editmobile" autocomplete="off">
    <input type="hidden" name="id" value="<c:out value='${editingMobile.id}' />" />
</c:if>
    <div class="form-group">
        <label for="model">Model</label>
        <input name="model" type="text" class="form-control" id="model" value="<jsp:getProperty name="mobile" property="model" />">
    </div>
    <div class="form-group">
        <label for="price">Price</label>
        <input name="price" type="text" class="form-control" id="price" value="<jsp:getProperty name="mobile" property="price" />">
    </div>
    <div class="form-group">
        <label for="manufacturer">Manufacturer</label>
        <input name="manufacturer" type="text" class="form-control" id="manufacturer" value="<jsp:getProperty name="mobile" property="manufacturer" />">
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form>
