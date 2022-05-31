package servlets;

import db.DatabaseManager;
import model.ResBeanBuilder;
import model.Result;
import model.ResultsBeanPoints;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static servlets.Utils.getHtmlDoubleString;
import static servlets.Utils.isInArea;

@WebServlet(urlPatterns = "/points")
public class PointMakerServlet extends HttpServlet {
    ResultsBeanPoints resultsBean = ResBeanBuilder.getBean();


    public PointMakerServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resultsBean.setX(Utils.getDoubleParameter(req, "x"));
        resultsBean.setY(Utils.getDoubleParameter(req, "y"));
        if (resultsBean.getEntries().size() > 0) {
            resultsBean.setR(resultsBean.getEntries().getFirst().getR());
            // nothing in db to use it as last submittion. just using default value = 2
        } else {
            resultsBean.setR(2.0);
        }
//        resultsBean.setR(Utils.getDoubleParameter(req, "r"));
        if (resultsBean.getX() != -1000) {
            resultsBean.submitResult();
        }
        makePoints(resultsBean.getEntries(), resp.getWriter(), resultsBean.getR());
    }

    public void makePoints(List<Result> results, PrintWriter writer, double r) {
        for (Result result : results) {
            makePoint(result, writer, r);
        }
    }

    public void makePoint(Result result, PrintWriter writer, double r) {
        String color = "red";
        double x = ((result.x * 2) / r * 60.0 + 150.0);
        double y = (150.0 - (result.y * 2) / r * 60.0);
        if (isInArea(result.x, result.y, r)) color = "green";

        writer.print(String.format("<circle r=\"5\" cx=%s cy=%s" +
                " id=\"pointer\" fill=%s></circle>", getHtmlDoubleString(x), getHtmlDoubleString(y), color
        ));
    }

    @Override
    public void destroy() {
        DatabaseManager.destroyInstance();
        super.destroy();
    }
}
