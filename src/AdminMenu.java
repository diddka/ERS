import java.util.Map;
import java.util.Scanner;

public class AdminMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void loginAsAdmin() {
        Map<String, Employee> employeeMap = Database.load();
        Scanner keyboard = new Scanner(System.in);
        String username, password;
        System.out.println("Enter username: ");
        username = keyboard.nextLine().trim();
        if (!employeeMap.containsKey(username)) {
            System.out.println("That username does not exist! Try again.");
            loginAsAdmin();
        }
        System.out.println("Enter password: ");
        password = keyboard.nextLine().trim();
        Employee employee = employeeMap.get(username);
        if ((employee.password).equals(password) && employee.validateUser.equals("admin")) {
            System.out.println("Successfully logged in.");
            System.out.println("Welcome, " + employee.validateUser + ": " + employee.firstName + " " + employee.lastName);
            viewAdminMenu();
        } else
            System.out.println("Incorrect password. Try again.");
        loginAsAdmin();

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
        System.out.print("\nChoose an option from 1 to 7: ");
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
            case "4" -> LoginMenu.seeMainMenu();
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