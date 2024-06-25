package com.example.domain;

import com.example.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user entity with basic details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String login;
    private String pass;
    private UserRole role;

    /**
     * Constructs a new User object with specified attributes.
     *
     * @param id    The unique identifier of the user.
     * @param login The login username of the user.
     * @param pass  The password of the user.
     * @param role  The role or access level of the user.
     */
    

    /**
     * Returns a string representation of the User object.
     *
     * @return A string representation including the user's ID, login, password, and role.
     */
    @Override
    public String toString() {
        return "User{id=" + id + ", login='" + login + ", password='" + pass + "', role=" + role + '}';
    }
}
