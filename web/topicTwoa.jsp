<%--
  Created by IntelliJ IDEA.
  User: lyy
  Date: 2020/4/21
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="http://gosspublic.alicdn.com/aliyun-oss-sdk-6.0.2.min.js"></script>
    <script src="layui/jquery.min.js"></script>
    <script src="layui/layui.all.js"/>
    <script>

    </script>
  </head>
  <body>

  <a href="<%=basePath%>" class="layui-btn">回到主页</a>
  <div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header"><h2>直传阿里云</h2></div>
          <div class="layui-card-body">
            <input type="file" name="file" id="file" />
            <div id="echo">

            </div>
          </div>
        </div>
      </div>
  </div>
    <script>
      $(function () {
        $('#file').on('change',function (e) {
          console.log(11)
          let file = e.target.files[0];
          let storeAs = '1919/';
          for(let i = 0; i < 2 ;i++){
            var temp = Math.floor(Math.random() * 11111111111111);
            storeAs+=temp.toString(16)
          }
          console.log(file.name + ' => ' + storeAs);
          // OSS.urlib是sdk内部封装的发送请求的逻辑，开发者可以使用任何发送请求的库向`sts-server`发送请求

          let client = new OSS({
            endpoint: 'oss-cn-beijing.aliyuncs.com/',
            accessKeyId: 'LTAI7Ix89ALe4rhV',
            accessKeySecret: '1VIaYHuqL23wuDb2vh9MEh99N3fj3V',
            bucket: 'icanlyy'
          });
          //storeAs表示上传的object name , file表示上传的文件
          var index = layer.load();
          client.multipartUpload(storeAs, file).then(function (result) {
            let res = result.res;
            if(res.status == 200){
              layer.msg("上传成功")
              $('#echo').empty()
              let url = res.requestUrls[0].split('?')
              $('#echo').append('<a type="button" href="'+url[0]+'" class="layui-btn" >点击获取</a>')
              layer.close(index)
            }else{
              layer.msg("服务器错误!")
              layer.close(index)
            }
            console.log(res);
          })
        })
      })
    </script>
  </body>
</html>