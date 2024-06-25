package com.example.forms;

import java.util.Scanner;

import com.example.DTO.User;
import com.example.service.UserService;

public class Registration {
    private UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }
    
    public boolean getRegistrationForm(){
        String enterLoginString = "Enter Login: ";
        String enterPassString = "Enter Password: ";

        Scanner scanner = new Scanner(System.in);

        System.out.print(enterLoginString);
        String enteredLogin = scanner.nextLine();

        System.out.print(enterPassString);
        String enteredPass = scanner.nextLine();

        
        if(enteredLogin.length() <3){
            System.out.println("Try another login (more than 3 characters)");
            return false;
        }
        User user = userService.addUser(enteredLogin, enteredPass, "normal");
         
        if(user == null){
            System.out.println("A user with this login already exists. Try another login (more than 3 characters)");
            return false;
        }

        return true;
    }
}
