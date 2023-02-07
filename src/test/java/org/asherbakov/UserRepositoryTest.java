package org.asherbakov;

import org.asherbakov.model.User;
import org.asherbakov.service.UserRepository;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepository();
    @BeforeEach
    void setUp() {
        userRepository.userAdd(new User("test1", "1"));
        userRepository.userAdd(new User("test2", "2"));
    }

    @Test
    @DisplayName("When users list is empty, return correct answer")
    void getEmptyUsersList() {
        Assertions.assertNull(new UserRepository().getAllUsers(), "Users list is not empty");
    }


    @Test
    @DisplayName("When users list equals [('test1', '1'), ('test2', '2')], return correct answer")
    void getUsersList() {
        Assertions.assertEquals(userRepository.getAllUsers(), List.of(new User("test1", "1"), new User("test2", "2")));
    }

    @Test
    @DisplayName("When user 'test2' find in list, return correct answer")
    void findUserForLogin() {
        Optional<User> user = userRepository.getUserForLogin("test2");
        Assertions.assertTrue(user.isPresent());
    }

    @Test
    @DisplayName("When user not find in list, return correct answer")
    void notFindUserForLogin() {
        Optional<User> user = userRepository.getUserForLogin("test222222");
        Assertions.assertFalse(user.isPresent());
    }

    @Test
    @DisplayName("When find user for login/password, return correct answer")
    void findUserForLoginAndPassword() {
        Optional<User> user = userRepository.getUserForLoginAndPassword("test1", "1");
        Assertions.assertTrue(user.isPresent());
    }

    @Test
    @DisplayName("When equals password but not equals login, return correct answer")
    void equalsPasswordNotEqualsLogin() {
        Optional<User> user = userRepository.getUserForLoginAndPassword("test11546", "1");
        Assertions.assertFalse(user.isPresent());
    }

    @Test
    @DisplayName("When equals login but not equals password, return correct answer")
    void equalsLoginNotEqualsPassword() {
        Optional<User> user = userRepository.getUserForLoginAndPassword("test1", "15684");
        Assertions.assertFalse(user.isPresent());
    }
}
