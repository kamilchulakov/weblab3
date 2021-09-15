package servlets;

import model.Result;
import model.Results;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static kotlin_meme_jsp.KJSPKt.getTable;
import static kotlin_meme_jsp.KJSPKt.makePoints;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("I WAS IN CHECK");
        double x = Utils.getDoubleParameter(req, "x");
        double y = Utils.getDoubleParameter(req, "y");
        double r = Utils.getDoubleParameter(req, "r");
        boolean result = isInArea(x, y, r);
        HttpSession session = req.getSession();
        Date currentTime = new Date();
        Result entry = new Result(x, y, r, result, (Date) session.getAttribute("queryTime"), currentTime);
        Results results = (Results) session.getAttribute("results");
        if (results == null) results = new Results();
        results.addEntry(entry);
        session.setAttribute("results", results);
        resp.getWriter().print(getTable(req, resp, results) + "kotlin");
        makePoints(req, resp, results);
    }

    private boolean isInArea(double x, double y, double r) {
        return ((x <= 0) && (y >= 0) && ((x * x + y * y) <= r * r + r + getFixParam(r))) || ((x >= 0) && (y <= 0) && (-x + r + r/10.0 >= -2* y))
                || ((x >= 0) && (y >= 0) && (y <= r / 2.0 + r/10.0) && (x <= r + r/10.0));
    }

    private double getFixParam(double r) {
        if (r == 1) return -r/4;
        if (r == 3) return r/4;
        if (r == 4) return r/3;
        if (r == 5) return r/2;
        return r/5;
    }
}
