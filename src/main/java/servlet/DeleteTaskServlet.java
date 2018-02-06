package servlet;

import manager.CommentManager;
import manager.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/deleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskManager taskManager = new TaskManager();
        Integer id = Integer.parseInt(req.getParameter("taskId"));
        CommentManager commentManager = new CommentManager();
        try {
            taskManager.deleteTaskById(id);
            commentManager.deleteCommentsByTaskId(id);
            resp.sendRedirect("/adminPageServlet");
        } catch (SQLException e) {
            req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);

        }
    }
}
