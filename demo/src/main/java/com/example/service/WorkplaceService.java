package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.DTO.User;
import com.example.DTO.Workplace;

/**
 * Service class responsible for managing workplace operations.
 */
public class WorkplaceService {
    private List<Workplace> workplaces = new ArrayList<>();
    private int currentId = 1;

    /**
     * Adds a new workplace reservation.
     *
     * @param workplaceStartTime The start time of the workplace reservation.
     * @param workplaceEndTime The end time of the workplace reservation.
     * @param user The user associated with the workplace reservation.
     * @return The created Workplace object.
     */
    public Workplace addWorkplace(Date workplaceStartTime, Date workplaceEndTime, User user){
        Workplace workplace = new Workplace(newId(), workplaceStartTime, workplaceEndTime, user);
        workplaces.add(workplace);
        return workplace;
    }

    /**
     * Retrieves all workplace reservations.
     *
     * @return List of all workplace reservations.
     */
    public List<Workplace> getAllWorkplace() {
        return workplaces.stream()
                    .collect(Collectors.toList());
    }

    /**
     * Updates the details of an existing workplace reservation.
     *
     * @param workplaceId The ID of the workplace reservation to update.
     * @param workplaceStartTime The new start time of the workplace reservation.
     * @param workplaceEndTime The new end time of the workplace reservation.
     * @param user The new user associated with the workplace reservation.
     * @return true if the workplace reservation was successfully updated, false otherwise.
     */
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

    /**
     * Deletes a workplace reservation based on its ID.
     *
     * @param id The ID of the workplace reservation to delete.
     * @return true if the workplace reservation was successfully deleted, false otherwise.
     */
    public boolean deleteWorkplace(int id) {
        return workplaces.removeIf(w -> w.getWorkplaceId() == id);
    }

    /**
     * Retrieves a workplace reservation by its ID.
     *
     * @param id The ID of the workplace reservation to retrieve.
     * @return The Workplace object if found, null otherwise.
     */
    public Workplace getWorkplaceById(int id) {
        Optional<Workplace> workplace = workplaces.stream().filter(w -> w.getWorkplaceId() == id).findFirst();
        return workplace.orElse(null);
    }

    /**
     * Generates a new unique ID for workplace reservations.
     *
     * @return A new unique ID.
     */
    private int newId(){
        return currentId++;
    } 

    /**
     * Prints details of each workplace reservation in the provided list.
     *
     * @param workplaces The list of workplace reservations to print.
     */
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
