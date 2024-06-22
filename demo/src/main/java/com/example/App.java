package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.DTO.Conference;
import com.example.DTO.User;
import com.example.DTO.Workplace;
import com.example.forms.Login;
import com.example.forms.Registration;
import com.example.service.ConferenceService;
import com.example.service.ReservationService;
import com.example.service.UserService;
import com.example.service.WorkplaceService;

/**
 * Main application class that manages user interaction and system operations.
 */
public class App {
    private static UserService userService;
    private static WorkplaceService workplaceService;
    private static ConferenceService conferenceService;
    private static ReservationService reservationService;
    private static User currentUser;

    /**
     * Main method to start the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        initializeServices();
        initializeTestData(); // For testing purposes
        mainMenu();
    }

    /**
     * Initializes all required services.
     */
    private static void initializeServices() {
        userService = new UserService();
        workplaceService = new WorkplaceService();
        conferenceService = new ConferenceService();
        reservationService = new ReservationService();
    }

    /**
     * Initializes test data for demonstration purposes.
     */
    private static void initializeTestData() {
        // Adding test data
        userService.addUser("123", "123", "normal");
        userService.addUser("456", "qqq", "normal");
        conferenceService.addConference("Conf1", null, null, null);
        conferenceService.addConference("Conf2", null, null, null);
        workplaceService.addWorkplace(null, null, null);
        workplaceService.addWorkplace(null, null, null);
    }

    /**
     * Displays the main menu and handles user input.
     */
    public static void mainMenu() {
        String mainMenuString = "1. Login \n" +
                                "2. Registration \n" +
                                "3. Exit \n" +
                                "Enter command: ";

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(mainMenuString);

            try {
                int enteredCommand = scanner.nextInt();

                if (enteredCommand == 3) {
                    break;
                }

                processMainCommand(enteredCommand);
            } catch (InputMismatchException e) {
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /**
     * Processes commands from the main menu.
     *
     * @param enteredCommand The command entered by the user.
     */
    public static void processMainCommand(int enteredCommand) {
        switch (enteredCommand) {
            case 1:
                // Handle user login
                Login login = new Login(userService);
                if (login.getLoginForm()) {
                    currentUser = login.getCurrentUser();
                    userMenu();
                }
                break;
            case 2:
                // Handle user registration
                Registration registration = new Registration(userService);
                registration.getRegistrationForm();
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    /**
     * Displays the user menu and handles user input after login.
     */
    private static void userMenu() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        while (true) {
            System.out.println("1. View all conferences and workplaces");
            System.out.println("2. View all reserved conferences and workplaces");
            System.out.println("3. View all my conferences");
            System.out.println("4. View all my workplaces");
            System.out.println("5. View available slots for a specific date and time");
            System.out.println("6. Add new conference");
            System.out.println("7. Edit conference");
            System.out.println("8. Add new workplace");
            System.out.println("0. Exit");
            System.out.print("Enter command: ");

            try {
                int command = scanner.nextInt();

                if (command == 0) {
                    // Save user changes before exiting
                    userService.updateUser(currentUser.getId(), currentUser.getLogin(), currentUser.getPass(), currentUser.getRole());
                    break;
                }

                processUserCommand(command, scanner, dateFormat);
            } catch (InputMismatchException e) {
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /**
     * Processes commands from the user menu.
     *
     * @param command   The command entered by the user.
     * @param scanner   Scanner object to read user input.
     * @param dateFormat SimpleDateFormat object for date parsing.
     */
    public static void processUserCommand(int command, Scanner scanner, SimpleDateFormat dateFormat) {
        switch (command) {
            case 1:
                // View all conferences and workplaces
                displayAllConferencesAndWorkplaces();
                viewAllSubMenu(scanner, dateFormat);
                break;
            case 2:
                // View all reserved conferences and workplaces
                displayReservedConferencesAndWorkplaces();
                viewReservedSubMenu(scanner, dateFormat);
                break;
            case 3:
                // View all conferences reserved by the current user
                displayMyConferences();
                break;
            case 4:
                // View all workplaces reserved by the current user
                displayMyWorkplaces();
                break;
            case 5:
                // View available slots for a specific date and time
                viewAvailableSlots(scanner, dateFormat);
                break;
            case 6:
                // Add new conference
                addNewConference(scanner);
                break;
            case 7:
                // Edit conference
                editConference(scanner);
                break;
            case 8:
                // Add new workplace
                addNewWorkplace();
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    /**
     * Displays all conferences and workplaces.
     */
    private static void displayAllConferencesAndWorkplaces() {
        System.out.println("All Conferences:");
        conferenceService.printConferenceDetails(conferenceService.getAllConference());
        System.out.println("All Workplaces:");
        workplaceService.printWorkplaceDetails(workplaceService.getAllWorkplace());
    }

    /**
     * Displays all reserved conferences and workplaces.
     */
    private static void displayReservedConferencesAndWorkplaces() {
        System.out.println("All Reserved Conferences:");
        reservationService.printConferenceDetails(reservationService.getAllConference());
        System.out.println("All Reserved Workplaces:");
        reservationService.printWorkplaceDetails(reservationService.getAllWorkplace());
    }

    /**
     * Displays conferences reserved by the current user.
     */
    private static void displayMyConferences() {
        System.out.println("My Conferences:");
        List<Conference> myConferences = reservationService.getAllConference().stream()
                .filter(c -> c.getConferenceReservationUsername().equals(currentUser.getLogin()))
                .collect(Collectors.toList());
        reservationService.printConferenceDetails(myConferences);
    }

    /**
     * Displays workplaces reserved by the current user.
     */
    private static void displayMyWorkplaces() {
        System.out.println("My Workplaces:");
        List<Workplace> myWorkplaces = reservationService.getAllWorkplace().stream()
                .filter(w -> w.getWorkplaceUser().equals(currentUser))
                .collect(Collectors.toList());
        reservationService.printWorkplaceDetails(myWorkplaces);
    }

    /**
     * Displays available conference and workplace slots for a specific date and time.
     *
     * @param scanner   Scanner object to read user input.
     * @param dateFormat SimpleDateFormat object for date parsing.
     */
    private static void viewAvailableSlots(Scanner scanner, SimpleDateFormat dateFormat) {
        System.out.print("Enter date and time (dd-MM-yyyy HH:mm): ");
        String dateInput = scanner.nextLine();
        try {
            Date date = dateFormat.parse(dateInput);
            List<Conference> bookedConferences = reservationService.getBookedConferences(date);
            List<Conference> availableConferences = conferenceService.getAllConference().stream()
                    .filter(c -> !bookedConferences.contains(c))
                    .collect(Collectors.toList());

            List<Workplace> bookedWorkplaces = reservationService.getBookedWorkplaces(date);
            List<Workplace> availableWorkplaces = workplaceService.getAllWorkplace().stream()
                    .filter(w -> !bookedWorkplaces.contains(w))
                    .collect(Collectors.toList());

            System.out.println("Available Conferences:");
            conferenceService.printConferenceDetails(availableConferences);
            System.out.println("Available Workplaces:");
            workplaceService.printWorkplaceDetails(availableWorkplaces);
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    /**
     * Adds a new conference based on user input.
     *
     * @param scanner Scanner object to read user input.
     */
    private static void addNewConference(Scanner scanner) {
        System.out.print("Enter conference title: ");
        String title = scanner.nextLine();
        conferenceService.addConference(title, null, null, currentUser.getLogin());
    }

    /**
     * Edits an existing conference based on user input.
     *
     * @param scanner Scanner object to read user input.
     */
    private static void editConference(Scanner scanner) {
        System.out.print("Enter conference ID to edit: ");
        int conferenceId = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        System.out.print("Enter new conference title: ");
        String newTitle = scanner.nextLine();
        conferenceService.updateConference(conferenceId, newTitle, null, null, null);
    }

    /**
     * Adds a new workplace reservation for the current user.
     */
    private static void addNewWorkplace() {
        workplaceService.addWorkplace(null, null, currentUser);
    }

    /**
     * Displays the submenu for viewing all conferences and workplaces.
     *
     * @param scanner   Scanner object to read user input.
     * @param dateFormat SimpleDateFormat object for date parsing.
     */
    private static void viewAllSubMenu(Scanner scanner, SimpleDateFormat dateFormat) {
        while (true) {
            System.out.println("1. Book a conference room");
            System.out.println("2. Book a workplace");
            System.out.println("0. Back to previous menu");
            System.out.print("Enter command: ");

            try {
                int command = scanner.nextInt();
                if (command == 0) {
                    break;
                }
                processViewAllSubCommand(command, scanner, dateFormat);
            } catch (InputMismatchException e) {
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /**
     * Processes commands from the submenu for viewing all conferences and workplaces.
     *
     * @param command   The command entered by the user.
     * @param scanner   Scanner object to read user input.
     * @param dateFormat SimpleDateFormat object for date parsing.
     */
    private static void processViewAllSubCommand(int command, Scanner scanner, SimpleDateFormat dateFormat) {
        switch (command) {
            case 1:
                // Book a conference room
                bookConferenceRoom(scanner, dateFormat);
                break;
            case 2:
                // Book a workplace
                bookWorkplace(scanner, dateFormat);
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    /**
     * Books a conference room based on user input.
     *
     * @param scanner   Scanner object to read user input.
     * @param dateFormat SimpleDateFormat object for date parsing.
     */
    private static void bookConferenceRoom(Scanner scanner, SimpleDateFormat dateFormat) {
        scanner.nextLine(); // Consume the newline left by nextInt
        System.out.print("Enter conference ID to book: ");
        int conferenceId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt
        System.out.print("Enter booking start time (dd-MM-yyyy HH:mm): ");
        String startTimeInput = scanner.nextLine();
        System.out.print("Enter booking end time (dd-MM-yyyy HH:mm): ");
        String endTimeInput = scanner.nextLine();
        try {
            Date startTime = dateFormat.parse(startTimeInput);
            Date endTime = dateFormat.parse(endTimeInput);
            if (isValidBookingTime(startTime, endTime)) {
                Conference conference = conferenceService.getConferenceById(conferenceId);
                if (conference != null && reservationService.bookConference(conference, startTime, endTime, currentUser.getLogin())) {
                    System.out.println("Conference room booked successfully.");
                } else {
                    System.out.println("Failed to book the conference room.");
                }
            } else {
                System.out.println("Invalid booking time.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    /**
     * Books a workplace based on user input.
     *
     * @param scanner   Scanner object to read user input.
     * @param dateFormat SimpleDateFormat object for date parsing.
     */
    private static void bookWorkplace(Scanner scanner, SimpleDateFormat dateFormat) {
        scanner.nextLine(); // Consume the newline left by nextInt
        System.out.print("Enter workplace ID to book: ");
        int workplaceId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt
        System.out.print("Enter booking start time (dd-MM-yyyy HH:mm): ");
        String startTimeInput = scanner.nextLine();
        System.out.print("Enter booking end time (dd-MM-yyyy HH:mm): ");
        String endTimeInput = scanner.nextLine();
        try {
            Date startTime = dateFormat.parse(startTimeInput);
            Date endTime = dateFormat.parse(endTimeInput);
            if (isValidBookingTime(startTime, endTime)) {
                Workplace workplace = workplaceService.getWorkplaceById(workplaceId);
                if (workplace != null && reservationService.bookWorkplace(workplace, startTime, endTime, currentUser)) {
                    System.out.println("Workplace booked successfully.");
                } else {
                    System.out.println("Failed to book the workplace.");
                }
            } else {
                System.out.println("Invalid booking time.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    /**
     * Displays the submenu for viewing reserved conferences and workplaces.
     *
     * @param scanner   Scanner object to read user input.
     * @param dateFormat SimpleDateFormat object for date parsing.
     */
    private static void viewReservedSubMenu(Scanner scanner, SimpleDateFormat dateFormat) {
        while (true) {
            System.out.println("1. Filter by date");
            System.out.println("2. Filter by user");
            System.out.println("3. Filter by resource");
            System.out.println("0. Back to previous menu");
            System.out.print("Enter command: ");

            try {
                int command = scanner.nextInt();
                if (command == 0) {
                    break;
                }
                processViewReservedSubCommand(command, scanner, dateFormat);
            } catch (InputMismatchException e) {
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /**
     * Processes commands from the submenu for viewing reserved conferences and workplaces.
     *
     * @param command   The command entered by the user.
     * @param scanner   Scanner object to read user input.
     * @param dateFormat SimpleDateFormat object for date parsing.
     */
    private static void processViewReservedSubCommand(int command, Scanner scanner, SimpleDateFormat dateFormat) {
        switch (command) {
            case 1:
                // Filter by date
                filterByDate(scanner, dateFormat);
                break;
            case 2:
                // Filter by user
                filterByUser(scanner);
                break;
            case 3:
                // Filter by resource
                filterByResource(scanner);
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    /**
     * Filters reserved conferences and workplaces by date.
     *
     * @param scanner   Scanner object to read user input.
     * @param dateFormat SimpleDateFormat object for date parsing.
     */
    private static void filterByDate(Scanner scanner, SimpleDateFormat dateFormat) {
        scanner.nextLine(); // Consume the newline left by nextInt
        System.out.print("Enter date (dd-MM-yyyy): ");
        String dateInput = scanner.nextLine();
        try {
            Date date = dateFormat.parse(dateInput);
            System.out.println("Reserved Conferences:");
            reservationService.printConferenceDetails(reservationService.getBookedConferencesOnDate(date));
            System.out.println("Reserved Workplaces:");
            reservationService.printWorkplaceDetails(reservationService.getBookedWorkplacesOnDate(date));
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    /**
     * Filters reserved conferences and workplaces by user.
     *
     * @param scanner Scanner object to read user input.
     */
    private static void filterByUser(Scanner scanner) {
        scanner.nextLine(); // Consume the newline left by nextInt
        System.out.print("Enter user login: ");
        String userLogin = scanner.nextLine();
        User user = userService.getUserByLogin(userLogin);
        if (user != null) {
            System.out.println("Reserved Conferences:");
            List<Conference> userConferences = reservationService.getAllConference().stream()
                    .filter(c -> c.getConferenceReservationUsername().equals(userLogin))
                    .collect(Collectors.toList());
            reservationService.printConferenceDetails(userConferences);
            System.out.println("Reserved Workplaces:");
            List<Workplace> userWorkplaces = reservationService.getAllWorkplace().stream()
                    .filter(w -> w.getWorkplaceUser().equals(user))
                    .collect(Collectors.toList());
            reservationService.printWorkplaceDetails(userWorkplaces);
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Filters reserved conferences and workplaces by resource type.
     *
     * @param scanner Scanner object to read user input.
     */
    private static void filterByResource(Scanner scanner) {
        scanner.nextLine(); // Consume the newline left by nextInt
        System.out.println("1. Conferences");
        System.out.println("2. Workplaces");
        int resourceCommand = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt
        if (resourceCommand == 1) {
            System.out.println("Reserved Conferences:");
            reservationService.printConferenceDetails(reservationService.getAllConference());
        } else if (resourceCommand == 2) {
            System.out.println("Reserved Workplaces:");
            reservationService.printWorkplaceDetails(reservationService.getAllWorkplace());
        } else {
            System.out.println("Invalid command.");
        }
    }

    /**
     * Checks if the booking time is valid.
     *
     * @param startTime The start time of the booking.
     * @param endTime   The end time of the booking.
     * @return True if the booking time is valid, false otherwise.
     */
    private static boolean isValidBookingTime(Date startTime, Date endTime) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1); // minimum booking time is the next day
        Date minBookingTime = cal.getTime();

        cal.add(Calendar.YEAR, 1); // maximum booking time is one year from now
        Date maxBookingTime = cal.getTime();

        return startTime.after(minBookingTime) && endTime.before(maxBookingTime) && endTime.after(startTime);
    }
}
