package com.example.Menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.App;
import com.example.DTO.User;
import com.example.forms.Login;
import com.example.service.ConferenceService;
import com.example.service.ReservationService;
import com.example.service.UserService;
import com.example.service.WorkplaceService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuTest {

    @Mock
    private UserService userService;

    @Mock
    private ConferenceService conferenceService;

    @Mock
    private WorkplaceService workplaceService;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private App menu;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("3\n".getBytes());

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @Test
    public void testMainMenu_exitCommand() {
        menu.mainMenu();
        assertThat(outContent.toString()).contains("Enter command:");
    }

}

