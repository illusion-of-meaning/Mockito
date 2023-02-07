package org.asherbakov;

import org.asherbakov.exception.UserNonUniqueException;
import org.asherbakov.model.User;
import org.asherbakov.service.UserRepository;
import org.asherbakov.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("When we call the method, return logins: 'test1', 'test2'")
    void getAllLogins() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("test1", "1"), new User("test2", "2")));
        Assertions.assertEquals(userService.getAllLogins(), List.of("test1", "test2"));
    }

    @Test
    void whenUserIsExistsReturnsTrue() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("test1", "1"), new User("test2", "2")));
        Assertions.assertTrue(userService.userIsExists("test1", "1"));
    }

    @Test
    void whenUserIsNotExistsReturnsTrue() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("test1", "1"), new User("test2", "2")));
        Assertions.assertFalse(userService.userIsExists("test3", "1"));
    }

    @Test
    @DisplayName("When we create a user not with the login 'test1' or 'test2', return correct answer")
    void createUser() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("test1", "1"), new User("test2", "2")));
        userService.createUser("test12", "1");
    }

    @Test
    @DisplayName("When we create a non-unique user throws exceptions")
    void createNonUniqueUser() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("test1", "1"), new User("test2", "2")));
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> userService.createUser("test1", "1"))
                .isInstanceOf(UserNonUniqueException.class)
                .hasMessage("Пользователь с таким логином уже существует.");
    }

    @Test
    @DisplayName("When we create a user without login throws exceptions")
    void createUserWithoutLogin() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> userService.createUser("", "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Логин, либо пароль не заданы.");
    }

    @Test
    @DisplayName("When we create a user without password throws exceptions")
    void createUserWithoutPassword() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> userService.createUser("test1", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Логин, либо пароль не заданы.");
    }
}
