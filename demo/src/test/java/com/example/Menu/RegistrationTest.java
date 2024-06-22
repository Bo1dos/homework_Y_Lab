package com.example.Menu;

import com.example.DTO.User;
import com.example.forms.Registration;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
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
    public void testGetRegistrationForm_SuccessfulRegistration() {
        String input = "newUser\npassword123\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        User newUser = new User(0, "newUser", "password123", "normal");

        when(userService.addUser("newUser", "password123", "normal")).thenReturn(newUser);

        boolean registrationResult = registration.getRegistrationForm();

        assertThat(registrationResult).isTrue();
        verify(userService).addUser("newUser", "password123", "normal");
    }

    @Test
    public void testGetRegistrationForm_ShortLogin() {
        String input = "ab\npassword123\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        boolean registrationResult = registration.getRegistrationForm();

        assertThat(registrationResult).isFalse();
        verify(userService, never()).addUser(anyString(), anyString(), anyString());
    }

    @Test
    public void testGetRegistrationForm_ExistingUser() {
        String input = "existingUser\npassword123\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        when(userService.addUser("existingUser", "password123", "normal")).thenReturn(null);

        boolean registrationResult = registration.getRegistrationForm();

        assertThat(registrationResult).isFalse();
        verify(userService).addUser("existingUser", "password123", "normal");
    }
}

