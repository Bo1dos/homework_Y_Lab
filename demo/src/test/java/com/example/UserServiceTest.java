package com.example;

import com.example.DTO.User;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testAddUser() {
        User user = userService.addUser("testUser", "password", "role");
        assertThat(user).isNotNull();
        assertThat(user.getLogin()).isEqualTo("testUser");
    }

    @Test
    public void testGetAllUsers() {
        userService.addUser("testUser", "password", "role");
        List<User> users = userService.getAllUsers();
        assertThat(users).hasSize(1);
    }

    @Test
    public void testUpdateUser() {
        User user = userService.addUser("testUser", "password", "role");
        boolean updated = userService.updateUser(user.getId(), "updatedUser", "newPassword", "newRole");
        assertTrue(updated);
        assertThat(userService.getUserById(user.getId()).getLogin()).isEqualTo("updatedUser");
    }

    @Test
    public void testDeleteUser() {
        User user = userService.addUser("testUser", "password", "role");
        boolean deleted = userService.deleteUser(user.getId());
        assertTrue(deleted);
        assertThat(userService.getAllUsers()).isEmpty();
    }

    @Test
    public void testIsLoginTaken() {
        userService.addUser("testUser", "password", "role");
        boolean isTaken = userService.isLoginTaken("testUser", -1);
        assertTrue(isTaken);
    }
}

