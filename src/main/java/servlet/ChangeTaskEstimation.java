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

@WebServlet(urlPatterns = "/changeEstimationServlet")
public class ChangeTaskEstimation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title=req.getParameter("taskTitle");
        String newEstimationStr=req.getParameter("newEstimation");
        TaskManager taskManager = new TaskManager();

        try {
            long estimate=Long.parseLong(newEstimationStr);
            Task task=taskManager.getTaskByTitle(title);
            if (task==null){
                req.setAttribute("errMessage","task not exist");
                req.getRequestDispatcher("/userPageServlet").forward(req,resp);
            }else {
                task.setEstimate(estimate);
                taskManager.updateTask(task);
                resp.sendRedirect("/userPageServlet");
            }
        } catch (SQLException e) {
            req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);
        }catch (NumberFormatException e) {
            req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);

        }
    }
}
