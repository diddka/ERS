import java.util.Scanner;

public class AdminMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void loginAsAdmin() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();
        if (name.equals("admin") && password.equals("admin")) {
            System.out.println("Welcome, user " + name + "!");
            viewAdminMenu();
        } else {
            System.out.println("Incorrect Username or Password! Try again.");
            LoginMenu.seeMainMenu();
        }
    }
    public static void viewAdminMenu() {
        System.out.println("""
                What do you want to do?\s
                \t1. Add new Client; \s
                \t2. Register new employees; \s
                \t3. Show employees statistics; \s
                \t4. VIEW CLIENTS LIST;\s
                \t5. VIEW EMPLOYEES LIST;\s
                \t6. Return to main menu;
                \t7. Exit.""");
        enterAdminChoice();
    }

    public static void enterAdminChoice() {
        System.out.print("Choose an option from 1 to 7: ");
        String choice = scanner.next().trim();
        switch (choice) {
            case "1" -> {
                Admin.addClient();
                enterAdminChoice();
            }
            case "2" -> {
                Admin.registerNewEmployee();
                enterAdminChoice();
            }
            case "3" -> {
                viewAdminStatisticMenu();
                enterAdminChoice();
            }
            case "4" -> {
                User.viewClientsList();
                enterAdminChoice();
            }
            case "5" -> {
                Admin.viewEmployeesList();
                enterAdminChoice();
            }
            case "6" -> LoginMenu.seeMainMenu();
            case "7" -> {
                System.out.println("Logging out... See you next time!");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid input. Try again!");
                enterAdminChoice();
            }
        }
    }

    public static void showEmployeeStatistics() {
        System.out.print("Select an option to show an employee statistics: ");
        String choice = scanner.next().trim();
        switch (choice) {
            case "1" -> {
                Admin.searchEmployeeByName();
                viewAdminStatisticMenu();
            }
            case "2" -> {
                Admin.searchProtocolByWeek();
                viewAdminStatisticMenu();
            }
            case "3" -> {
                Admin.searchByEverythingInFile();
                viewAdminStatisticMenu();
            }
            case "4" ->
                    LoginMenu.seeMainMenu();
            default -> {
                System.out.println("Invalid input. Try again!");
                showEmployeeStatistics();
            }
        }
    }

    public static void viewAdminStatisticMenu() {
        System.out.println("""
                What employees statistics would you like to displayed? \s
                \t1. Search by employee first name; \s
                \t2. Search by week number; \s
                \t3. Search by everything in Protocol file;
                \t4. Return to main menu.""");
        showEmployeeStatistics();
    }


}