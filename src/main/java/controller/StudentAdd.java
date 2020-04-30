package controller;

import entity.Result;
import entity.Student;
import org.apache.http.client.utils.DateUtils;
import util.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-29 15:10
 **/
@WebServlet("/addStudent")
public class StudentAdd extends HttpServlet implements IdRegister {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setDefaultParameter(req,resp);
        Student student = new Student();
        reflectParameter(student,req);
        String id = getId();
        setId(student,id);
        RedisUtil.getInstance().SORTSET.zadd(ztableName,student.getAvgscore(),id);
        RedisUtil.getInstance().HASH.hset(htableName,id,student.toJSON());
        successData(resp,Result.success());
    }
}
