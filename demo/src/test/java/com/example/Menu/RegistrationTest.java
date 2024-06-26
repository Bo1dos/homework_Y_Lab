package com.example.Menu;

import com.example.domain.User;
import com.example.enums.UserRole;
import com.example.forms.Registration;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private Registration registration;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Successful Registration")
    public void testGetRegistrationForm_SuccessfulRegistration() {
        String input = "newUser\npassword123\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        User newUser = new User(0, "newUser", "password123", UserRole.USER);

        when(userService.addUser("newUser", "password123", UserRole.USER)).thenReturn(newUser);

        boolean registrationResult = registration.registrationUser();

        assertThat(registrationResult).isTrue();
        verify(userService).addUser("newUser", "password123", UserRole.USER);
    }

    @Test
    @DisplayName("Registration with Short Login")
    public void testGetRegistrationForm_ShortLogin() {
        String input = "ab\npassword123\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        boolean registrationResult = registration.registrationUser();

        assertThat(registrationResult).isFalse();
        verify(userService, never()).addUser(eq("ab"), anyString(), eq(UserRole.USER));
    }


    @Test
    @DisplayName("Registration with Existing User")
    public void testGetRegistrationForm_ExistingUser() {
        String input = "existingUser\npassword123\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        when(userService.addUser("existingUser", "password123", UserRole.USER)).thenReturn(null);

        boolean registrationResult = registration.registrationUser();

        assertThat(registrationResult).isFalse();
        verify(userService).addUser("existingUser", "password123", UserRole.USER);
    }
}

