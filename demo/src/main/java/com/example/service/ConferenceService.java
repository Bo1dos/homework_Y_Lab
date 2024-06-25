package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.domain.Conference;

/**
 * Service class responsible for managing conferences.
 */
public class ConferenceService {
    private static List<Conference> conferences = new ArrayList<>();
    private static int currentId = 1;
    
    /**
     * Adds a new conference to the list of conferences.
     *
     * @param conferenceTitle The title of the conference.
     * @return The newly added Conference object.
     */
    public Conference addConference(String conferenceTitle){
        Conference conference = new Conference(newId(), conferenceTitle);
        conferences.add(conference);
        return conference;
    }

    /**
     * Retrieves all conferences currently stored.
     *
     * @return A list of all conferences.
     */
    public List<Conference> getAllConference() {
        return conferences;
    }

    /**
     * Updates an existing conference with new information.
     *
     * @param conferenceId The ID of the conference to update.
     * @param conferenceTitle The new title of the conference.
     * @param conferenceStartTime The new start time of the conference.
     * @param conferenceEndTime The new end time of the conference.
     * @param conferenceReservationUsername The new username of the user who reserved the conference.
     * @return true if the conference was successfully updated, false otherwise.
     */
    public boolean updateConference(int conferenceId, String conferenceTitle) {
        Conference conference = getConferenceById(conferenceId);
        
        if (conference != null) {
            if (isTitleTaken(conferenceTitle, conferenceId)) {
                return false;
            }
            conference.setConferenceId(conferenceId);
            conference.setConferenceTitle(conferenceTitle);


            return true;
        }
        return false;
    }

    /**
     * Deletes a conference from the list based on its ID.
     *
     * @param id The ID of the conference to delete.
     * @return true if the conference was successfully deleted, false otherwise.
     */
    public boolean deleteConference(int id) {
        return conferences.removeIf(c -> c.getConferenceId() == id);
    }

    /**
     * Retrieves a conference based on its ID.
     *
     * @param id The ID of the conference to retrieve.
     * @return The Conference object if found, or null if not found.
     */
    public Conference getConferenceById(int id) {
        Optional<Conference> conference = conferences.stream().filter(c -> c.getConferenceId() == id).findFirst();
        return conference.orElse(null);
    }

    /**
     * Retrieves a conference based on its title.
     *
     * @param title The title of the conference to retrieve.
     * @return The Conference object if found, or null if not found.
     */
    public Conference getConferenceByTitle(String title) {
        Optional<Conference> conference = conferences.stream().filter(c -> c.getConferenceTitle().equals(title)).findFirst();
        return conference.orElse(null);
    }

    /**
     * Checks if a conference title is already taken, excluding the conference with the specified ID.
     *
     * @param title The title to check.
     * @param excludeConferenceId The ID of the conference to exclude from the check.
     * @return true if the title is already taken by another conference, false otherwise.
     */
    public boolean isTitleTaken(String title, int excludeConferenceId) {
        for (Conference conference : conferences) {
            if (conference.getConferenceTitle().equals(title) && conference.getConferenceId() != excludeConferenceId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a new unique ID for a conference.
     *
     * @return The new ID.
     */
    private int newId(){
        return currentId++;
    } 

    /**
     * Prints details of multiple conferences.
     *
     * @param conferences The list of conferences to print.
     */
    public void printConferenceDetails(List<Conference> conferences) {
        // Print header
        System.out.println(String.format("%-20s\t%-20s\t", "ID", "Title"));
    
        // Print each conference detail
        for (Conference conference : conferences) {
            System.out.println(String.format("%-20s\t%-20s\t",
                    conference.getConferenceId(),
                    conference.getConferenceTitle()));
        }
    }

}