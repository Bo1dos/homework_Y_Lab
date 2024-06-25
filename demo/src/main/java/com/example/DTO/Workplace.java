package com.example.DTO;

import java.util.Date;

public class Workplace {
    private int workplaceId;
    private Date workplaceStartTime;
    private Date workplaceEndTime;
    private User user;



    public Workplace(int workplaceId, Date workplaceStartTime, Date workplaceEndTime, User user) {
        this.workplaceId = workplaceId;
        this.workplaceStartTime = workplaceStartTime;
        this.workplaceEndTime = workplaceEndTime;
        this.user = user;
    }
    public int getWorkplaceId() {
        return workplaceId;
    }
    public void setWorkplaceId(int workplaceId) {
        this.workplaceId = workplaceId;
    }
    public Date getWorkplaceStartTime() {
        return workplaceStartTime;
    }
    public void setWorkplaceStartTime(Date workplaceStartTime) {
        this.workplaceStartTime = workplaceStartTime;
    }
    public Date getWorkplaceEndTime() {
        return workplaceEndTime;
    }
    public void setWorkplaceEndTime(Date workplaceEndTime) {
        this.workplaceEndTime = workplaceEndTime;
    }
    public User getWorkplaceUser() {
        return user;
    }
    public void setWorkplaceUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Workspace{id=" + workplaceId + ", start time='" + workplaceStartTime + 
        ", end time='" + workplaceEndTime + "', user=" + user.getLogin() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workplace workplace = (Workplace) o;

        return workplaceId == workplace.workplaceId;
    }

    @Override
    public int hashCode() {
        return workplaceId;
    }

}
