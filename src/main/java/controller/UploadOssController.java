package controller;

import com.aliyun.oss.OSSClient;
import util.Common;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-21 16:32
 **/
@WebServlet(urlPatterns = "/upload")
@MultipartConfig
public class UploadOssController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        String disposition = part.getHeader("Content-Disposition");
        String suffix = disposition.substring(disposition.lastIndexOf("."),disposition.length()-1);
        System.out.println(suffix);
        InputStream is = part.getInputStream();
        String path = Common.uploadObject2OSS(is, suffix);
        req.setAttribute("path",path);
        is.close();
        req.getRequestDispatcher("/topicTwoEcho.jsp").forward(req,resp);
    }

//    private String getFileName(String suffix){
//        return suffix.substring(suffix.indexOf("filename=\""),suffix.length()-1);
//    }
}
