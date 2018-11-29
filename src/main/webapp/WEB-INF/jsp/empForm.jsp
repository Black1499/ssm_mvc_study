<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        input[value="批量添加"]{
            margin-left: 300px;
            margin-top: 20px;
            margin-bottom: 20px;
            height: 30px;
            width: 150px;
        }
        #empInfo{
            width: 60%;
            border:1px solid;
        }
    </style>
</head>
<body>
<form:form method="post" action="/addByForm" modelAttribute="employeeBean">
    <form:errors path="*"/>
<table id="empForm">
    <tr>
        <th>员工编号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>学历</th>
        <th>月薪</th>
        <th>添加</th>
        <th>移除</th>
    </tr>
    <tr>
        <td><input type="text" name="id"/></td>
        <td><input type="text" name="name"/></td>
        <td>
            <select name="sex">
                <option value="男">男<option>
                <option value="女">女<option>
            </select>
        </td>
        <td>
            <select name="education">
                <option value="专科">专科<option>
                <option value="本科">本科<option>
                <option value="硕士">硕士<option>
                <option value="博士">博士<option>
            </select>
        </td>
        <td><input type="text" name="salary"/></td>
        <td><input type="button" value="添加" /></td>
        <td><input type="button" value="移除" /></td>
    </tr>
</table>
</form:form>
<input type="button" value="批量添加">

<table id="empInfo">
    <tr>
        <th>员工编号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>学历</th>
        <th>月薪</th>
    </tr>
    <c:forEach var="emp" items="${empList}">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.name}</td>
            <td>${emp.sex}</td>
            <td>${emp.education}</td>
            <td>${emp.salary}</td>
        </tr>
    </c:forEach>
</table>
</body>
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script>
    var app={
        addForm:function(){
            var tr=$("#empForm tr")[1];
            $("#empForm").on("click","input[value='添加']",function () {
                $("#empForm").append($(tr).clone());
            });
        },
        delForm:function(){
            $("#empForm").on("click","input[value='移除']",function (){
                if($("#empForm tr").size()>2){
                    $(this.parentElement.parentElement).remove();
                }else{
                    alert("无法删除最后一行");
                }
            });
        },
        addEmployee:function(){
            $("input[value='批量添加']").click(function(){
                $("#empForm tr").each(function(index,object){
                   $("input[type=text], select",object).each(function (i,o) {
                       $(o).attr("name","empList["+(index-1)+"]."+$(o).attr("name"));
                   });
                });
                $("form").submit();
            });
        },
        init:function(){
            app.addForm();
            app.delForm();
            app.addEmployee();
        }
    };
    app.init();
</script>
</html>
