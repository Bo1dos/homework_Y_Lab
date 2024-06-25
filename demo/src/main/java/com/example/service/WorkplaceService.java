package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.DTO.User;
import com.example.DTO.Workplace;

public class WorkplaceService {
    List<Workplace> workplaces = new ArrayList<>();
    private int currentId = 1;
    
    //Create
    public Workplace addWorkplace(Date workplaceStartTime, Date workplaceEndTime, User user){
        Workplace workplace = new Workplace(newId(), workplaceStartTime, workplaceEndTime, user);
        workplaces.add(workplace);
        return workplace;
    }

    //Read
    public List<Workplace> getAllWorkplace() {
        return workplaces.stream()
                    .collect(Collectors.toList());
    }

    //Update
    public boolean updateWorkplace(int workplaceId, Date workplaceStartTime, Date workplaceEndTime, User user) {
        Workplace workplace = getWorkplaceById(workplaceId);
        
        if (workplace != null) {

            workplace.setWorkplaceId(workplaceId);
            workplace.setWorkplaceStartTime(workplaceStartTime);
            workplace.setWorkplaceEndTime(workplaceEndTime);
            workplace.setWorkplaceUser(user);


            return true;
        }
        return false;
    }

    //Delete
    public boolean deleteWorkplace(int id) {
        return workplaces.removeIf(w -> w.getWorkplaceId() == id);
    }


    // Helper methods
    public Workplace getWorkplaceById(int id) {
        Optional<Workplace> workplace = workplaces.stream().filter(w -> w.getWorkplaceId() == id).findFirst();
        return workplace.orElse(null);
    }

    private int newId(){
        return currentId++;
    } 

    public void printWorkplaceDetails(List<Workplace> workplaces) {
        // Print header
        System.out.println(String.format("%-10s\t", "ID"));
    
        // Print each workplace detail
        for (Workplace workplace : workplaces) {
            System.out.println(String.format("%-10d\t",
                    workplace.getWorkplaceId()));
        }
    }

}
