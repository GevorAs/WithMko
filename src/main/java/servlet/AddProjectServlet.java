package servlet;

import manager.ProjectManager;
import model.Project;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addProjectServlet")
public class AddProjectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String errMessage = "";
        long price = 0;
        if (Validator.isEmpty(name)) {
            errMessage += "please input name<br>";
        }

        try {

            price = Long.parseLong(priceStr);
        } catch (Exception e) {
            errMessage += "input number price<br>";
        }

        if (Validator.isEmpty(startDate)) {
            errMessage += "please input valid start date<br>";
        }
        if (Validator.isEmpty(endDate)) {
            errMessage += "please input valid end date<br>";
        }
        ProjectManager projectManager = new ProjectManager();
        if (errMessage.equals("")){
            Project project = new Project();
            project.setName(name);
            project.setPrice(price);
            project.setStartDate(startDate);
            project.setEndDate(endDate);

            try {
                projectManager.add(project);
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
