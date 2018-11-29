<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title"/></title>
    <!--就在这拿到css连接-->
    <link rel="stylesheet" href="<spring:theme code="css.link"/>"/>

</head>
<body>
<h1><spring:message code="order.voice" arguments="${msg}"/></h1>

<a href="/language/zh">中文</a>
<a href="/language/en">英文</a><br/>


<a href="/themes/yellow">黄色主题</a>
<a href="/themes/blue">蓝色主题</a>

<form:form method="post" action="/addOrder" modelAttribute="order">
    <spring:message code="order.id"/>
    <form:input path="id"/>
    <form:errors path="id"/><br/>

    <spring:message code="order.name"/>
    <form:input path="name"/>
    <form:errors path="name"/><br/>

    <spring:message code="order.email"/>
    <form:input path="email"/>
    <form:errors path="email"/><br/>

    <spring:message code="order.price"/>
    <form:input path="price"/>
    <form:errors path="price"/> <br/>
    <input type="submit"/>
</form:form>

</body>
</html>