package com.example;

import com.example.domain.Conference;
import com.example.domain.ReservedConference;
import com.example.domain.ReservedWorkplace;
import com.example.domain.User;
import com.example.domain.Workplace;
import com.example.enums.UserRole;
import com.example.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Book Conference with normal Date Test ")
    public void testBookConference() {
        String dateString1 = "24-06-2024 20:00";
        String dateString2 = "24-06-2024 21:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        
        try {
            Date date1 = dateFormat.parse(dateString1);
            Date date2 = dateFormat.parse(dateString2);
            Conference conference = new Conference(1, "Test Conference");
            boolean booked = reservationService.bookConference(conference, date1, date2, "testUser");
            assertTrue(booked);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }

    @Test
    @DisplayName("Get Booked Conferences on Date Test")
    public void testGetBookedConferencesOnDate() {
        Conference conference = new Conference(1, "Test Conference");
        reservationService.bookConference(conference, new Date(), new Date(), "testUser");
        List<ReservedConference> bookedConferences = reservationService.getBookedConferencesOnDate(new Date());
        assertThat(bookedConferences).hasSize(1);
    }

    @Test
    @DisplayName("Cancel Conference Reservation Test")
    public void testCancelConferenceReservation() {
        Conference conference = new Conference(1, "Test Conference");
        reservationService.bookConference(conference, new Date(), new Date(), "testUser");
        boolean cancelled = reservationService.cancelConferenceReservation(conference.getConferenceId());
        assertTrue(cancelled);
    }

    @Test
    @DisplayName("Book Workplace  with normal Date Test")
    public void testBookWorkplace() {
        User user = new User(1, "testUser", "password", UserRole.USER);
        ReservedWorkplace workplace = new ReservedWorkplace(1, new Date(), new Date(), user);
        boolean booked = reservationService.bookWorkplace(workplace, new Date(), new Date(), user);
        assertTrue(booked);
    }

    @Test
    @DisplayName("Get Booked Workplaces on Date Test")
    public void testGetBookedWorkplacesOnDate() {
        User user = new User(1, "testUser", "password", UserRole.USER);
        ReservedWorkplace workplace = new ReservedWorkplace(1, new Date(), new Date(), user);
        reservationService.bookWorkplace(workplace, new Date(), new Date(), user);
        List<ReservedWorkplace> bookedWorkplaces = reservationService.getBookedWorkplacesOnDate(new Date());
        assertThat(bookedWorkplaces).hasSize(1);
    }

    @Test
    @DisplayName("Cancel Workplace Reservation Test")
    public void testCancelWorkplaceReservation() {
        User user = new User(1, "testUser", "password", UserRole.USER);
        ReservedWorkplace workplace = new ReservedWorkplace(1, new Date(), new Date(), user);
        reservationService.bookWorkplace(workplace, new Date(), new Date(), user);
        boolean cancelled = reservationService.cancelWorkplaceReservation(workplace.getWorkplaceId());
        assertTrue(cancelled);
    }
}

