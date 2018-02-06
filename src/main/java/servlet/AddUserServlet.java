package servlet;

import manager.UserManager;
import model.User;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String surname=req.getParameter("surname");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String repassword=req.getParameter("repassword");
        String errMessage="";
        UserManager userManager=new UserManager();

        if (Validator.isEmpty(name)){
            errMessage+="Name is empty<br>";
        }
        if (Validator.isEmpty(surname)){
            errMessage+="Surname is empty<br>";
        }
        if (Validator.isEmpty(email)){
            errMessage+="Email is empty<br>";
        }else {
            try {
                if (userManager.userIsExistByEmail(email)){
                    errMessage+="Email is already exist<br>";
                }
            } catch (SQLException e) {
                req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);

            }
        }
        if (Validator.isEmpty(password)){
            errMessage+="Password is empty<br>";
        }
        if (password.length()<6){
            errMessage+="Password is small 6<br>";
        }
        if (!password.equals(repassword)){
            errMessage+="Password and Re-password not equals";
        }

        if (errMessage.equals("")) {
            User user=new User(name,surname,email,password,"USER");
            try {
                userManager.addUser(user);
            } catch (SQLException e) {
                req.getRequestDispatcher("WEB-INF/err.jsp").forward(req,resp);
            }
            resp.sendRedirect("/adminPageServlet");
        }else {
            req.setAttribute("errMessage",errMessage);
            req.getRequestDispatcher("/adminPageServlet").forward(req,resp);
        }
    }
}
