package servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Utils {
    public static double getDoubleParameter(HttpServletRequest request, String parameter) {
        return Double.parseDouble(request.getParameter(parameter).replace(",", "."));
    }

    public static boolean isInArea(double x, double y, double r) {
        return isInRect(x, y, r) || isInCircle(x, y, r)
                || isInPolygon(x, y, r);
    }

    public static boolean isValid(double x, double y, double r) {
        return (x <= 2 && x >= -2) && (y >+ -5 && y <= 5) && (r >= 2 && r <= 5);
    }

    private static boolean isInRect(double x, double y, double r) {
        return ((x <= 0) && (y >= 0) && (y <= r ) && (x >= -r));
    }

    private static boolean isInCircle(double x, double y, double r) {
        return (x >= 0) && (y <= 0) && ((x * x + y * y) <= r * r / 4.0);
    }

    private static boolean isInPolygon(double x, double y, double r) {
        return ((x >= 0) && (y >= 0) && (-x + r >= 2* y));
    }
}
