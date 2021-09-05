import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!Optional.of((boolean) getServletContext().getAttribute("redirected")).orElse(false)) getServletContext().getRequestDispatcher("/lang_redirect").forward(req, resp);
        } catch (Exception ignored) {
            System.out.println(ignored);
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }
}
