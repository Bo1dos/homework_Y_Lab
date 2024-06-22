package com.example.forms;

import java.util.Scanner;

import com.example.DTO.User;
import com.example.service.UserService;

/**
 * Represents a login form that interacts with UserService to authenticate users.
 */
public class Login {
    private UserService userService;
    private User currentUser;

    /**
     * Constructs a new Login object with a UserService dependency.
     *
     * @param userService The UserService instance to use for user authentication.
     */
    public Login(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the login form to the user and validates user credentials.
     *
     * @return true if the user is successfully authenticated, false otherwise.
     */
    public boolean getLoginForm() {
        String enterLoginString = "Enter Login: ";
        String enterPassString = "Enter Password: ";

        Scanner scanner = new Scanner(System.in);

        System.out.print(enterLoginString);
        String enteredLogin = scanner.nextLine();

        System.out.print(enterPassString);
        String enteredPass = scanner.nextLine();

        // Attempt to retrieve user information based on entered login
        currentUser = userService.getUserByLogin(enteredLogin);

        boolean userPassFlag = false;
        // Validate user credentials
        if (currentUser != null && currentUser.getPass().equals(enteredPass)) {
            userPassFlag = true;
        } else {
            currentUser = null;  // Ensure currentUser is set to null if login fails
        }

        // If user not found or password is incorrect, notify the user
        if (currentUser == null || !userPassFlag) {
            System.out.println("User not found or password is incorrect. Try another login or password");
            return false;
        }

        return true;
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The User object representing the currently authenticated user.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the currently authenticated user.
     *
     * @param currentUser The User object to set as the currently authenticated user.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
