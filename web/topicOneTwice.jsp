<%--
  Created by IntelliJ IDEA.
  User: lyy
  Date: 2020/4/21
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title></title>
    <link href="layui/css/layui.css" rel="stylesheet"/>
    <style>
      .layui-card{
        min-height: 200px;
      }
    </style>
  </head>
  <body>

  <a href="<%=basePath%>" class="layui-btn">回到主页</a>
  <div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row">
      <div class="layui-col-md6">
        <div class="layui-card">
          <div class="layui-card-header"><h2>customize header</h2></div>
          <div class="layui-card-body">
            ${customize_header}
          </div>
        </div>
      </div>
      <div class="layui-col-md6">
        <div class="layui-card">
          <div class="layui-card-header"><h2>cookies</h2></div>
          <div class="layui-card-body">
            <table class="layui-table">
              <colgroup>
                <col width="150">
                <col width="150">
              </colgroup>
              <tbody>
              <c:forEach items="${cookies}" var="c">
                <tr>
                  <td>${c.name}</td>
                  <td>${c.value}</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="layui/jquery.min.js"/>
  <script src="layui/layui.all.js"/>
  <script>
    layui.use(['element', 'layer'], function(){
      var element = layui.element;
      var layer = layui.layer;

      //监听折叠
      element.on('collapse(test)', function(data){
        layer.msg('展开状态：'+ data.show);
      });
    });
  </body>
</html>
