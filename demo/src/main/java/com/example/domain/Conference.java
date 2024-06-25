package com.example.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * Represents a conference entity with its details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conference {
    private int conferenceId;
    private String conferenceTitle;


    /**
     * Constructs a new Conference object with specified attributes.
     *
     * @param conferenceId               The unique identifier of the conference.
     * @param conferenceTitle            The title or name of the conference.

     */


    /**
     * Returns a string representation of the Conference object.
     *
     * @return A string representation including the conference ID.
     */
    @Override
    public String toString() {
        return "Conference{id=" + conferenceId + ", title='" + conferenceTitle + '}';
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
