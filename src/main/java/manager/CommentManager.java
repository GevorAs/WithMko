package manager;



import dbDriver.DBConnectionProvider;
import model.Comment;
import model.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {
    private final DBConnectionProvider provider = DBConnectionProvider.getInstance();
    private final Connection connection = provider.getConnection();


    public void add(Comment comment) throws SQLException {
//        private long id;
//        private String text;
//        private long commentedUserId;
//        private long taskId;
//        private String commentedDate;
        Statement statement = connection.createStatement();
        String query = "INSERT INTO `comment` (`" +
                "text`, `user_id`, `task_id`" +
                ") VALUES (" +
                "'" + comment.getText() +
                "','" + comment.getCommentedUserId() +
                "','" + comment.getTaskId() +
                "')";
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            comment.setId(resultSet.getInt(1));
        }
    }
    public void deleteCommentsByTaskId(long id) throws SQLException {
        String query="DELETE FROM `comment` WHERE `task_id` = '"+id+"'";
        Statement statement=connection.createStatement();
        statement.executeUpdate(query);
    }

    public List<Comment> getCommentsByTask(long taskId) throws SQLException {
    Statement statement=connection.createStatement();
    String query="select * from `comment` where `task_id`='"+taskId+"' ";
    ResultSet resultSet=statement.executeQuery(query);
        List<Comment> comments = new ArrayList<Comment>();
        while (resultSet.next()) {
            Comment comment = new Comment(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getString(5));
            comments.add(comment);
        }
        return comments;
   }
}
