package servlet;

import manager.CommentManager;
import model.Comment;
import model.User;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addCommentServlet")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user= (User) req.getSession().getAttribute("user");
        String text=req.getParameter("text");
        String taskIdStr=req.getParameter("taskId");
        String errMessage="";
        if (Validator.isEmpty(text)){
            errMessage+="input text<br>";
        }
        long taskId=0;
        try {
            taskId=Long.parseLong(taskIdStr);

        }catch (Exception e){
            errMessage+="<h3>not any tasks</h3><br>";
        }
        CommentManager commentManager = new CommentManager();
        if (errMessage.equals("")){
            try {
                Comment comment = new Comment();
                comment.setText(text);
                comment.setCommentedUserId(user.getId());
                comment.setTaskId(taskId);
                commentManager.add(comment);
                resp.sendRedirect("/adminPageServlet");
            } catch (SQLException e) {
                req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);
            }

        }else {
            req.setAttribute("errMessage",errMessage);
            req.getRequestDispatcher("/adminPageServlet").forward(req,resp);
           }

    }
}
