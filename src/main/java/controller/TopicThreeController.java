package controller;

import entity.Header;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-21 14:56
 **/
@WebServlet(urlPatterns = "/topicThree")
@SuppressWarnings("all")
public class TopicThreeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        req.setAttribute("cookies",cookies);
        Enumeration<String> headerNames = req.getHeaderNames();
        List<Header> reqList = new ArrayList<>();
        List<Header> respList = new ArrayList<>();
        while (headerNames.hasMoreElements()){
            String tempName = headerNames.nextElement();
            if(headerNames != null && tempName!= null){
                String tempVal = req.getHeader(tempName);
                reqList.add(Header.builder().key(tempName)
                        .name(tempVal == null ? "null" : tempVal).build());
            }
        }
        Collection<String> respHeader = resp.getHeaderNames();
        respHeader.forEach(e->{
            System.out.println(e+"----"+resp.getHeader(e));
            respList.add(Header.builder().key(e)
                    .name(req.getHeader(e)).build());
        });
        req.setAttribute("reqHeaders",reqList);
        req.setAttribute("respHeaders",reqList);
        req.setAttribute("now",new Date());
//        req.setAttribute("random",String.format("%.2f",Double.valueOf(System.currentTimeMillis())));
        req.setAttribute("random",System.currentTimeMillis());
        req.getRequestDispatcher("/topicThree.jsp").forward(req,resp);
    }
    public static void main(String[] args) {
    }
}
