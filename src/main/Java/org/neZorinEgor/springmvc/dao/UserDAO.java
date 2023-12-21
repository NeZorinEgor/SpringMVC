package org.neZorinEgor.springmvc.dao;

import org.neZorinEgor.springmvc.models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class UserDAO {
    private List<User> userList;
    private static int COUNTER = 0;

    {
        userList = new ArrayList<>();

        userList.add(new User(++COUNTER, "ex1"));
        userList.add(new User(++COUNTER, "ex2"));
        userList.add(new User(++COUNTER, "ex3"));
        userList.add(new User(++COUNTER, "BOB"));
    }

    public List<User> showAllUsers(){
        return userList;
    }

    public User findUserById(int id){
        return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void create(User user) {
        user.setId(++COUNTER);
        userList.add(user);
    }
}
