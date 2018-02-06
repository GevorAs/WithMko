package servlet;

import manager.TaskManager;
import model.Comment;
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

@WebServlet("/userPageServlet")
public class UserPageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  errMessage=(String)req.getAttribute("errMessage");
        if (req.getSession().getAttribute("user")==null){
            resp.sendRedirect("/homeServlet");
        }else {
            User user=(User) req.getSession().getAttribute("user");
            TaskManager taskManager = new TaskManager();
           List<Comment> comments=(List) req.getAttribute("comments");
           req.setAttribute("commentsByTaskId",comments);
           List<Task> tasksByUserId;
            List<Task> tasksByStatus;
            req.setAttribute("errMessage",errMessage);
            try {
                if (req.getParameter("taskStatus")!=null) {

                    if (req.getParameter("taskStatus").equalsIgnoreCase("NEW")) {
                        tasksByStatus = taskManager.NewTasksByUserEmail(user.getId());
                        req.setAttribute("tasksByStatus", tasksByStatus);
                    }
                    if (req.getParameter("taskStatus").equalsIgnoreCase("INPROGRESS")) {
                        tasksByStatus = taskManager.CurrentTasksByUserEmail(user.getId());
                        req.setAttribute("tasksByStatus", tasksByStatus);

                    }
                    if (req.getParameter("taskStatus").equalsIgnoreCase("FINISHED")) {
                        tasksByStatus = taskManager.FinishedTasksByUserEmail(user.getId());
                        req.setAttribute("tasksByStatus", tasksByStatus);

                    }
                }
                tasksByUserId=taskManager.getTasksByUserId(user.getId());
                req.setAttribute("tasksByUserId",tasksByUserId);

            } catch (SQLException e) {
                req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);
            }

            req.getRequestDispatcher("WEB-INF/user.jsp").forward(req,resp);
        }

    }
}
