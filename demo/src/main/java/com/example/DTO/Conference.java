package com.example.DTO;

import java.util.Date;

/**
 * Represents a conference entity with its details.
 */
public class Conference {
    private int conferenceId;
    private String conferenceTitle;
    private Date conferenceStartTime;
    private Date conferenceEndTime;
    private String conferenceReservationUsername;

    /**
     * Constructs a new Conference object with specified attributes.
     *
     * @param conferenceId               The unique identifier of the conference.
     * @param conferenceTitle            The title or name of the conference.
     * @param conferenceStartTime        The start date and time of the conference.
     * @param conferenceEndTime          The end date and time of the conference.
     * @param conferenceReservationUsername The username of the person who made the reservation for the conference.
     */
    public Conference(int conferenceId, String conferenceTitle, Date conferenceStartTime, Date conferenceEndTime, String conferenceReservationUsername) {
        this.conferenceId = conferenceId;
        this.conferenceTitle = conferenceTitle;
        this.conferenceStartTime = conferenceStartTime;
        this.conferenceEndTime = conferenceEndTime;
        this.conferenceReservationUsername = conferenceReservationUsername;
    }

    /**
     * Retrieves the conference ID.
     *
     * @return The unique identifier of the conference.
     */
    public int getConferenceId() {
        return conferenceId;
    }

    /**
     * Sets the conference ID.
     *
     * @param conferenceId The unique identifier of the conference.
     */
    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    /**
     * Retrieves the title of the conference.
     *
     * @return The title or name of the conference.
     */
    public String getConferenceTitle() {
        return conferenceTitle;
    }

    /**
     * Sets the title of the conference.
     *
     * @param conferenceTitle The title or name of the conference.
     */
    public void setConferenceTitle(String conferenceTitle) {
        this.conferenceTitle = conferenceTitle;
    }

    /**
     * Retrieves the start date and time of the conference.
     *
     * @return The start date and time of the conference.
     */
    public Date getConferenceStartTime() {
        return conferenceStartTime;
    }

    /**
     * Sets the start date and time of the conference.
     *
     * @param conferenceStartTime The start date and time of the conference.
     */
    public void setConferenceStartTime(Date conferenceStartTime) {
        this.conferenceStartTime = conferenceStartTime;
    }

    /**
     * Retrieves the end date and time of the conference.
     *
     * @return The end date and time of the conference.
     */
    public Date getConferenceEndTime() {
        return conferenceEndTime;
    }

    /**
     * Sets the end date and time of the conference.
     *
     * @param conferenceEndTime The end date and time of the conference.
     */
    public void setConferenceEndTime(Date conferenceEndTime) {
        this.conferenceEndTime = conferenceEndTime;
    }

    /**
     * Retrieves the username of the person who made the reservation for the conference.
     *
     * @return The username of the person who made the reservation.
     */
    public String getConferenceReservationUsername() {
        return conferenceReservationUsername;
    }

    /**
     * Sets the username of the person who made the reservation for the conference.
     *
     * @param conferenceReservationUsername The username of the person who made the reservation.
     */
    public void setConferenceReservationUsername(String conferenceReservationUsername) {
        this.conferenceReservationUsername = conferenceReservationUsername;
    }

    /**
     * Returns a string representation of the Conference object.
     *
     * @return A string representation including the conference ID, title, start and end times, and reservation username.
     */
    @Override
    public String toString() {
        return "Conference{id=" + conferenceId + ", title='" + conferenceTitle +
                "', start time='" + conferenceStartTime + "', end time='" + conferenceEndTime +
                "', booker's name=" + conferenceReservationUsername + '}';
    }

    /**
     * Checks whether this Conference object is equal to another object.
     *
     * @param o The object to compare this Conference with.
     * @return true if the objects are equal based on conference ID, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        return conferenceId == that.conferenceId;
    }

    /**
     * Returns the hash code value for this Conference object.
     *
     * @return The hash code value based on the conference ID.
     */
    @Override
    public int hashCode() {
        return conferenceId;
    }
}
