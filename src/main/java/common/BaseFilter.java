package common;


import redis.clients.jedis.JedisPoolConfig;
import util.RedisUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-21 10:42
 **/
//@WebFilter(urlPatterns = "/topicOnce")
public class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HandleReqNumber.handleTopicOne();
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {

    }
}
