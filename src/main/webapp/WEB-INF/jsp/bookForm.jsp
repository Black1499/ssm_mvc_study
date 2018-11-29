<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/addBook" method="post" modelAttribute="book">
    <%--form:input为接收用户输入--%>
    <%--form:errors为输入错误时，后台返回的错误信息（来自注解中的message）--%>
    <label>名称</label>
    <form:input path="name" value="${book.name}" />
    <form:errors path="name"/><br/>

    <label>价格</label>
    <form:input path="price" value="${book.price}"/>
    <form:errors path="price"/><br/>

    <label>号码</label>
    <form:input path="phone" value="${book.phone}"/>
    <form:errors path="phone"/><br/>
    <input type="submit"/>
</form:form>
</body>
</html>
