package servlet;

import manager.TaskManager;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/changeStatusServlet")
public class ChangeTaskStatusServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("taskTitle");
        String newStatus = req.getParameter("newStatus");
        TaskManager taskManager = new TaskManager();
        System.out.printf(newStatus);
        try {
            Task task = taskManager.getTaskByTitle(title);
            task.setStatus(newStatus);
            taskManager.updateTask(task);
            resp.sendRedirect("/userPageServlet");

        } catch (SQLException e) {
            req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);
        }
    }
}
