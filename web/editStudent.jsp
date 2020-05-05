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
<form class="layui-form layui-form-pane" id="demo2" lay-filter="example">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="title" value="${entity.name}" autocomplete="off"
                   placeholder="请输入姓名" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">日期选择</label>
        <div class="layui-input-block">
            <input type="text" name="birthday" value="${entity.birthday}" id="date1" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">平均分</label>
        <div class="layui-input-block">
            <input type="text" name="avgscore" value="${entity.avgscore}" lay-verify="number" autocomplete="off"
                   placeholder="请输入平均分" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入备注" value="${entity.description}" class="layui-textarea"
                      name="description"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <a type="submit" href="javascript:void(0)" class="layui-btn" lay-submit lay-filter="demo2">立即提交</a>
            <a type="button" href="javascript:void(0)" class="layui-btn" onclick="cancel()">取消</a>
        </div>
    </div>
</form>
<script src="layui/jquery.min.js"></script>
<script src="layui/layui.all.js"></script>
<script type="text/javascript">
    var basepath = '<%=basePath%>', tempId = '';
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
    $(function () {
        var url = location.href.split("="), id = url[1];

        layui.use(['form', 'layer', 'laydate'], function () {
            var form = layui.form
                , layer = layui.layer
                , laydate = layui.laydate;

            laydate.render({
                elem: '#date1'
            });
            $.get(basepath + "getStudent?id=" + id, res => {
                let e = JSON.parse(res);
                if (e.code == 200) {
                    let data = e.data;
                    console.log(data)
                    tempId = data.id;
                    form.val("example", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                        "name": data.name // "name": "value"
                        , "birthday": data.birthday
                        , "description": data.description
                        , "avgscore": data.avgscore
                    });
                    setTimeout(() => {
                        form.render()
                    }, 400)
                } else
                    layer.msg("系统错误")
            });

            form.on('submit(demo2)', function (data) {
                var index = layer.load()
                data.field['id'] = tempId;
                console.log(data.field)
                aj.ajax(basepath + 'updateStudent', 'post', data.field, (res) => {
                    var temp = JSON.parse(res)
                    if (temp.code == 200) {
                        cancel();
                        layer.msg("提交成功")
                    } else {
                        layer.close(index)
                        layer.msg("系统错误")
                    }
                })
            })
            form.render()
        })

    })

    function cancel() {
        layer.closeAll()
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }
</script>
</body>
</html>
