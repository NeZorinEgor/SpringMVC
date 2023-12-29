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
        //return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
        return null;
    }

    public void create(User user) {
//        user.setId(++COUNTER);
//        userList.add(user);
    }

    public void delete(int id) {
        //userList.removeIf((user -> user.getId() == id));
    }

    public void update(int id, User user) {
//        User updateUser = findUserById(id);
//        updateUser.setName(user.getName());
//        updateUser.setEmail(user.getEmail());
    }
}
