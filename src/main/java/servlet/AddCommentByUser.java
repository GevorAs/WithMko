package servlet;

import manager.CommentManager;
import manager.TaskManager;
import model.Comment;
import model.Task;
import model.User;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addCommentByUser")
public class AddCommentByUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String text = req.getParameter("text");
        String title = req.getParameter("taskTitle");
        if (Validator.isEmpty(text)) {
            req.setAttribute("errMessage", "please input text for add comment<br>");
            req.getRequestDispatcher("/userPageServlet").forward(req, resp);
        } else {
            try {
                CommentManager commentManager = new CommentManager();
                TaskManager taskManager = new TaskManager();
                Task task = taskManager.getTaskByTitle(title);
                Comment comment = new Comment();
                comment.setText(text);
                comment.setCommentedUserId(user.getId());
                comment.setTaskId(task.getId());
                commentManager.add(comment);
                resp.sendRedirect("/userPageServlet");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
