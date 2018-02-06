package servlet;

import manager.TaskManager;
import model.Task;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addTaskServlet")
public class AddTaskServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("name");
        String desc = req.getParameter("desc");
        String estimateStr = req.getParameter("estimate");
        String assignTo = req.getParameter("userId");
        String projectIdStr = req.getParameter("projectId");
        String errMessage = "";
        long userId = Long.parseLong(assignTo);
        long projectId = 0;
        long estimate = 0;
        if (Validator.isEmpty(title)) {
            errMessage += "please input Title for add task<br>";
        }


        if (Validator.isEmpty(estimateStr)) {
            errMessage += "please input estimate for add task<br>";
        } else {
            try {

                estimate = Long.parseLong(estimateStr);
            } catch (Exception e) {
                errMessage += "input number estimate<br>";
            }
        }
        try {
            projectId = Long.parseLong(projectIdStr);

        } catch (Exception e) {
            errMessage += "<h2>please first create a project</h2>";
        }
        TaskManager taskManager = new TaskManager();


        try {
            if (taskManager.taskIsExists(title)) {
                errMessage += "input other title<br>";
            }
            if (errMessage.equals("")) {
                Task task = new Task();
                task.setTitle(title);
                task.setDesc(desc);
                task.setEstimate(estimate);
                task.setAssignTo(userId);
                task.setStatus("NEW");
                task.setProjectId(projectId);

                taskManager.addTask(task);
                resp.sendRedirect("/adminPageServlet");


            } else {
                req.setAttribute("errMessage", errMessage);
                req.getRequestDispatcher("/adminPageServlet").forward(req, resp);

            }
        } catch (SQLException e) {
            req.getRequestDispatcher("WEB-INF/err.jsp").forward(req, resp);
        }
    }
}
