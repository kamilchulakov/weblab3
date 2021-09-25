package servlets;

import model.Result;
import model.ResultsBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class AddingDataFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = req.getSession();
        ResultsBean resultsBean = new ResultsBean();
        resultsBean.addEntry(new Result(1.0,2.0, 3.0, false, new Date(), new Date()));
//        httpSession.setAttribute("resultsBean", resultsBean);
        chain.doFilter(req, res);
    }
}
