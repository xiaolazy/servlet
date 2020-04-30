package common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-21 14:26
 **/
@WebFilter(urlPatterns = "/**")
public class CrosHeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setHeader("Access-Control-Allow-Methods","PUT,GET,POST,HEAD,DELETE");
        resp.setHeader("Access-Control-Allow-Headers","*");
        req.setCharacterEncoding("utf-8");
//        resp.setHeader("Access-Control-Expose-Headers","etag,X-Powered-By");
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
