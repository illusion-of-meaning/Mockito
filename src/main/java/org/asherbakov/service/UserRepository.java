package org.asherbakov.service;

import org.asherbakov.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    List<User> userList = new ArrayList<>();
    public List<User> getAllUsers() {
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList;
        }
    }

    public Optional<User> getUserByLogin(String login) {
        return userList.stream().filter(s -> s.getLOGIN().equals(login)).findAny();
    }

    public Optional<User> getUserByLoginAndPassword(String login, String password) {
        return userList.stream()
                .filter(s -> s.getLOGIN().equals(login))
                .filter(s -> s.getPassword().equals(password))
                .findAny();
    }

    public void userAdd(User user) {
        userList.add(user);
    }
}
