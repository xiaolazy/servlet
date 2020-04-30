<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lyy
  Date: 2020/4/21
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title></title>
    <link href="layui/css/layui.css" rel="stylesheet"/>
  </head>
  <body>
  我是TOMCAT 4444444444
  <s:iterator value="new int[10]" status="i">
    <s:property value="#i.index"/>
  </s:iterator>
  <a href="<%=basePath%>topicOnce" class="layui-btn">题目1</a>
  <a href="<%=basePath%>topicTwo" class="layui-btn">题目2Servlet中转</a>
  <a href="<%=basePath%>topicTwoa" class="layui-btn">题目2直传</a>
  <a href="<%=basePath%>topicThree" class="layui-btn">题目3</a>
  <script src="layui/jquery.min.js"/>
  <script src="layui/layui.all.js"/>
  </body>
</html>
