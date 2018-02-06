package servlet;

import manager.UserManager;
import model.User;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String errMessage = "";
        if (Validator.isEmpty(email)) {
            errMessage += "please input email<br>";
        }
        if (Validator.isEmpty(password)) {
            errMessage += "please input password<br>";
        }

        if (errMessage.equals("")){
            UserManager userManager = new UserManager();
            User user;
            try {
                user = userManager.getUserByEmailAndPassword(email,password);
                if (user!=null){
                    HttpSession session=req.getSession();
                    user.setPassword(null);
                    session.setAttribute("user",user);
                    resp.sendRedirect("/validatorServlet");
                }else {
                    errMessage="invalid login or password";
                    req.setAttribute("errMessage",errMessage);
                    req.getRequestDispatcher("WEB-INF/home.jsp").forward(req,resp);
                }
            } catch (SQLException e) {
                req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);

            }

        }else {
            req.setAttribute("errMessage",errMessage);
            req.getRequestDispatcher("WEB-INF/home.jsp").forward(req,resp);

        }
    }
}
