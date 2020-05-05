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
            <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入姓名"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">日期选择</label>
        <div class="layui-input-block">
            <input type="text" name="birthday" id="date1" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">平均分</label>
        <div class="layui-input-block">
            <input type="text" name="avgscore" lay-verify="number" autocomplete="off" placeholder="请输入平均分"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入备注" class="layui-textarea" name="description"></textarea>
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
    var basepath = '<%=basePath%>';
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
    layui.use(['form', 'layer', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;

        laydate.render({
            elem: '#date1'
        });
        form.render()
        form.on('submit(demo2)', function (data) {
            var index = layer.load()
            aj.ajax(basepath + 'addStudent', 'post', data.field, (res) => {
                var temp = JSON.parse(res)
                if (temp.code == 200) {
                    cancel()
                    layer.msg("提交成功")
                } else {
                    layer.close(index)
                    layer.msg("系统错误")
                }
            })
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
