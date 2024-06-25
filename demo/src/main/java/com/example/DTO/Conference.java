package com.example.DTO;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conference {
    private int conferenceId;
    private String conferenceTitle;
    private Date conferenceStartTime;
    private Date conferenceEndTime;
    private String conferenceReservationUsername;


    //List<Workplace> workplaces = new ArrayList<>();
    
    public Conference(int conferenceId, String conferenceTitle, Date conferenceStartTime, Date conferenceEndTime, String conferenceReservationUsername) {
        this.conferenceId = conferenceId;
        this.conferenceTitle = conferenceTitle;
        this.conferenceStartTime = conferenceStartTime;
        this.conferenceEndTime = conferenceEndTime;
        this.conferenceReservationUsername = conferenceReservationUsername;
    }

    
    
    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceTitle() {
        return conferenceTitle;
    }

    public void setConferenceTitle(String conferenceTitle) {
        this.conferenceTitle = conferenceTitle;
    }

    public Date getConferenceStartTime() {
        return conferenceStartTime;
    }

    public void setConferenceStartTime(Date conferenceStartTime) {
        this.conferenceStartTime = conferenceStartTime;
    }

    public Date getConferenceEndTime() {
        return conferenceEndTime;
    }

    public void setConferenceEndTime(Date conferenceEndTime) {
        this.conferenceEndTime = conferenceEndTime;
    }

    public String getConferenceReservationUsername() {
        return conferenceReservationUsername;
    }

    public void setConferenceReservationUsername(String conferenceReservationUsername) {
        this.conferenceReservationUsername = conferenceReservationUsername;
    }


    @Override
    public String toString() {
        return "Conference{id=" + conferenceId + ", title='" + conferenceTitle + 
        ", start time='" + conferenceStartTime + ", end time='" + conferenceEndTime + 
        "', booker's name=" + conferenceReservationUsername + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        return conferenceId == that.conferenceId;
    }

    @Override
    public int hashCode() {
        return conferenceId;
    }
 
}
