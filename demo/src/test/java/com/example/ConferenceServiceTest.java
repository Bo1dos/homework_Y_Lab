package com.example;

import com.example.domain.Conference;
import com.example.service.ConferenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Adding a conference should create a valid conference object")
    public void testAddConference() {
        Conference conference = conferenceService.addConference("Test Conference");
        assertThat(conference).isNotNull();
        assertThat(conference.getConferenceTitle()).isEqualTo("Test Conference");
    }

    @Test
    @DisplayName("Updating a conference should change its title")
    public void testUpdateConference() {
        Conference conference = conferenceService.addConference("Test Conference1");
        boolean updated = conferenceService.updateConference(conference.getConferenceId(), "Updated Conference");
        assertTrue(updated);
        assertThat(conferenceService.getConferenceById(conference.getConferenceId()).getConferenceTitle()).isEqualTo("Updated Conference");
    }

    @Test
    @DisplayName("Deleting a conference should remove it from the list")
    public void testDeleteConference() {
        Conference conference = conferenceService.addConference("Test Conference2");
        boolean deleted = conferenceService.deleteConference(conference.getConferenceId());
        assertTrue(deleted);
        assertThat(conferenceService.getAllConference()).isEmpty();
    }

    @Test
    @DisplayName("Checking if title is taken should return true for existing title")
    public void testIsTitleTaken() {
        conferenceService.addConference("Test Conference3");
        boolean isTaken = conferenceService.isTitleTaken("Test Conference", -1);
        assertTrue(isTaken);
    }

    @Test
    @DisplayName("Getting all conferences should return a list with one conference")
    public void testGetAllConferences() {
        conferenceService.addConference("Test Conference4");
        List<Conference> conferences = conferenceService.getAllConference();
        assertThat(conferences).hasSize(4);
    }
}
