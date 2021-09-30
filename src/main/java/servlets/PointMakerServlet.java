package servlets;

import model.Result;
import model.ResultsBean;

import javax.faces.bean.ManagedBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static servlets.Utils.getHtmlDoubleString;

@WebServlet(urlPatterns = "/points")
public class PointMakerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultsBean resultsBean = new ResultsBean();
        resultsBean.setX(Utils.getDoubleParameter(req, "x"));
        resultsBean.setY(Utils.getDoubleParameter(req, "y"));
        resultsBean.setR(Utils.getDoubleParameter(req, "r"));
        if (resultsBean.getY() == -1000) {
            Result lastResult = resultsBean.getEntries().getLast();
            makePoint(lastResult, resp.getWriter(), resultsBean.getR());
        }
        else {
            if (resultsBean.getX() != -1000) resultsBean.submitResult();
            makePoints(resultsBean.getEntries(), resp.getWriter(),
                    resultsBean.getR());
        }
    }
    public void makePoints(List<Result> results, PrintWriter writer, double r) {
        for (Result result:  results) {
            makePoint(result, writer, r);
        }
    }

    public void makePoint(Result result, PrintWriter writer,double r) {
        String color = "red";
        if (result.result) color = "green";
        double x = ((result.x * 2) / r * 50 + 300 / 2.0) ;
        double y = (300 / 2.0 - (result.y * 2) / r * 50);

        writer.print(String.format("<circle r=\"5\" cx=%s cy=%s" +
                " id=\"pointer\" fill=%s></circle>", getHtmlDoubleString(x), getHtmlDoubleString(y), color
        ));
    }
}
