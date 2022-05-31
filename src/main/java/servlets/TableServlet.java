package servlets;

import model.ResBeanBuilder;
import model.Result;
import model.ResultsBeanPoints;

import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.Utils.getHtmlDoubleString;

@WebServlet(urlPatterns = "/table")
public class TableServlet extends HttpServlet {
    ResultsBeanPoints resultsBean = ResBeanBuilder.getBean();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result lastResult = resultsBean.getEntries().getFirst();
        resp.getWriter().println(String.format("<tr>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s</td>" +
                        "<td>%s ms</td>" +
                        "</tr>", getHtmlDoubleString(lastResult.getX()), getHtmlDoubleString(lastResult.getY()),
                getHtmlDoubleString(lastResult.getR()),
                lastResult.getResult() ? "Yes" : "No", lastResult.getQuery(), lastResult.getTime()));
    }
}
