package com.example.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.DTO.Conference;
import com.example.DTO.User;
import com.example.DTO.Workplace;

/**
 * Service class responsible for managing reservations of workplaces and conferences.
 */
public class ReservationService {
    private List<Workplace> workplaceReservations = new ArrayList<>();
    private List<Conference> conferenceReservations = new ArrayList<>();

    /**
     * Retrieves all booked workplaces on a specific date.
     *
     * @param date The date to check for bookings.
     * @return List of booked workplaces on the given date.
     */
    public List<Workplace> getBookedWorkplaces(Date date) {
        return workplaceReservations.stream()
                .filter(w -> !date.before(w.getWorkplaceStartTime()) && !date.after(w.getWorkplaceEndTime()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all booked conferences on a specific date.
     *
     * @param date The date to check for bookings.
     * @return List of booked conferences on the given date.
     */
    public List<Conference> getBookedConferences(Date date) {
        return conferenceReservations.stream()
                .filter(c -> !date.before(c.getConferenceStartTime()) && !date.after(c.getConferenceEndTime()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all booked workplaces that overlap with a specific date.
     *
     * @param date The date to check for overlapping bookings.
     * @return List of booked workplaces that overlap with the given date.
     */
    public List<Workplace> getBookedWorkplacesOnDate(Date date) {
        return workplaceReservations.stream()
                .filter(w -> isSameDay(w.getWorkplaceStartTime(), date) || isSameDay(w.getWorkplaceEndTime(), date))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all booked conferences that overlap with a specific date.
     *
     * @param date The date to check for overlapping bookings.
     * @return List of booked conferences that overlap with the given date.
     */
    public List<Conference> getBookedConferencesOnDate(Date date) {
        return conferenceReservations.stream()
                .filter(c -> isSameDay(c.getConferenceStartTime(), date) || isSameDay(c.getConferenceEndTime(), date))
                .collect(Collectors.toList());
    }

    /**
     * Books a workplace for a specific time slot with a user.
     *
     * @param workplace The workplace to book.
     * @param startTime The start time of the booking.
     * @param endTime The end time of the booking.
     * @param user The user booking the workplace.
     * @return true if the booking was successful, false otherwise.
     */
    public boolean bookWorkplace(Workplace workplace, Date startTime, Date endTime, User user) {
        if (isTimeSlotAvailableWorkplace(workplaceReservations, startTime, endTime)) {
            Workplace reservation = new Workplace(workplace.getWorkplaceId(), startTime, endTime, user);
            workplaceReservations.add(reservation);
            return true;
        }
        return false;
    }

    /**
     * Books a conference room for a specific time slot with a username.
     *
     * @param conference The conference to book.
     * @param startTime The start time of the booking.
     * @param endTime The end time of the booking.
     * @param username The username reserving the conference.
     * @return true if the booking was successful, false otherwise.
     */
    public boolean bookConference(Conference conference, Date startTime, Date endTime, String username) {
        if (isTimeSlotAvailableConference(conferenceReservations, startTime, endTime)) {
            Conference reservation = new Conference(conference.getConferenceId(), conference.getConferenceTitle(), startTime, endTime, username);
            conferenceReservations.add(reservation);
            return true;
        }
        return false;
    }

    /**
     * Cancels a workplace reservation based on its ID.
     *
     * @param reservationId The ID of the workplace reservation to cancel.
     * @return true if the cancellation was successful, false otherwise.
     */
    public boolean cancelWorkplaceReservation(int reservationId) {
        return workplaceReservations.removeIf(w -> w.getWorkplaceId() == reservationId);
    }

    /**
     * Cancels a conference reservation based on its ID.
     *
     * @param reservationId The ID of the conference reservation to cancel.
     * @return true if the cancellation was successful, false otherwise.
     */
    public boolean cancelConferenceReservation(int reservationId) {
        return conferenceReservations.removeIf(c -> c.getConferenceId() == reservationId);
    }

    /**
     * Checks if two dates fall on the same day.
     *
     * @param date1 The first date.
     * @param date2 The second date.
     * @return true if both dates are on the same day, false otherwise.
     */
    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Checks if a time slot for booking a conference is available.
     *
     * @param conferenceReservations The list of existing conference reservations.
     * @param startTime The start time of the new reservation.
     * @param endTime The end time of the new reservation.
     * @return true if the time slot is available, false otherwise.
     */
    private boolean isTimeSlotAvailableConference(List<Conference> conferenceReservations, Date startTime, Date endTime) {
        for (Conference conference : conferenceReservations) {
            if (startTime.before(conference.getConferenceEndTime()) && endTime.after(conference.getConferenceStartTime())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a time slot for booking a workplace is available.
     *
     * @param workplaceReservations The list of existing workplace reservations.
     * @param startTime The start time of the new reservation.
     * @param endTime The end time of the new reservation.
     * @return true if the time slot is available, false otherwise.
     */
    private boolean isTimeSlotAvailableWorkplace(List<Workplace> workplaceReservations, Date startTime, Date endTime) {
        for (Workplace workplace : workplaceReservations) {
            if (startTime.before(workplace.getWorkplaceEndTime()) && endTime.after(workplace.getWorkplaceStartTime())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints details of multiple workplaces.
     *
     * @param workplaces The list of workplaces to print.
     */
    public void printWorkplaceDetails(List<Workplace> workplaces) {
        // Print header
        System.out.println(String.format("%-10s\t%-30s\t%-30s\t%-20s", "ID", "Start Time", "End Time", "User"));
    
        // Print each workplace detail
        for (Workplace workplace : workplaces) {
            System.out.println(String.format("%-10d\t%-20s\t%-20s\t%-20s",
                    workplace.getWorkplaceId(),
                    workplace.getWorkplaceStartTime(),
                    workplace.getWorkplaceEndTime(),
                    workplace.getWorkplaceUser().getLogin()));
        }
    }

    /**
     * Prints details of multiple conferences.
     *
     * @param conferences The list of conferences to print.
     */
    public void printConferenceDetails(List<Conference> conferences) {
        // Print header
        System.out.println(String.format("%-20s\t%-30s\t%-30s\t%-30s\t%-20s", "ID", "Title", "Start Time", "End Time", "Reserved By"));
    
        // Print each conference detail
        for (Conference conference : conferences) {
            System.out.println(String.format("%-20s\t%-30s\t%-30s\t%-30s\t%-20s",
                    conference.getConferenceId(),
                    conference.getConferenceTitle(),
                    conference.getConferenceStartTime(),
                    conference.getConferenceEndTime(),
                    conference.getConferenceReservationUsername()));
        }
    }

    /**
     * Retrieves all conference reservations.
     *
     * @return List of all conference reservations.
     */
    public List<Conference> getAllConference() {
        return conferenceReservations.stream()
                    .collect(Collectors.toList());
    }

    /**
     * Retrieves all workplace reservations.
     *
     * @return List of all workplace reservations.
     */
    public List<Workplace> getAllWorkplace() {
        return workplaceReservations.stream()
                    .collect(Collectors.toList());
    }

}
