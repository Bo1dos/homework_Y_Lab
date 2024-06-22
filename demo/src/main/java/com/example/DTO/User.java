package com.example.DTO;

/**
 * Represents a user entity with basic details.
 */
public class User {
    private int id;
    private String login;
    private String pass;
    private String role;

    /**
     * Constructs a new User object with specified attributes.
     *
     * @param id    The unique identifier of the user.
     * @param login The login username of the user.
     * @param pass  The password of the user.
     * @param role  The role or access level of the user.
     */
    public User(int id, String login, String pass, String role) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.role = role;
    }

    /**
     * Retrieves the user's ID.
     *
     * @return The unique identifier of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id The unique identifier of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the login username of the user.
     *
     * @return The login username of the user.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login username of the user.
     *
     * @param login The login username of the user.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets the password of the user.
     *
     * @param pass The password of the user.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Retrieves the role or access level of the user.
     *
     * @return The role or access level of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role or access level of the user.
     *
     * @param role The role or access level of the user.
     */
    public void setRole(String role) {
        this.role = role;
    }

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
