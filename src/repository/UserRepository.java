package repository;

import models.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUserName(), user);
    }

    public User getUser(String userName) {
        return users.get(userName);
    }

    public boolean exists(String userName) {
        return users.containsKey(userName);
    }
}
