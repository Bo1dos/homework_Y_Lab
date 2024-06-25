package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Represents a reserved workplace entity with additional details including start and end times, and assigned user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReservedWorkplace extends Workplace {
    private Date workplaceStartTime;
    private Date workplaceEndTime;
    private User user;

    /**
     * Constructs a new ReservedWorkplace object with specified attributes.
     *
     * @param workplaceId       The unique identifier of the workplace.
     * @param workplaceStartTime The start time of the workplace.
     * @param workplaceEndTime   The end time of the workplace.
     * @param user              The user assigned to the workplace.
     */
    public ReservedWorkplace(int workplaceId, Date workplaceStartTime, Date workplaceEndTime, User user) {
        super(workplaceId);
        this.workplaceStartTime = workplaceStartTime;
        this.workplaceEndTime = workplaceEndTime;
        this.user = user;
    }

    /**
     * Returns a string representation of the ReservedWorkplace object.
     *
     * @return A string representation including the workplace's ID, start and end times, and assigned user's login.
     */
    @Override
    public String toString() {
        return "ReservedWorkplace{id=" + getWorkplaceId() + ", start time='" + workplaceStartTime +
                "', end time='" + workplaceEndTime + "', user=" + (user != null ? user.getLogin() : "null") + '}';
    }
}
