package com.example;

import com.example.DTO.Conference;
import com.example.service.ConferenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConferenceServiceTest {

    private ConferenceService conferenceService;

    @BeforeEach
    public void setUp() {
        conferenceService = new ConferenceService();
    }

    @Test
    public void testAddConference() {
        Conference conference = conferenceService.addConference("Test Conference", new Date(), new Date(), "testUser");
        assertThat(conference).isNotNull();
        assertThat(conference.getConferenceTitle()).isEqualTo("Test Conference");
    }

    @Test
    public void testGetAllConferences() {
        conferenceService.addConference("Test Conference", new Date(), new Date(), "testUser");
        List<Conference> conferences = conferenceService.getAllConference();
        assertThat(conferences).hasSize(1);
    }

    @Test
    public void testUpdateConference() {
        Conference conference = conferenceService.addConference("Test Conference", new Date(), new Date(), "testUser");
        boolean updated = conferenceService.updateConference(conference.getConferenceId(), "Updated Conference", new Date(), new Date(), "updatedUser");
        assertTrue(updated);
        assertThat(conferenceService.getConferenceById(conference.getConferenceId()).getConferenceTitle()).isEqualTo("Updated Conference");
    }

    @Test
    public void testDeleteConference() {
        Conference conference = conferenceService.addConference("Test Conference", new Date(), new Date(), "testUser");
        boolean deleted = conferenceService.deleteConference(conference.getConferenceId());
        assertTrue(deleted);
        assertThat(conferenceService.getAllConference()).isEmpty();
    }

    @Test
    public void testIsTitleTaken() {
        conferenceService.addConference("Test Conference", new Date(), new Date(), "testUser");
        boolean isTaken = conferenceService.isTitleTaken("Test Conference", -1);
        assertTrue(isTaken);
    }
}

