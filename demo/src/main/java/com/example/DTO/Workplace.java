package com.example.DTO;

import java.util.Date;

/**
 * Represents a workplace entity with details including its ID, start and end times, and assigned user.
 */
public class Workplace {
    private int workplaceId;
    private Date workplaceStartTime;
    private Date workplaceEndTime;
    private User user;

    /**
     * Constructs a new Workplace object with specified attributes.
     *
     * @param workplaceId       The unique identifier of the workplace.
     * @param workplaceStartTime The start time of the workplace.
     * @param workplaceEndTime   The end time of the workplace.
     * @param user              The user assigned to the workplace.
     */
    public Workplace(int workplaceId, Date workplaceStartTime, Date workplaceEndTime, User user) {
        this.workplaceId = workplaceId;
        this.workplaceStartTime = workplaceStartTime;
        this.workplaceEndTime = workplaceEndTime;
        this.user = user;
    }

    /**
     * Retrieves the ID of the workplace.
     *
     * @return The unique identifier of the workplace.
     */
    public int getWorkplaceId() {
        return workplaceId;
    }

    /**
     * Sets the ID of the workplace.
     *
     * @param workplaceId The unique identifier of the workplace.
     */
    public void setWorkplaceId(int workplaceId) {
        this.workplaceId = workplaceId;
    }

    /**
     * Retrieves the start time of the workplace.
     *
     * @return The start time of the workplace.
     */
    public Date getWorkplaceStartTime() {
        return workplaceStartTime;
    }

    /**
     * Sets the start time of the workplace.
     *
     * @param workplaceStartTime The start time of the workplace.
     */
    public void setWorkplaceStartTime(Date workplaceStartTime) {
        this.workplaceStartTime = workplaceStartTime;
    }

    /**
     * Retrieves the end time of the workplace.
     *
     * @return The end time of the workplace.
     */
    public Date getWorkplaceEndTime() {
        return workplaceEndTime;
    }

    /**
     * Sets the end time of the workplace.
     *
     * @param workplaceEndTime The end time of the workplace.
     */
    public void setWorkplaceEndTime(Date workplaceEndTime) {
        this.workplaceEndTime = workplaceEndTime;
    }

    /**
     * Retrieves the user assigned to the workplace.
     *
     * @return The user assigned to the workplace.
     */
    public User getWorkplaceUser() {
        return user;
    }

    /**
     * Sets the user assigned to the workplace.
     *
     * @param user The user assigned to the workplace.
     */
    public void setWorkplaceUser(User user) {
        this.user = user;
    }

    /**
     * Returns a string representation of the Workplace object.
     *
     * @return A string representation including the workplace's ID, start and end times, and assigned user's login.
     */
    @Override
    public String toString() {
        return "Workplace{id=" + workplaceId + ", start time='" + workplaceStartTime +
                "', end time='" + workplaceEndTime + "', user=" + (user != null ? user.getLogin() : "null") + '}';
    }

    /**
     * Compares this Workplace object to another object for equality based on workplaceId.
     *
     * @param o The object to compare to.
     * @return true if the objects are equal based on workplaceId, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workplace workplace = (Workplace) o;

        return workplaceId == workplace.workplaceId;
    }

    /**
     * Generates a hash code for the Workplace object based on workplaceId.
     *
     * @return The hash code of the Workplace object.
     */
    @Override
    public int hashCode() {
        return workplaceId;
    }
}
