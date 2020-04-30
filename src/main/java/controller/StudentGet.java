package controller;

import entity.Result;
import entity.Student;
import util.Common;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-29 15:10
 **/
@WebServlet("/getStudent")
public class StudentGet extends HttpServlet implements IdRegister {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setDefaultParameter(req,resp);
        String id = req.getParameter("id");
        String hget = getRedisHash().hget(htableName, id);
        successData(resp, Result.successData(Common.toStudent(hget)));
//        req.getRequestDispatcher("/updateStudent.jsp").forward(req,resp);
    }
}
