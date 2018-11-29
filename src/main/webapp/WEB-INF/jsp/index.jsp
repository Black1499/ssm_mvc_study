<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>所有作者</h4>
<ul>
    <c:forEach items="${authors}" var="author">
        <li>${author.id},${author.name},${author.birthday},${author.sex}</li>
    </c:forEach>
</ul>

<form method="post" action="/">
    <label>作者</label>
    <select name = "author_id">
        <c:forEach items="${authors}" var="author">
            <option value="${author.id}">${author.name}</option>
        </c:forEach>
    </select><br/>
    <input type="text" name="tilte" placeholder="标题" /><br />
    <textarea name="context">
    </textarea>
    <input type="submit" />
</form>

<c:forEach items="${articles}" var="article">
    <li>${article.tilte}</li>
</c:forEach>
</body>
</html>
