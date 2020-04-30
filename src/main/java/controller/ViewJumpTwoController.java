package controller;

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
 * @generate: 2020-04-21 16:32
 **/
@WebServlet(urlPatterns = "/topicTwoa")
public class ViewJumpTwoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/topicTwoa.jsp").forward(req,resp);
    }
}
