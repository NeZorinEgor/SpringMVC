package org.neZorinEgor.springmvc.dao;

import org.neZorinEgor.springmvc.models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int COUNTER = 0;

    private static final String URL = "jdbc:mysql://localhost:3306/SpringMvc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<User> showAllUsers(){
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM User";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                User user = new User();
                user.setId((resultSet.getInt("id")));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public User findUserById(int id){
        User user = null;
        PreparedStatement stmnt = null;
        try {
            stmnt = connection.prepareStatement("SELECT * FROM User WHERE id=?");
            stmnt.setInt(1, id);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void create(User user){
        try {
            PreparedStatement stmnt = connection.prepareStatement("INSERT INTO User(name, email) value (?, ?)");
            stmnt.setString(1, user.getName());
            stmnt.setString(2, user.getEmail());
            stmnt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement stmnt = connection.prepareStatement("DELETE FROM `User` WHERE id=?");
            stmnt.setInt(1, id);
            stmnt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE User SET name=?, email=? WHERE id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
