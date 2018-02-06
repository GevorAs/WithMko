package servlet;

import manager.ProjectManager;
import manager.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/deleteProjectServlet")
public class DeleteProjectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectManager projectManager = new ProjectManager();
        TaskManager taskManager = new TaskManager();
        Integer id = Integer.parseInt(req.getParameter("projectId"));
        try {
            projectManager.deleteProjectById(id);
            taskManager.deleteTasksByProjectId(id);
            resp.sendRedirect("/adminPageServlet");
        } catch (SQLException e) {
            req.getRequestDispatcher("WEB-INF/err.jsp").forward(req, resp);

        }
    }
}
