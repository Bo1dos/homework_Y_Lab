package com.example.forms;

import java.util.Scanner;

import com.example.DTO.User;
import com.example.service.UserService;

public class Login {
    private UserService userService;
    private User currentUser;


    public Login(UserService userService) {
        this.userService = userService;
    }
    
    public boolean getLoginForm(){

        String enterLoginString = "Enter Login: ";
        String enterPassString = "Enter Password: ";

        Scanner scanner = new Scanner(System.in);

        System.out.print(enterLoginString);
        String enteredLogin = scanner.nextLine();

        System.out.print(enterPassString);
        String enteredPass = scanner.nextLine();

        
        
        currentUser = userService.getUserByLogin(enteredLogin);
        
        boolean userPassFlag = false;
        if(currentUser != null && currentUser.getPass().equals(enteredPass)){
            userPassFlag = true;
        } else {
            currentUser = null;  // Ensure currentUser is set to null if login fails
        }

        //If user not found or password is incorrect
        if(currentUser == null || userPassFlag == false){
            System.out.println("User not found or password is incorrect. Try another login or password");
            return false;
        }

       
        return true;
    }

    
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
