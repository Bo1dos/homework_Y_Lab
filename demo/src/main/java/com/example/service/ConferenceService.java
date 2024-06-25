package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.DTO.Conference;

public class ConferenceService {
    List<Conference> conferences = new ArrayList<>();
    private int currentId = 1;
    
    //Create
    public Conference addConference(String conferenceTitle, Date conferenceStartTime, Date conferenceEndTime, String conferenceBookedUsername){
        Conference conference = new Conference(newId(), conferenceTitle, conferenceStartTime, conferenceEndTime, conferenceBookedUsername);
        conferences.add(conference);
        return conference;
    }

    //Read
    public List<Conference> getAllConference() {
        return conferences.stream()
                    .collect(Collectors.toList());
    }

    //Update
    public boolean updateConference(int conferenceId, String conferenceTitle, Date conferenceStartTime, Date conferenceEndTime, String conferenceReservationUsername) {
        Conference conference = getConferenceById(conferenceId);
        
        if (conference != null) {
            if (isTitleTaken(conferenceTitle, conferenceId)) {
                return false;
            }
            conference.setConferenceId(conferenceId);
            conference.setConferenceTitle(conferenceTitle);
            conference.setConferenceStartTime(conferenceStartTime);
            conference.setConferenceEndTime(conferenceEndTime);
            conference.setConferenceReservationUsername(conferenceReservationUsername);


            return true;
        }
        return false;
    }

    //Delete
    public boolean deleteConference(int id) {
        return conferences.removeIf(c -> c.getConferenceId() == id);
    }


    // Helper methods
    public Conference getConferenceById(int id) {
        Optional<Conference> conference = conferences.stream().filter(c -> c.getConferenceId() == id).findFirst();
        return conference.orElse(null);
    }


    public Conference getConferenceByTitle(String title) {
        
        Optional<Conference> conferenence = conferences.stream().filter(c -> c.getConferenceTitle().equals(title)).findFirst();

        return conferenence.orElse(null);
    }

    public boolean isTitleTaken(String title, int excludeConferenceId) {
        for (Conference conference : conferences) {
            if (conference.getConferenceTitle().equals(title) && conference.getConferenceId() != excludeConferenceId) {
                return true;
            }
        }
        return false;
    }

    private int newId(){
        return currentId++;
    } 


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
