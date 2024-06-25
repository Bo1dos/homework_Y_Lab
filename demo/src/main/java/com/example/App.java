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

public class App {
    private static UserService userService;
    private static WorkplaceService workplaceService;
    private static ConferenceService conferenceService;
    private static ReservationService reservationService;

    

    private static User currentUser;


    public static void main( String[] args ) {
        userService = new UserService();
        workplaceService = new WorkplaceService();
        conferenceService = new ConferenceService();
        reservationService = new ReservationService();


        userService.addUser("123", "123", "normal");
        userService.addUser("456", "qqq", "normal");
        conferenceService.addConference("Conf1", null, null, null);
        conferenceService.addConference("Conf2", null, null, null);
        workplaceService.addWorkplace(null, null, null);
        workplaceService.addWorkplace(null, null, null);
        
        
        mainMenu();
    }

    public static void mainMenu(){
        String mainMenuString = "1. Login \n"+
                          "2. Registration \n"+
                          "3. Exit \n" +
                          "Enter command: ";

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print(mainMenuString);

            try {
                int enteredCommand = scanner.nextInt();

                if(enteredCommand == 3){
                    break;
                }

                processMainCommand(enteredCommand);
            } catch (InputMismatchException e) {
                scanner.next();
            }
            
            
        }
        

    }

    private static void userMenu(){
        Scanner scanner = new Scanner(System.in);
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
                    userService.updateUser(currentUser.getId(), currentUser.getLogin(), currentUser.getPass(), currentUser.getRole());
                    break;
                }

                processUserCommand(command);
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }

    }

    public static void processUserCommand(int command) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        switch (command) {
            case 1:
                System.out.println("All Conferences:");
                conferenceService.printConferenceDetails(conferenceService.getAllConference());
                System.out.println("All Workplaces:");
                workplaceService.printWorkplaceDetails(workplaceService.getAllWorkplace());
                viewAllSubMenu();
                break;
            case 2:
                System.out.println("All Reserved Conferences:");
                reservationService.printConferenceDetails(reservationService.getAllConference());
                System.out.println("All Reserved Workplaces:");
                reservationService.printWorkplaceDetails(reservationService.getAllWorkplace());
                viewReservedSubMenu();
                break;
            case 3:
                System.out.println("My Conferences:");
                List<Conference> myConferences = reservationService.getAllConference().stream()
                        .filter(c -> c.getConferenceReservationUsername().equals(currentUser.getLogin()))
                        .collect(Collectors.toList());
                reservationService.printConferenceDetails(myConferences);
                viewMyConferencesSubMenu();
                break;
            case 4:
                System.out.println("My Workplaces:");
                List<Workplace> myWorkplaces = reservationService.getAllWorkplace().stream()
                        .filter(w -> w.getWorkplaceUser().equals(currentUser))
                        .collect(Collectors.toList());
                reservationService.printWorkplaceDetails(myWorkplaces);
                viewMyWorkplacesSubMenu();
                break;
            case 5:
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
                break;
            case 6:
                System.out.print("Enter conference title: ");
                String title = scanner.nextLine();
                conferenceService.addConference(title, null, null, currentUser.getLogin());
                break;
            case 7:
                System.out.print("Enter conference ID to edit: ");
                int conferenceId = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                System.out.print("Enter new conference title: ");
                String newTitle = scanner.nextLine();
                conferenceService.updateConference(conferenceId, newTitle, null, null, null);
               
                break;
            case 8:
                workplaceService.addWorkplace(null, null, currentUser);
                break;
            
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    private static void viewAllSubMenu() {
        Scanner scanner = new Scanner(System.in);
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
                processViewAllSubCommand(command);
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }
    }

    private static void processViewAllSubCommand(int command) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        switch (command) {
            case 1:
                System.out.print("Enter conference ID to book: ");
                int conferenceId = scanner.nextInt();
                scanner.nextLine(); // consume the newline
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
                break;
            case 2:
                System.out.print("Enter workplace ID to book: ");
                int workplaceId = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                System.out.print("Enter booking start time (dd-MM-yyyy HH:mm): ");
                String workplaceStartTimeInput = scanner.nextLine();
                System.out.print("Enter booking end time (dd-MM-yyyy HH:mm): ");
                String workplaceEndTimeInput = scanner.nextLine();
                try {
                    Date workplaceStartTime = dateFormat.parse(workplaceStartTimeInput);
                    Date workplaceEndTime = dateFormat.parse(workplaceEndTimeInput);
                    if (isValidBookingTime(workplaceStartTime, workplaceEndTime)) {
                        Workplace workplace = workplaceService.getWorkplaceById(workplaceId);
                        if (workplace != null && reservationService.bookWorkplace(workplace, workplaceStartTime, workplaceEndTime, currentUser)) {
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
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    

    private static void viewReservedSubMenu() {
        Scanner scanner = new Scanner(System.in);
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
                processViewReservedSubCommand(command);
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }
    }

    private static void processViewReservedSubCommand(int command) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        switch (command) {
            case 1:
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
                break;
            case 2:
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
                break;
            case 3:
                System.out.println("1. Conferences");
                System.out.println("2. Workplaces");
                int resourceCommand = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                if (resourceCommand == 1) {
                    System.out.println("Reserved Conferences:");
                    reservationService.printConferenceDetails(reservationService.getAllConference());
                } else if (resourceCommand == 2) {
                    System.out.println("Reserved Workplaces:");
                    reservationService.printWorkplaceDetails(reservationService.getAllWorkplace());
                } else {
                    System.out.println("Invalid command.");
                }
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    private static void viewMyConferencesSubMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Cancel a conference reservation");
            System.out.println("0. Back to previous menu");
            System.out.print("Enter command: ");
    
            try {
                int command = scanner.nextInt();
                if (command == 0) {
                    break;
                }
                processCancelConferenceReservation(command);
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }
    }

    private static void processCancelConferenceReservation(int command) {
        Scanner scanner = new Scanner(System.in);
        switch (command) {
            case 1:
                System.out.print("Enter conference ID to cancel: ");
                int conferenceId = scanner.nextInt();
                if (reservationService.cancelConferenceReservation(conferenceId)) {
                    System.out.println("Conference reservation cancelled successfully.");
                } else {
                    System.out.println("Failed to cancel conference reservation.");
                }
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }
    
    private static void viewMyWorkplacesSubMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Cancel a workplace reservation");
            System.out.println("0. Back to previous menu");
            System.out.print("Enter command: ");
    
            try {
                int command = scanner.nextInt();
                if (command == 0) {
                    break;
                }
                processCancelWorkplaceReservation(command);
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }
    }
    
    private static void processCancelWorkplaceReservation(int command) {
        Scanner scanner = new Scanner(System.in);
        switch (command) {
            case 1:
                System.out.print("Enter workplace ID to cancel: ");
                int workplaceId = scanner.nextInt();
                if (reservationService.cancelWorkplaceReservation(workplaceId)) {
                    System.out.println("Workplace reservation cancelled successfully.");
                } else {
                    System.out.println("Failed to cancel workplace reservation.");
                }
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

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

    public static void processMainCommand(int enteredCommand){
        switch (enteredCommand) {
            case 1:{
                Login login = new Login(userService);

                if(login.getLoginForm()){
                    currentUser = login.getCurrentUser();
                    userMenu();
                }
                    
                
                break;
            }
            case 2:{
                Registration registration = new Registration(userService);
                registration.getRegistrationForm();
                break;
            }
  
        
            default:
                break;
        }
    }


    public static UserService getUserService() {
        return userService;
    }

    public static void setUserService(UserService userService) {
        App.userService = userService;
    }

    public static WorkplaceService getWorkplaceService() {
        return workplaceService;
    }

    public static void setWorkplaceService(WorkplaceService workplaceService) {
        App.workplaceService = workplaceService;
    }

    public static ConferenceService getConferenceService() {
        return conferenceService;
    }

    public static void setConferenceService(ConferenceService conferenceService) {
        App.conferenceService = conferenceService;
    }

    public static ReservationService getReservationService() {
        return reservationService;
    }

    public static void setReservationService(ReservationService reservationService) {
        App.reservationService = reservationService;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
}
