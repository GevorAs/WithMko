package servlet;

import manager.ProjectManager;
import manager.TaskManager;
import manager.UserManager;
import model.Project;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/adminPageServlet")
public class AdminPageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String  errMessage=(String)req.getAttribute("errMessage");
        if (req.getSession().getAttribute("user")==null){
            resp.sendRedirect("/homeServlet");
        }else {
            List<Task> tasksByStatus;
            TaskManager taskManager = new TaskManager();
            List<Task> tasks;
            UserManager userManager = new UserManager();
            List<User> users;
            ProjectManager projectManager = new ProjectManager();
            List<Project> projects;
            String taskStatus;
            try {

                if (req.getParameter("taskStatus")!=null){
                    taskStatus=req.getParameter("taskStatus");
                tasksByStatus= taskManager.getTasksByStatus(taskStatus);
                req.setAttribute("tasksByStatus",tasksByStatus);
                }
                tasks=taskManager.getAllTasks();
                users=userManager.getAllUsers();
                projects=projectManager.getAllProjects();
                req.setAttribute("projects",projects);
                req.setAttribute("users",users);
                req.setAttribute("tasks",tasks);
                req.setAttribute("errMessage",errMessage);
                req.getRequestDispatcher("WEB-INF/admin.jsp").forward(req,resp);
            } catch (SQLException e) {
                req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);
            }
        }
    }
}
