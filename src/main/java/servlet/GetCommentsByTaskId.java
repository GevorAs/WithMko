package servlet;

import manager.CommentManager;
import model.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/getCommentsByTaskIdServlet")
public class GetCommentsByTaskId extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskIdStr=req.getParameter("getCommentsBytaskId");
        long taskId=Long.parseLong(taskIdStr);
        CommentManager commentManager = new CommentManager();
        List<Comment> comments;
        try {
           comments= commentManager.getCommentsByTask(taskId);
            req.setAttribute("comments",comments);
            req.getRequestDispatcher("/userPageServlet").forward(req,resp);
        } catch (SQLException e) {
            req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);
        }
    }
}
