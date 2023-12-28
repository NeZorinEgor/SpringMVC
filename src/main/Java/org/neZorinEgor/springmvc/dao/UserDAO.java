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

        userList.add(new User(++COUNTER, "sample BOB", "test@mail.org"));
        userList.add(new User(++COUNTER, "sample EGOR", "test@mail.org"));
        userList.add(new User(++COUNTER, "sample DANILA", "test@mail.org"));
        userList.add(new User(++COUNTER, "sample DEDOR", "test@mail.org"));
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

    public void delete(int id) {
        userList.removeIf((user -> user.getId() == id));
    }

    public void update(int id, User user) {
        User updateUser = findUserById(id);
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
    }
}
