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
    }

    private boolean isInArea(double x, double y, double r) {
        return ((x <= 0) && (y >= 0) && ((x * x + y * y) <= r * r + r + 2)) || ((x >= 0) && (y <= 0) && (-x + r >= -2* y))
                || ((x >= 0) && (y >= 0) && (y <= r / 2.0) && (x <= r));
    }
}
