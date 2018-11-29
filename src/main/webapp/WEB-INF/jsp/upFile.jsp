
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${msg}</h1>
<form action="/up" method="post" enctype="multipart/form-data">
    <label>文件上传:</label>
    <input type="file" name="file"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
