package com.example.forms;

import java.util.Scanner;

import com.example.domain.User;
import com.example.service.UserService;

/**
 * Represents a login form that interacts with UserService to authenticate users.
 */
public class Login {
    private UserService userService;

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
    public User loginUser() {
        String enterLoginString = "Enter Login: ";
        String enterPassString = "Enter Password: ";

        Scanner scanner = new Scanner(System.in);

        System.out.print(enterLoginString);
        String enteredLogin = scanner.nextLine();

        System.out.print(enterPassString);
        String enteredPass = scanner.nextLine();

        
        User currentUser = userService.getUserByLogin(enteredLogin);

        boolean userPassFlag = false;
        
        if (currentUser != null && currentUser.getPass().equals(enteredPass)) {
            userPassFlag = true;
        } else if (currentUser == null || !userPassFlag) {
            System.out.println("User not found or password is incorrect. Try another login or password");
            currentUser = null; 
        }


        return currentUser;
    }


}
