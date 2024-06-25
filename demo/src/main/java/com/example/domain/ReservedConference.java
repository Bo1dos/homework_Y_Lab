package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Represents a reserved conference entity with its details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReservedConference extends Conference {
    private Date conferenceStartTime;
    private Date conferenceEndTime;
    private String conferenceReservationUsername;

    /**
     * Constructs a new ReservedConference object with specified attributes.
     *
     * @param conferenceId               The unique identifier of the conference.
     * @param conferenceTitle            The title or name of the conference.
     * @param conferenceStartTime        The start date and time of the conference.
     * @param conferenceEndTime          The end date and time of the conference.
     * @param conferenceReservationUsername The username of the person who made the reservation for the conference.
     */
    public ReservedConference(int conferenceId, String conferenceTitle, Date conferenceStartTime, Date conferenceEndTime, String conferenceReservationUsername) {
        super(conferenceId, conferenceTitle);
        this.conferenceStartTime = conferenceStartTime;
        this.conferenceEndTime = conferenceEndTime;
        this.conferenceReservationUsername = conferenceReservationUsername;
    }

    /**
     * Returns a string representation of the ReservedConference object.
     *
     * @return A string representation including the conference ID, title, start and end times, and reservation username.
     */
    @Override
    public String toString() {
        return "ReservedConference{id=" + getConferenceId() + ", title='" + getConferenceTitle() +
                "', start time='" + conferenceStartTime + "', end time='" + conferenceEndTime +
                "', booker's name=" + conferenceReservationUsername + '}';
    }
}
