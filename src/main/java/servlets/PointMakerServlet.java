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
        if (Utils.getDoubleParameter(req, "y") == -1000) {
            System.out.println("I was in -1000 points");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ResultsBean resultsBean = new ResultsBean();
            Result lastResult = resultsBean.getEntries().getFirst();
            makePoint(lastResult, resp.getWriter(), resultsBean.getR());
        }
        else {
            ResultsBean resultsBean = new ResultsBean();
            resultsBean.setX(Utils.getDoubleParameter(req, "x"));
            resultsBean.setY(Utils.getDoubleParameter(req, "y"));
            resultsBean.setR(Utils.getDoubleParameter(req, "r"));
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
        double x = ((result.x * 2) / r * 60 + 150.0);
        double y = (150.0 - (result.y * 2) / r * 60);
        System.out.printf("%.1f %.1f %.1f %.1f\n",x, y, result.x, result.y);

        writer.print(String.format("<circle r=\"5\" cx=%s cy=%s" +
                " id=\"pointer\" fill=%s></circle>", getHtmlDoubleString(x), getHtmlDoubleString(y), color
        ));
    }
}
