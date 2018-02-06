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

@WebServlet(urlPatterns = "/changeTaskAssignToServlet")
public class ChangeTaskAssignToServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  String taskTitle=req.getParameter("taskTitle");
  String userIdStr=req.getParameter("userId");
  long userId=Long.parseLong(userIdStr);
        TaskManager taskManager = new TaskManager();
        try {
           Task task= taskManager.getTaskByTitle(taskTitle);
           task.setAssignTo(userId);
           taskManager.updateTask(task);
            req.getRequestDispatcher("/adminPageServlet").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
