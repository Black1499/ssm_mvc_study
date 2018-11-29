<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        input[value="批量添加"] {
            margin-left: 300px;
            margin-top: 20px;
            margin-bottom: 20px;
            height: 30px;
            width: 150px;
        }

        #empInfo {
            width: 60%;
            border: 1px solid;
        }
        button{
            margin-left: 150px;
            margin-top: 20px;
            margin-bottom: 20px;
            height: 30px;
            width: 150px;
        }
        #myFile{
            display: none;
        }
    </style>
</head>
<body>
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
        <td><input type="text" class="id"/></td>
        <td><input type="text" class="name"/></td>
        <td>
            <select class="sex">
                <option value="男">男
                <option>
                <option value="女">女
                <option>
            </select>
        </td>
        <td>
            <select class="education">
                <option value="专科">专科
                <option>
                <option value="本科">本科
                <option>
                <option value="硕士">硕士
                <option>
                <option value="博士">博士
                <option>
            </select>
        </td>
        <td><input type="text" class="salary"/></td>
        <td><input type="button" value="添加"/></td>
        <td><input type="button" value="移除"/></td>
    </tr>
</table>

<input type="button" value="批量添加">

<table id="empInfo">
    <tr>
        <th>员工编号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>学历</th>
        <th>月薪</th>
    </tr>
</table>

<button id="outExcel">导出</button>
<button id="inExcel">导入</button><h4></h4>

<input type="file" id="myFile" multiple="multiple"/>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script>
    var app = {
        blindData: function () {
            $("#empInfo tr:gt(0)").remove();
            $.get("/listAll", "", function (data) {
                var html = "";
                $.each(data, function (index, obj) {
                    html += "<tr>";
                    html += "<th>" + obj.id + "</th>";
                    html += "<th>" + obj.name + "</th>";
                    html += "<th>" + obj.sex + "</th>";
                    html += "<th>" + obj.education + "</th>";
                    html += "<th>" + obj.salary + "</th>";
                    html += "</tr>";
                });
                $("#empInfo").append(html);
            });
        },
        addForm: function () {
            $("#empForm").on("click", "input[value='添加']", function () {
                var html = "";
                html += "<tr>";
                html += "<td><input type='text' class='id'/></td>";
                html += "<td><input type='text' class='name'/></td>";
                html += "<td>";
                html += "<select class='sex'>";
                html += "<option value='男'>男</option>";
                html += "<option value='女'>女</option>";
                html += "</select>";
                html += "</td>";
                html += "<td>";
                html += "<select class='education'>";
                html += "<option value='专科'>专科</option>";
                html += "<option value='本科'>本科</option>";
                html += "<option value='硕士'>硕士</option>";
                html += "<option value='博士'>博士</option>";
                html += "</select>";
                html += "</td>";
                html += "<td><input type='text' class='salary'/></td>";
                html += "<td><input type='button' value='添加' /></td>";
                html += "<td><input type='button' value='移除' /></td>";
                html += "</tr>";
                $("#empForm").append(html);
            });
        },
        delForm: function () {
            $("#empForm").on("click", "input[value='移除']", function () {
                if ($("#empForm tr").length > 2) {
                    $(this.parentElement.parentElement).remove();
                } else {
                    alert("无法删除最后一行");
                }
            });
        },
        empList: [],
        empListAdd: function () {
            var size = $("#empForm tr").size();
            for (var i = 0; i < size - 1; i++) {
                app.empList.push({
                    id: $(".id")[i].value,
                    name: $(".name")[i].value,
                    sex: $(".sex")[i].value,
                    education: $(".education")[i].value,
                    salary: $(".salary")[i].value
                });
            }
        },
        addEmployee: function () {
            $("input[value='批量添加']").click(function () {
                app.empListAdd();
                console.log(app.empList);
                $.ajax({
                    url: "/addList",
                    data: JSON.stringify(app.empList),
                    contentType: "application/json",
                    type: "POST",
                    success: function () {
                        alert("添加成功");
                        app.blindData();
                    }
                });
                app.empList = [];
            });
        },
        outExcel:function(){
          $("#outExcel").click(function () {
             window.location.href="/emp/outExcel";
          });
        },
        inExcel:function(){
            $("#inExcel").click(function () {
               $("#myFile").click();
               $("#myFile").change(function(){
                   var formData = new FormData();
                   formData.append("file",document.getElementById("myFile").files[0]);
                   $.ajax({
                       type:"post",
                       url:"/emp/inExcel",
                       data: formData,
                       cache: false,
                       contentType: false, //必须false才会自动加上正确的Content-Type
                       processData: false,  //必须false才会避开jQuery对 formdata 的默认处理
                   }).done(function(data){
                       alert(data);
                       app.blindData();
                   });
               });
            });
        },
        init: function () {
            app.addForm();
            app.delForm();
            app.addEmployee();
            app.blindData();
            app.outExcel();
            app.inExcel();
        }
    };
    app.init();
</script>
</html>
