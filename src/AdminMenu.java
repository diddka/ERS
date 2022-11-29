import java.util.Map;
import java.util.Scanner;

public class AdminMenu {
    static Scanner scanner = new Scanner(System.in);

    protected static void loginAsAdmin() {
        Map<String, Employee> employeeMap = Database.load();
        Scanner keyboard = new Scanner(System.in);
        String username, password;
        System.out.println("\nEnter your username: ");
        username = keyboard.nextLine().trim();
        if (!employeeMap.containsKey(username)) {
            System.out.println("That username does not exist! Try again.");
            loginAsAdmin();
        }
        System.out.println("Enter your password: ");
        password = keyboard.nextLine().trim();
        Employee employee = employeeMap.get(username);
        if ((employee.password).equals(password) && employee.validateUser.equalsIgnoreCase("admin")) {
            System.out.println("Successfully logged in.");
            System.out.println("Welcome, " + employee.validateUser + ": " + employee.firstName + " " + employee.lastName);
            viewAdminMenu();
        } else System.out.println("Incorrect password. Try again.");
        loginAsAdmin();

    }

    protected static void viewAdminMenu() {
        System.out.println("""
                \nWhat do you want to do?\s
                \t1. Add new Client; \s
                \t2. Register new employees; \s
                \t3. Show employees statistics; \s
                \t4. VIEW CLIENTS LIST;\s
                \t5. VIEW EMPLOYEES LIST;\s
                \t6. Return to login menu;
                \t7. Exit.""");
        enterAdminChoice();
    }

    private static void enterAdminChoice() {
        System.out.print("Choose an option from 1 to 7: ");
        String choice = scanner.next().trim();
        switch (choice) {
            case "1" -> {
                Admin.addClient();
                viewAdminMenu();
            }
            case "2" -> {
                Admin.registerNewEmployee();
                viewAdminMenu();
            }
            case "3" -> {
                viewAdminStatisticMenu();
                viewAdminMenu();
            }
            case "4" -> {
                User.viewClientsList();
                viewAdminMenu();
            }
            case "5" -> {
                Admin.viewEmployeesList();
                viewAdminMenu();
            }
            case "6" -> LoginMenu.seeLoginMenu();
            case "7" -> LoginMenu.loggingOut();
            default -> {
                System.out.println("Invalid input. Try again!");
                enterAdminChoice();
            }
        }
    }

    protected static void showEmployeeStatistics() {
        System.out.print(" >>> Select an option to show an employee statistics: ");
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
            case "4" -> viewAdminMenu();
            case "5" -> LoginMenu.seeLoginMenu();
            case "6" -> LoginMenu.loggingOut();
            default -> {
                System.out.println("Invalid input. Try again!");
                showEmployeeStatistics();
            }
        }
    }

    protected static void viewAdminStatisticMenu() {
        System.out.println("""
                \n >>> What employees statistics would you like to displayed? <<<
                \t > 1. Search by employee name;
                \t > 2. Search by week number;
                \t > 3. Search by everything in Protocol file;
                \t > 4. Back to main menu;
                \t > 5. Return to login menu;
                \t > 6. Exit.""");
        showEmployeeStatistics();
    }

    protected static void askForANewSearch() {
        System.out.println("""
                \n ~~~ Did you want a new search by week? ~~~
                \t ~~~ 1. Yes
                \t ~~~ 2. No""");
        System.out.print("Enter your answer: ");
        String answer = scanner.next().trim();
        while (checkInputOnlyContainsNumbers(answer)) {
            switch (answer) {
                case "1" -> Admin.searchProtocolByWeek();
                case "2" -> AdminMenu.viewAdminMenu();
                default -> System.out.println("Incorrect input!");
            }
            askForANewSearch();
        }
    }

    private static boolean checkInputOnlyContainsNumbers(String input) {
        for (char symbol : input.toCharArray()) {
            if (Character.isAlphabetic(symbol)) {
                System.out.println("Invalid input! Please, enter a number for answer (1 or 2)!");
                AdminMenu.askForANewSearch();
                return false;
            }
        }
        return true;
    }

}