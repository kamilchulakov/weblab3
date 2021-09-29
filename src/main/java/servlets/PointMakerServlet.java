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

@WebServlet(urlPatterns = "/points")
public class PointMakerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ResultsBean resultsBean = (ResultsBean) req.getSession().getAttribute("rb");
//        System.out.println(resultsBean);
//        if (resultsBean == null) resultsBean = new ResultsBean();
//        resultsBean.setX(Utils.getDoubleParameter(req, "x"));
//        resultsBean.setY(Utils.getDoubleParameter(req, "y"));
//        resultsBean.setR(Utils.getDoubleParameter(req, "r"));
//        resultsBean.submitResult();
//        req.getSession().setAttribute("resultsBean", resultsBean);
//        makePoints(resultsBean, resp.getWriter(),
//                Utils.getDoubleParameter(req, "r"));
//        System.out.println(req.getParameter("sessionId"));
    }
    public void makePoints(ResultsBean results, PrintWriter writer, double r) {
        for (Result result:  results.getEntries()) {
            String color = "red";
            if (result.result) color = "green";
            writer.print(String.format("<circle r=\"5\" cx=%f cy=%f" +
                            " id=\"pointer\" fill=%s></circle>", ((result.x * 2) / r * 50 + 300 / 2.0) - 30,
                    (300 / 2.0 - (result.y * 2) / r * 50) - 30, color
            ));
        }
    }
}
