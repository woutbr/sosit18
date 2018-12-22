package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Performing user authentication in Java EE / JSF using j_security_check : answer by Vítor E. Silva Souza
 * https://stackoverflow.com/a/2997254
 *
 * @author woutbr@student.hik.be
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Destroys the session for this user.
        if (session != null) {
            session.invalidate();
        }
        
        // Redirects back to the initial page.
        response.sendRedirect(request.getContextPath());
    }
}
