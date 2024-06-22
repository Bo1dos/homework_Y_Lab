package com.example;

import com.example.DTO.User;
import com.example.DTO.Workplace;
import com.example.service.WorkplaceService;
import org.junit.jupiter.api.BeforeEach;
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
    public void testAddWorkplace() {
        User user = new User(1, "testUser", "password", "role");
        Workplace workplace = workplaceService.addWorkplace(new Date(), new Date(), user);
        assertThat(workplace).isNotNull();
        assertThat(workplace.getWorkplaceUser().getLogin()).isEqualTo("testUser");
    }

    @Test
    public void testGetAllWorkplaces() {
        User user = new User(1, "testUser", "password", "role");
        workplaceService.addWorkplace(new Date(), new Date(), user);
        List<Workplace> workplaces = workplaceService.getAllWorkplace();
        assertThat(workplaces).hasSize(1);
    }

    @Test
    public void testUpdateWorkplace() {
        User user = new User(1, "testUser", "password", "role");
        Workplace workplace = workplaceService.addWorkplace(new Date(), new Date(), user);
        boolean updated = workplaceService.updateWorkplace(workplace.getWorkplaceId(), new Date(), new Date(), user);
        assertTrue(updated);
    }

    @Test
    public void testDeleteWorkplace() {
        User user = new User(1, "testUser", "password", "role");
        Workplace workplace = workplaceService.addWorkplace(new Date(), new Date(), user);
        boolean deleted = workplaceService.deleteWorkplace(workplace.getWorkplaceId());
        assertTrue(deleted);
        assertThat(workplaceService.getAllWorkplace()).isEmpty();
    }
}

