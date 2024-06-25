package com.example.service;


import com.example.DTO.Conference;
import com.example.DTO.User;
import com.example.DTO.Workplace;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {
    private List<Workplace> workplaceReservations = new ArrayList<>();
    private List<Conference> conferenceReservations = new ArrayList<>();

    // View booked slots for Reservation on a specific date
    public List<Workplace> getBookedWorkplaces(Date date) {
        return workplaceReservations.stream()
                .filter(w -> !date.before(w.getWorkplaceStartTime()) && !date.after(w.getWorkplaceEndTime()))
                .collect(Collectors.toList());
    }

    public List<Conference> getBookedConferences(Date date) {
        return conferenceReservations.stream()
                .filter(c -> !date.before(c.getConferenceStartTime()) && !date.after(c.getConferenceEndTime()))
                .collect(Collectors.toList());
    }

    // Helper method to check if two dates fall on the same day
    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    // View booked slots for Reservation on a specific date
    public List<Workplace> getBookedWorkplacesOnDate(Date date) {
        return workplaceReservations.stream()
                .filter(w -> isSameDay(w.getWorkplaceStartTime(), date) || isSameDay(w.getWorkplaceEndTime(), date))
                .collect(Collectors.toList());
    }

    public List<Conference> getBookedConferencesOnDate(Date date) {
        return conferenceReservations.stream()
                .filter(c -> isSameDay(c.getConferenceStartTime(), date) || isSameDay(c.getConferenceEndTime(), date))
                .collect(Collectors.toList());
    }

    // Book a workplace
    public boolean bookWorkplace(Workplace workplace, Date startTime, Date endTime, User user) {
        if (isTimeSlotAvailableWorkplace(workplaceReservations, startTime, endTime)) {
            Workplace Reservation = new Workplace(workplace.getWorkplaceId(), startTime, endTime, user);
            workplaceReservations.add(Reservation);
            return true;
        }
        return false;
    }

    // Book a conference room
    public boolean bookConference(Conference conference, Date startTime, Date endTime, String username) {
        if (isTimeSlotAvailableConference(conferenceReservations, startTime, endTime)) {
            Conference Reservation = new Conference(conference.getConferenceId(), conference.getConferenceTitle(), startTime, endTime, username);
            conferenceReservations.add(Reservation);
            return true;
        }
        return false;
    }

    // Cancel a workplace Reservation
    public boolean cancelWorkplaceReservation(int ReservationId) {
        return workplaceReservations.removeIf(w -> w.getWorkplaceId() == ReservationId);
    }

    // Cancel a conference room Reservation
    public boolean cancelConferenceReservation(int ReservationId) {
        return conferenceReservations.removeIf(c -> c.getConferenceId() == ReservationId);
    }

    private boolean isTimeSlotAvailableConference(List<Conference> conferenceReservations, Date startTime, Date endTime) {
        for (Conference conference : conferenceReservations) {
            if (startTime.before(conference.getConferenceEndTime()) && endTime.after(conference.getConferenceStartTime())) {
                return false;
            }
        }
        return true;
    }

    private boolean isTimeSlotAvailableWorkplace(List<Workplace> workplaceReservations, Date startTime, Date endTime) {
        for (Workplace workplace : workplaceReservations) {
            if (startTime.before(workplace.getWorkplaceEndTime()) && endTime.after(workplace.getWorkplaceStartTime())) {
                return false;
            }
        }
        return true;
    }

    private boolean isSameTime(Date date1, Date date2) {
        
        return date1.equals(date2);
         
    }

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
    

    public List<Conference> getAllConference() {
        return conferenceReservations.stream()
                    .collect(Collectors.toList());
    }

    public List<Workplace> getAllWorkplace() {
        return workplaceReservations.stream()
                    .collect(Collectors.toList());
    }

}
