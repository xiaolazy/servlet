package controller;

import entity.PageResult;
import entity.Student;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import util.Common;
import util.RedisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-29 15:11
 **/
@WebServlet(urlPatterns = {"/listStudent"})
public class StudentList extends HttpServlet implements IdRegister {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("pageNum");
        Integer num = StringUtils.isBlank(pageNum) ? 1 : Integer.valueOf(pageNum);
        Set<String> zrevrange = RedisUtil.getInstance().SORTSET.zrevrange(ztableName, (num-1) * 10, num * 10 -1);
        String[] keys = (String[])zrevrange.toArray(new String[zrevrange.size()]);
        if(keys == null || keys.length < 1) {
            req.getRequestDispatcher("/listStudent.jsp").forward(req,resp);
            return;
        }
        List<String> temp = getRedisHash().hmget(htableName, keys);
        List<Student> rest =new ArrayList();
        temp.forEach(e->{rest.add( Common.toStudent(e));});
        req.setAttribute("rest",rest);
        req.setAttribute("currPage",num);
        req.setAttribute("count",RedisUtil.getInstance().SORTSET.zcard(ztableName)/10 + 1);
        req.getRequestDispatcher("/listStudent.jsp").forward(req,resp);
        successData(resp, PageResult.successData(zrevrange));
    }
    public static void main(String[] args) {
        System.out.println(26/10+1);
        System.out.println(1/10+1);
        System.out.println(0/10+1);
        System.out.println(12/10+1);
        System.out.println(33/10+1);
        System.out.println(65/10+1);
    }
}
