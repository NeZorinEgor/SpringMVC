package org.neZorinEgor.springmvc.dao;

import org.neZorinEgor.springmvc.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private List<User> userList;

    {
        userList = new ArrayList<>();

        userList.add(new User(1, "ex1"));
        userList.add(new User(2, "ex2"));
        userList.add(new User(3, "ex3"));
        userList.add(new User(4, "BOB"));
    }

    public List<User> showAllUsers(){
        return userList;
    }

    public User findUserById(int id){
        return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
}
