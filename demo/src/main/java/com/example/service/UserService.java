package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.DTO.User;

public class UserService {
    List<User> users = new ArrayList<>();
    private int currentId = 1;

    // Create
    public User addUser(String login, String pass, String role) {
        User user = new User(newId(), login, pass, role);

        User existingUser = getUserByLogin(login);
        if(existingUser == null){
            users.add(user);
            return user;
        }
        return null;
        
    }

    // Read
    public List<User> getAllUsers() {
        return users.stream()
                    .collect(Collectors.toList());
    }


    // Update
    public boolean updateUser(int id, String login,  String pass, String role) {
        User user = getUserById(id);
        
        if (user != null) {
            if (isLoginTaken(login, id)) {
                return false;
            }
            user.setLogin(login);
            user.setPass(pass);
            user.setRole(role);

            return true;
        }
        return false;
    }

    // Delete
    public boolean deleteUser(int id) {
        return users.removeIf(p -> p.getId() == id);
    }

    // Helper methods
    public User getUserById(int id) {
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        return user.orElse(null);
    }


    public User getUserByLogin(String login) {
        
        Optional<User> user = users.stream().filter(u -> u.getLogin().equals(login)).findFirst();

        return user.orElse(null);
    }

    public boolean isLoginTaken(String login, int excludeUserId) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getId() != excludeUserId) {
                return true;
            }
        }
        return false;
    }

    private int newId(){
        return currentId++;
    } 

}
