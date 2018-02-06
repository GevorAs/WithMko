package manager;


import dbDriver.DBConnectionProvider;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
 private final DBConnectionProvider provider=DBConnectionProvider.getInstance();
   private final Connection connection=provider.getConnection();



    public void addUser(User user) throws SQLException {
        Statement statement=connection.createStatement();
          String query="INSERT INTO `user` (`" +
                  "name`, `surname" +
                  "`, `email`, `password" +
                  "`, `type`)" +
                " VALUES (" +
                  "'" +user.getName()+
                  "', '"+user.getSurname()+
                  "', '"+user.getEmail()+
                  "', '"+user.getPassword()+
                  "', '"+user.getType()+"')";
        statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
        ResultSet resultSet=statement.getGeneratedKeys();
        if (resultSet.next()){
            user.setId(resultSet.getInt(1));
        }
    }
  public void deleteUserById(long id) throws SQLException {
        String query="DELETE FROM `user` WHERE `id` = '"+id+"'";
        Statement statement=connection.createStatement();
      statement.executeUpdate(query);
  }
    public  User getUserByEmailAndPassword(String email,String password) throws SQLException {
        Statement statement=connection.createStatement();
        String query="select * from `user` where `email`='"+email+"' AND `password`='"+password+"'";
        ResultSet resultSet=statement.executeQuery(query);
         User user1 =null;
        if (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setType(resultSet.getString(6));
            user1=user;
        }
        return user1;
    }
    public  User getUserById(long id) throws SQLException {
        Statement statement=connection.createStatement();
        String query="select * from `user` where `id`='"+id+"';";
        ResultSet resultSet=statement.executeQuery(query);
        final User user = new User();
        if (resultSet.next()){
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setType(resultSet.getString(6));
        }
        return user;
    }
    public List<User> getAllUsers() throws SQLException {
        Statement statement=connection.createStatement();
        String query="select * from `user`";
        ResultSet resultSet=statement.executeQuery(query);
        List<User> users = new ArrayList<User>();
        while (resultSet.next()){
            final User user = new User(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
            users.add(user);
        }
        return users;
    }
    public boolean userIsExistByEmail(String email) throws SQLException {
        Statement statement=connection.createStatement();
        String sql="select * from `user` where `email`='"+email+"'";
        ResultSet resultSet=statement.executeQuery(sql);
        return resultSet.next() ;
    }

}
