package servlets;

import model.WelcomeBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/table", "/app.xhtml", "/points"})
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        WelcomeBean welcomeBean = (WelcomeBean) req.getSession().getAttribute("welcomeBean");
        if (welcomeBean == null) {
            System.out.println("Redirect because no name was provided.");
            req.getRequestDispatcher("/index.xhtml").forward(req, res);
        }
        else {
            String request = req.getRequestURL().toString();
            if (welcomeBean.getName().equals("") && !request.contains("index")) {
                System.out.println("Redirect because no name was provided.");
                req.getRequestDispatcher("/index.xhtml").forward(req, res);
            } else chain.doFilter(req, res);
        }
    }
}
