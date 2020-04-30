package controller;

import com.sun.xml.internal.ws.util.StringUtils;
import common.HandleReqNumber;
import entity.Header;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-21 09:41
 **/
@WebServlet(urlPatterns = "/topicOnce")
@SuppressWarnings("all")
public class TestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(HandleReqNumber.getTopicOne() == 0) twiceReq(req,resp);
        else onceReq(req,resp);
    }
    private void twiceReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cookie[] cookies = req.getCookies();
        for (int i = 0; i <cookies.length ; i++) {
            if(cookies[i] != null){
                if("customize_header".equals(cookies[i].getName()))
                    req.setAttribute("customize_header",cookies[i].getValue());
            }
        }
        req.setAttribute("cookies",cookies);
        req.getRequestDispatcher("/topicOneTwice.jsp").forward(req,resp);
    }
    protected Cookie getCookie(){
        Cookie cookie = new Cookie("customize_header","I_can_cache_for_a_day");
        cookie.setMaxAge(60*60*24);
        return cookie;
    }
    private void onceReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userIp",handleIp(req.getLocalAddr()+":"+req.getLocalPort()));
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
        resp.addCookie(getCookie());
        resp.setDateHeader("expires",30);
        req.getRequestDispatcher("/topicOne.jsp").forward(req,resp);
    }
    private String handleIp(String ip){
        if(ip == null || ip.equals(""))
            return "未知IP";
        if(ip.contains("0:0:0:0:0:0:0:1"))
            return ip.replace("0:0:0:0:0:0:0:1","127.0.0.1");
        return ip;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
