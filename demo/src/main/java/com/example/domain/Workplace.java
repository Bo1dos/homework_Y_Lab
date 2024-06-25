package com.example.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a workplace entity with details including its ID.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workplace {
    private int workplaceId;


    /**
     * Constructs a new Workplace object with specified attributes.
     *
     * @param workplaceId       The unique identifier of the workplace.

     */
   

    /**
     * Returns a string representation of the Workplace object.
     *
     * @return A string representation including the workplace's ID.
     */
    @Override
    public String toString() {
        return "Workplace{id=" + workplaceId +'}';
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
