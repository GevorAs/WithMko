package manager;


import dbDriver.DBConnectionProvider;
import model.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    private final DBConnectionProvider provider = DBConnectionProvider.getInstance();
    private final Connection connection = provider.getConnection();

    public void add(Project project) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "INSERT INTO `project` (`" +
                "name`, `price`, `start_date`, `end_date`" +
                ") VALUES (" +
                "'" + project.getName() +
                "','" + project.getPrice() +
                "','" + project.getStartDate() +
                "','" + project.getEndDate() +
                "')";
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            project.setId(resultSet.getInt(1));
        }
    }
    public void deleteProjectById(long id) throws SQLException {
        String query="DELETE FROM `project` WHERE `id` = '"+id+"'";
        Statement statement=connection.createStatement();
        statement.executeUpdate(query);
    }
    public List<Project> getAllProjects() throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `project`";
        ResultSet resultSet = statement.executeQuery(query);
        List<Project> projects = new ArrayList<Project>();
        while (resultSet.next()) {
            Project project = new Project(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            projects.add(project);
        }
        return projects;
    }



    public Project getProjectById(long id) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `project` where `id`='" + id + "'";
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            Project project = new Project(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            return project;
        }
        return null;
    }
}
