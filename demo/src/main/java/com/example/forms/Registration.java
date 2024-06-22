package com.example.forms;

import java.util.Scanner;

import com.example.DTO.User;
import com.example.service.UserService;

/**
 * Represents a registration form that interacts with UserService to register new users.
 */
public class Registration {
    private UserService userService;

    /**
     * Constructs a new Registration object with a UserService dependency.
     *
     * @param userService The UserService instance to use for user registration.
     */
    public Registration(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the registration form to the user and registers a new user.
     *
     * @return true if the user is successfully registered, false otherwise.
     */
    public boolean getRegistrationForm() {
        String enterLoginString = "Enter Login: ";
        String enterPassString = "Enter Password: ";

        Scanner scanner = new Scanner(System.in);

        System.out.print(enterLoginString);
        String enteredLogin = scanner.nextLine();

        System.out.print(enterPassString);
        String enteredPass = scanner.nextLine();

        // Validate login length
        if (enteredLogin.length() < 3) {
            System.out.println("Try another login (more than 3 characters)");
            return false;
        }

        // Attempt to add user with entered credentials
        User user = userService.addUser(enteredLogin, enteredPass, "normal");

        // Check if user registration was successful
        if (user == null) {
            System.out.println("A user with this login already exists. Try another login (more than 3 characters)");
            return false;
        }

        return true;
    }
}