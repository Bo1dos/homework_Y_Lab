package com.example;

import com.example.DTO.Conference;
import com.example.DTO.User;
import com.example.DTO.Workplace;
import com.example.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationServiceTest {

    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        reservationService = new ReservationService();
    }

    @Test
    public void testBookConference() {
        String dateString1 = "24-06-2024 20:00";
        String dateString2 = "24-06-2024 21:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        
        try {
            Date date1 = dateFormat.parse(dateString1);
            Date date2 = dateFormat.parse(dateString2);
            Conference conference = new Conference(1, "Test Conference", new Date(), new Date(), null);
            boolean booked = reservationService.bookConference(conference, date1, date2, "testUser");
            assertTrue(booked);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }

    @Test
    public void testGetBookedConferencesOnDate() {
        Conference conference = new Conference(1, "Test Conference", new Date(), new Date(), "testUser");
        reservationService.bookConference(conference, new Date(), new Date(), "testUser");
        List<Conference> bookedConferences = reservationService.getBookedConferencesOnDate(new Date());
        assertThat(bookedConferences).hasSize(1);
    }

    @Test
    public void testCancelConferenceReservation() {
        Conference conference = new Conference(1, "Test Conference", new Date(), new Date(), "testUser");
        reservationService.bookConference(conference, new Date(), new Date(), "testUser");
        boolean cancelled = reservationService.cancelConferenceReservation(conference.getConferenceId());
        assertTrue(cancelled);
    }

    @Test
    public void testBookWorkplace() {
        User user = new User(1, "testUser", "password", "role");
        Workplace workplace = new Workplace(1, new Date(), new Date(), user);
        boolean booked = reservationService.bookWorkplace(workplace, new Date(), new Date(), user);
        assertTrue(booked);
    }

    @Test
    public void testGetBookedWorkplacesOnDate() {
        User user = new User(1, "testUser", "password", "role");
        Workplace workplace = new Workplace(1, new Date(), new Date(), user);
        reservationService.bookWorkplace(workplace, new Date(), new Date(), user);
        List<Workplace> bookedWorkplaces = reservationService.getBookedWorkplacesOnDate(new Date());
        assertThat(bookedWorkplaces).hasSize(1);
    }

    @Test
    public void testCancelWorkplaceReservation() {
        User user = new User(1, "testUser", "password", "role");
        Workplace workplace = new Workplace(1, new Date(), new Date(), user);
        reservationService.bookWorkplace(workplace, new Date(), new Date(), user);
        boolean cancelled = reservationService.cancelWorkplaceReservation(workplace.getWorkplaceId());
        assertTrue(cancelled);
    }
}

