package manager;


import dbDriver.DBConnectionProvider;
import model.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final DBConnectionProvider provider = DBConnectionProvider.getInstance();
    private final Connection connection = provider.getConnection();

    public void addTask(Task task) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "INSERT INTO `task` (`" +
                "title`, `desc`, `estimate`, `user_id`" +
                ", `status`,`project_id`) VALUES (" +
                "'" + task.getTitle() +
                "','" + task.getDesc() +
                "','" + task.getEstimate() +
                "','" + task.getAssignTo() +
                "','" + task.getStatus() +
                "','" + task.getProjectId() + "')";
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            task.setId(resultSet.getInt(1));
        }
    }

    public void deleteTaskById(long id) throws SQLException {
        String query = "DELETE FROM `task` WHERE `id` = '" + id + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void deleteTasksByProjectId(long id) throws SQLException {
        String query = "DELETE FROM `task` WHERE `project_id` = '" + id + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void updateTask(Task task) throws SQLException {
        String query = "UPDATE `task` SET `title`='" +
                task.getTitle() + "', `desc`='" +
                task.getDesc() + "', `estimate`='" +
                task.getEstimate()+"',`status`='"+task.getStatus() + "',`user_id`='"+task.getAssignTo()+"' WHERE `id`='" +
                task.getId() + "'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);


    }

    public List<Task> NewTasksByUserEmail(long userId) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `task` where `status`='NEW' and `user_id`='"
                + userId + "'";
        ResultSet resultSet = statement.executeQuery(query);
        List<Task> tasks = new ArrayList<Task>();
        while (resultSet.next()) {
            final Task task = new Task(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
            tasks.add(task);
        }
        return tasks;
    }

    public List<Task> CurrentTasksByUserEmail(long userId) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `task` where `status`='INPROGRESS' and `user_id`='"
                + userId + "'";
        ResultSet resultSet = statement.executeQuery(query);
        List<Task> tasks = new ArrayList<Task>();
        while (resultSet.next()) {
            final Task task = new Task(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
            tasks.add(task);
        }
        return tasks;
    }

    public List<Task> FinishedTasksByUserEmail(long userId) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `task` where `status`='FINISHED' and `user_id`='"
                + userId + "'";
        ResultSet resultSet = statement.executeQuery(query);
        List<Task> tasks = new ArrayList<Task>();
        while (resultSet.next()) {
            final Task task = new Task(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
            tasks.add(task);
        }
        return tasks;
    }

    public List<Task> getAllTasks() throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `task` ";
        ResultSet resultSet = statement.executeQuery(query);
        List<Task> tasks = new ArrayList<Task>();
        while (resultSet.next()) {
            final Task task = new Task(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
            tasks.add(task);
        }
        return tasks;
    }

    public Task getTaskByNameAndUserEmail(String taskName, long userId) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `task` where `title`='"
                + taskName + "' and  `user_id`='" + userId + "'";
        ResultSet resultSet = statement.executeQuery(query);
        Task task = null;
        if (resultSet.next()) {
            task = new Task(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
        }
        return task;
    }

    public List<Task> getTasksByStatus(String status) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `task` where `status`='" + status + "'";
        ResultSet resultSet = statement.executeQuery(query);
        List<Task> tasks = new ArrayList<Task>();
        while (resultSet.next()) {
            final Task task = new Task(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
            tasks.add(task);
        }
        return tasks;
    }

    public List<Task> getTasksByUserId(long userId) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `task` where `user_id`='" + userId + "'";
        ResultSet resultSet = statement.executeQuery(query);
        List<Task> tasks = new ArrayList<Task>();
        while (resultSet.next()) {
            final Task task = new Task(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
            tasks.add(task);
        }
        return tasks;
    }

    public Task getTaskByTitle(String taskName) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `task` where `title`='"
                + taskName + "'";
        ResultSet resultSet = statement.executeQuery(query);
        Task task = null;
        if (resultSet.next()) {
            task = new Task(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8));
        }
        return task;
    }

    public boolean taskIsExists(String taskName) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "select * from `task` where `title`='"
                + taskName + "'";
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet.next();
    }


}
