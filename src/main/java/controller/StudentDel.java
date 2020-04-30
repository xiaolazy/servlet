package controller;

import entity.Result;
import util.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-29 15:10
 **/
@WebServlet("/delStudent")
public class StudentDel extends HttpServlet implements IdRegister {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setDefaultParameter(req,resp);
        String id = req.getParameter("id");
        long zrem = RedisUtil.getInstance().SORTSET.zrem(ztableName, id);
        long id1 = getRedisHash().hdel(htableName,id);
        successData(resp, Result.success());
    }
}
