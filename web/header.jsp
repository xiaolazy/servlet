<%--
  Created by IntelliJ IDEA.
  User: lyy
  Date: 2020/4/21
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--  String path = request.getContextPath();--%>
<%--  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
<%--%>--%>
<%--<c:set var="url"><%=basePath%></c:set>--%>
<html>
  <head>
    <title></title>
    <link href="layui/css/layui.css" rel="stylesheet"/>
    <script src="layui/jquery.min.js"></script>
    <script src="layui/layui.all.js"></script>
    <script>
      var aj = {
        ajax(url,type,data,callback){
          $.ajax({
            type:type,
            url:url,
            data:data,
            success:function (res) {
              callback(res)
            }
          })
        }

      }
    </script>

