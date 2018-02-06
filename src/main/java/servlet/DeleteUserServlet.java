package servlet;

import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserManager userManager = new UserManager();
        Integer id = Integer.parseInt(req.getParameter("userId"));
        try {
            userManager.deleteUserById(id);
            resp.sendRedirect("/adminPageServlet");
        } catch (SQLException e) {
            req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);

        }
    }
}
