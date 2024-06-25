package com.example;

import com.example.domain.User;
import com.example.domain.Workplace;
import com.example.enums.UserRole;
import com.example.service.WorkplaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkplaceServiceTest {

    private WorkplaceService workplaceService;

    @BeforeEach
    public void setUp() {
        workplaceService = new WorkplaceService();
    }



    @Test
    @DisplayName("Delete Workplace Test")
    public void testDeleteWorkplace() {
        Workplace workplace = workplaceService.addWorkplace();
        boolean deleted = workplaceService.deleteWorkplace(workplace.getWorkplaceId());
        assertTrue(deleted);
    }

    @Test
    @DisplayName("Get All Workplaces Test")
    public void testGetAllWorkplaces() {
        workplaceService.addWorkplace();
        List<Workplace> workplaces = workplaceService.getAllWorkplace();
        assertThat(workplaces).hasSize(1);
    }



    @Test
    @DisplayName("Add Workplace Test")
    public void testAddWorkplace() {
        Workplace workplace = workplaceService.addWorkplace();
        assertThat(workplace).isNotNull();
        assertThat(workplace.getWorkplaceId()).isEqualTo(3);
    }


}

