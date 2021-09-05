import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lang_redirect")
public class LanguageRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getHeader("Accept-Language").startsWith("ru")) {
            req.getRequestDispatcher("index_ru.jsp").forward(req, resp);
        } else {
            resp.getWriter().println("BOTAY: " + req.getRequestURL());
            getServletContext().setAttribute("redirected", true);
            resp.sendRedirect(req.getContextPath() + "index.jsp");
        }
    }
}
