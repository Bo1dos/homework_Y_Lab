// package com.example.Menu;

// import com.example.DTO.User;
// import com.example.forms.Login;
// import com.example.service.UserService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.jupiter.MockitoExtension;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.*;

// import java.io.ByteArrayInputStream;
// import java.util.Scanner;

// @ExtendWith(MockitoExtension.class)
// public class LoginTest {

//     @Mock
//     private UserService userService;

//     @InjectMocks
//     private Login login;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void testGetLoginForm_SuccessfulLogin() {
//         String input = "testUser\npassword123\n";
//         User user = new User(0, "testUser", "password123", "normal");

//         System.setIn(new ByteArrayInputStream(input.getBytes()));
//         Scanner scanner = new Scanner(System.in);

//         when(userService.getUserByLogin("testUser")).thenReturn(user);

//         boolean loginResult = login.getLoginForm();

//         assertThat(loginResult).isTrue();
//         assertThat(login.getCurrentUser()).isEqualTo(user);
//         verify(userService).getUserByLogin("testUser");
//     }

//     @Test
//     public void testGetLoginForm_UnsuccessfulLogin() {
//         String input = "testUser\nwrongPassword\n";

//         System.setIn(new ByteArrayInputStream(input.getBytes()));
//         Scanner scanner = new Scanner(System.in);

//         User user = new User(0, "testUser", "password123", "normal");

//         when(userService.getUserByLogin("testUser")).thenReturn(user);

//         boolean loginResult = login.getLoginForm();

//         assertThat(loginResult).isFalse();
//         assertThat(login.getCurrentUser()).isNull();
//         verify(userService).getUserByLogin("testUser");
//     }

//     @Test
//     public void testGetLoginForm_UserNotFound() {
//         String input = "nonexistentUser\npassword123\n";

//         System.setIn(new ByteArrayInputStream(input.getBytes()));
//         Scanner scanner = new Scanner(System.in);

//         when(userService.getUserByLogin("nonexistentUser")).thenReturn(null);

//         boolean loginResult = login.getLoginForm();

//         assertThat(loginResult).isFalse();
//         assertThat(login.getCurrentUser()).isNull();
//         verify(userService).getUserByLogin("nonexistentUser");
//     }
// }
