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

    <%@include file="/header.jsp" %>
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
    </tbody>
    <tbody>
    <c:forEach var="its" items="${rest}">
<%--      <td style="display: none">${its.id}</td>--%>
     <tr>
         <td>${its.name}</td>
         <td>${its.birthday}</td>
         <td>${its.description}</td>
         <td>${its.avgscore}</td>
         <td>
             <a href="javascript:void(0)" type="button" onclick="opt.edit('${its.id}')" class="layui-btn layui-btn-primary">编辑</a>
             <a href="javascript:void(0)" type="button" onclick="opt.del('${its.id}')" class="layui-btn layui-btn-danger">删除</a>
         </td>
     </tr>
    </c:forEach>
    </tbody>
  </table>
  <div id="demo0" class="layui-box layui-laypage layui-laypage-molv">
      <c:forEach begin="1" end="${count}" var="coun">
          <a href='<%=basePath%>listStudent?pageNum=${coun}' style="background-color:#1E9FFF;color: white">
            <em class="layui-laypage-em" >${coun} </em>
          </a>
      </c:forEach>
  </div>
  <script>
    var count = '${count}',currPage = '${currPage}',basepath =' <%=basePath%>';
    layui.use(['laypage', 'layer'], function() {
      var laypage = layui.laypage
              , layer = layui.layer;

      // //总页数低于页码总数
      // laypage.render({
      //   elem: 'demo0'
      //   , count: count
      //     ,layout: ['prev', 'next']
      //     ,prev: '<em><a href="'+basepath+"listStudent?pageNum="+(currPage-1)+'">上一页</a></em>'
      //     ,next: '<em><a href="'+basepath+"listStudent?pageNum="+(currPage+1)+'">下一页</a></em>'
      // });


    })
    var opt = {
      add() {
        layer.open({
          type: 2,
          content: '/addStudent.jsp',
          area:[800,500],
          end:function () {
            location.reload()
          }
        })
      },
      edit(id) {
        layer.open({
          type: 2,
          content: '/editStudent.jsp?id='+id,
          area:[800,500],
            end:function () {
                location.reload()
            }
        })
      },
      del(id) {
          layer.confirm('确认删除？', {
              btn: ['确认', '取消'] //可以无限个按钮
          }, function(index, layero){
              $.get(basepath+'delStudent?id='+id,res=>{
                  var temp = JSON.parse(res)
                  if(temp.code == 200) {layer.msg("删除成功") ;location.reload()}
                  else layer.msg("系统错误")
              })
          }, function(index){

          });
      },

    }
  </script>
  </body>
</html>
