package com.example;

import com.example.domain.User;
import com.example.enums.UserRole;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Add User with Normal Data Test")
    public void testAddUser() {
        User user = userService.addUser("testUser", "password", UserRole.USER);
        assertThat(user).isNotNull();
        assertThat(user.getLogin()).isEqualTo("testUser");
    }

    @Test
    @DisplayName("Get All Users Test")
    public void testGetAllUsers() {
        userService.addUser("testUser1", "password", UserRole.USER);
        List<User> users = userService.getAllUsers();
        assertThat(users).hasSize(2);
    }

    @Test
    @DisplayName("Update User with Normal Data Test")
    public void testUpdateUser() {
        User user = userService.addUser("testUser2", "password", UserRole.USER);
        boolean updated = userService.updateUser(user.getId(), "updatedUser", "newPassword", UserRole.GUEST);
        assertTrue(updated);
        assertThat(userService.getUserById(user.getId()).getLogin()).isEqualTo("updatedUser");
    }


    @Test
    @DisplayName("Delete User Test")
    public void testDeleteUser() {
        User user = userService.addUser("testUser3", "password", UserRole.USER);
        boolean deleted = userService.deleteUser(user.getId());
        assertTrue(deleted);
    }


    @Test
    @DisplayName("Check if Login is Taken Test")
    public void testIsLoginTaken() {
        userService.addUser("testUser", "password", UserRole.USER);
        boolean isTaken = userService.isLoginTaken("testUser", -1);
        assertTrue(isTaken);
    }
}

