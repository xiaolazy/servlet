package controller;

import util.Common;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-21 16:32
 **/
@WebServlet(urlPatterns = "/topicTwo")
public class ViewJumpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/topicTwo.jsp").forward(req,resp);
    }
}
