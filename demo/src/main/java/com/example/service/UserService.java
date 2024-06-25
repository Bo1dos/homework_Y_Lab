package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.domain.User;
import com.example.enums.UserRole;
import com.example.exception.UserAlreadyExistsException;

/**
 * Service class responsible for managing user operations.
 */
public class UserService {
    private static List<User> users = new ArrayList<>();
    private static int currentId = 1;

    /**
     * Adds a new user with the provided login, password, and role.
     *
     * @param login The login name of the user.
     * @param pass The password of the user.
     * @param role The role of the user (e.g., "admin", "normal").
     * @return The created User object if successful, null if a user with the same login already exists.
     */
    public User addUser(String login, String pass, UserRole role) {
        User user = new User(newId(), login, pass, role);
        try {
            User existingUser = getUserByLogin(login);
            if (existingUser == null) {
                users.add(user);
                return user;
            } else {
                throw new UserAlreadyExistsException("User with login " + user.getLogin() + " already exists.");
            }

        } catch (UserAlreadyExistsException e) {

            return null;
        }

    }

    /**
     * Retrieves all users registered in the system.
     *
     * @return List of all registered users.
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Updates the details of an existing user.
     *
     * @param id The ID of the user to update.
     * @param login The new login name of the user.
     * @param pass The new password of the user.
     * @param role The new role of the user.
     * @return true if the user was successfully updated, false otherwise (e.g., login already taken).
     */
    public boolean updateUser(int id, String login, String pass, UserRole role) {
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

    /**
     * Deletes a user based on their ID.
     *
     * @param id The ID of the user to delete.
     * @return true if the user was successfully deleted, false otherwise.
     */
    public boolean deleteUser(int id) {
        return users.removeIf(p -> p.getId() == id);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    public User getUserById(int id) {
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        return user.orElse(null);
    }

    /**
     * Retrieves a user by their login name.
     *
     * @param login The login name of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    public User getUserByLogin(String login) {
        Optional<User> user = users.stream().filter(u -> u.getLogin().equals(login)).findFirst();
        return user.orElse(null);
    }

    /**
     * Checks if a login name is already taken by another user.
     *
     * @param login The login name to check.
     * @param excludeUserId The ID of the user to exclude from the check (typically used during update).
     * @return true if the login name is already taken, false otherwise.
     */
    public boolean isLoginTaken(String login, int excludeUserId) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getId() != excludeUserId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a new unique ID for users.
     *
     * @return A new unique ID.
     */
    private int newId() {
        return currentId++;
    }

}
