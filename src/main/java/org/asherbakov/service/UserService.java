package org.asherbakov.service;

import org.asherbakov.exception.UserNonUniqueException;
import org.asherbakov.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllLogins() {
        return userRepository.getAllUsers().stream().map(User::getLOGIN).collect(Collectors.toList());
    }

    public void createUser(String login, String password) {
        if (login != null && !login.isBlank() &&
                password != null && !password.isBlank()) {
            List<String> allLogins = getAllLogins();
            if (allLogins.contains(login)) {
                throw new UserNonUniqueException("Пользователь с таким логином уже существует.");
            }
            userRepository.userAdd(new User(login, password));
        } else {
            throw new IllegalArgumentException("Логин, либо пароль не заданы.");
        }
    }

    public boolean userIsExists(String login, String password) {
        List<User> userList = userRepository.getAllUsers();
        if (userList.contains(new User(login, password))) {
            return true;
        } else {
            return false;
        }
    }
}
