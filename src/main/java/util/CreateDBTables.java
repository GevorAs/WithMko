package util;


import dbDriver.DBConnectionProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CreateDBTables {
    private DBConnectionProvider provider = DBConnectionProvider.getInstance();
    private Connection connection = provider.getConnection();
    private Statement statement = connection.createStatement();

    String name;
    String surname;
    String email;
    String password;
    String userStrQuery;
    String projectStrQuery;
    String taskStrQuery;
    String commentStrQuery;
    String adminAdd;
    private void loadProperties() throws IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\NKAR_ERG_ARPINE_GOHAR\\Projects\\WebApps\\WithMko\\src\\main\\resources\\config.properties"));
        name=properties.getProperty("admin.name");
        surname=properties.getProperty("admin.surname");
        email=properties.getProperty("admin.email");
        password=properties.getProperty("admin.password");
    }
    private CreateDBTables() throws SQLException, IOException {
        loadProperties();
        str();
        statement.executeUpdate(userStrQuery);
        statement.executeUpdate(taskStrQuery);
        statement.executeUpdate(projectStrQuery);
        statement.executeUpdate(commentStrQuery);
        ResultSet resultSet = statement.executeQuery("select `email` from `user` where `email`='"+email+"'");

        if (!resultSet.next()) {
            statement.executeUpdate(adminAdd);
        }

    }
    private void str(){

           userStrQuery =
                " CREATE TABLE IF NOT EXISTS `user` (" +
                        "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                        "  `name` varchar(255) DEFAULT NULL," +
                        "  `surname` varchar(255) DEFAULT NULL," +
                        "  `email` varchar(255) NOT NULL," +
                        "  `password` varchar(255) NOT NULL," +
                        "  `type` enum('manager','user') NOT NULL," +
                        "  PRIMARY KEY (`id`)" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8";
          adminAdd = "INSERT INTO `user` (`name`, `surname`, `email`, `password`, `type`)" +
                " VALUES ('"+name+"', '"+surname+"', '"+email+"', '"+password+"', 'MANAGER');";
          taskStrQuery =
                "CREATE TABLE IF NOT EXISTS `task` (" +
                        "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                        "  `title` varchar(255) NOT NULL," +
                        "  `desc` varchar(255) DEFAULT NULL," +
                        "  `estimate` int(11) NOT NULL," +
                        "  `user_id` int(11) NOT NULL," +
                        "  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                        "  `status` enum('NEW','FINISHED','INPROGRESS') NOT NULL," +
                        "  `project_id` int(11) NOT NULL," +
                        "  PRIMARY KEY (`id`)" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8";
           projectStrQuery =
                "CREATE TABLE IF NOT EXISTS `project` (" +
                        "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                        "  `name` varchar(255) NOT NULL," +
                        "  `price` double NOT NULL," +
                        "  `start_date` varchar(255) NOT NULL," +
                        "  `end_date` varchar(255) NOT NULL," +
                        "  PRIMARY KEY (`id`)" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

           commentStrQuery =
                "CREATE TABLE IF NOT EXISTS `comment` (" +
                        "  `id` int(11) NOT NULL AUTO_INCREMENT," +
                        "  `text` text NOT NULL," +
                        "  `user_id` int(11) NOT NULL," +
                        "  `task_id` int(11) NOT NULL," +
                        "  `comment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                        "  PRIMARY KEY (`id`)" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

    }

    public static void getInstance() throws SQLException, IOException {
        new CreateDBTables();
    }

}
