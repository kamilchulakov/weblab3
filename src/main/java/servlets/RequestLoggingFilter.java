package servlets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class RequestLoggingFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.printf("Request Url:%s Method:%s Session:%s Data:%s\n", req.getRequestURL(), req.getMethod(),
                req.getSession().getId(), req.getParameterMap().values().size());
        chain.doFilter(req, res);
    }
}
