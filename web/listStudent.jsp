<%--
  Created by IntelliJ IDEA.
  User: lyy
  Date: 2020/4/21
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%--<c:set var="url"><%=basePath%></c:set>--%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title></title>
    <link href="layui/css/layui.css" rel="stylesheet"/>
</head>
<body>
<a href="javascript:void(0)" type="button" onclick="opt.add()" class="layui-btn layui-btn-normal">新增</a>
<table class="layui-table" lay-skin="line">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="200">
        <col width="200">
        <col width="200">
    </colgroup>
    <thead>
    <tr>
        <%--        <th style="display: none">编号</th>--%>
        <th>姓名</th>
        <th>出生日期</th>
        <th>备注</th>
        <th>平均分</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="its" items="${rest}">
        <%--      <td style="display: none">${its.id}</td>--%>
        <tr>
            <td>${its.name}</td>
            <td>${its.birthday}</td>
            <td>${its.description}</td>
            <td>${its.avgscore}</td>
            <td>
                <a href="javascript:void(0)" type="button" onclick="opt.edit('${its.id}')"
                   class="layui-btn layui-btn-primary">编辑</a>
                <a href="javascript:void(0)" type="button" onclick="opt.del('${its.id}')"
                   class="layui-btn layui-btn-danger">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="demo0" class="layui-box layui-laypage layui-laypage-molv">
    <c:forEach begin="1" end="${count}" var="coun">
        <a href='<%=basePath%>listStudent?pageNum=${coun}' style="background-color:#1E9FFF;color: white">
            <em class="layui-laypage-em">${coun} </em>
        </a>
    </c:forEach>
</div>
<script src="layui/jquery.min.js"></script>
<script src="layui/layui.all.js"></script>
<script type="text/javascript">
    var count = '${count}', currPage = '${currPage}', basepath = '<%=basePath%>';
    var aj = {
        ajax(url, type, data, callback) {
            $.ajax({
                type: type,
                url: url,
                data: data,
                success: function (res) {
                    callback(res)
                }
            })
        }

    }
    layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage
            , layer = layui.layer;
    })
    var opt = {
        add() {
            layer.open({
                type: 2,
                content: '/addStudent.jsp',
                area: [800, 500],
                end: function () {
                    location.reload()
                }
            })
        },
        edit(id) {
            layer.open({
                type: 2,
                content: '/editStudent.jsp?id=' + id,
                area: [800, 500],
                end: function () {
                    location.reload()
                }
            })
        },
        del(id) {
            layer.confirm('确认删除？', {
                btn: ['确认', '取消'] //可以无限个按钮
            }, function (index, layero) {
                $.get(basepath + 'delStudent?id=' + id, res => {
                    var temp = JSON.parse(res)
                    if (temp.code == 200) {
                        layer.msg("删除成功");
                        location.reload()
                    } else layer.msg("系统错误")
                })
            }, function (index) {

            });
        },

    }
</script>
</body>
</html>
