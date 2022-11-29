import java.util.Map;
import java.util.Scanner;

public class EmployeeMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void loginAsEmployee() {
        Map<String, Employee> employeeMap = Database.load();
        Scanner keyboard = new Scanner(System.in);
        String username, password;
        System.out.println("\nEnter your username: ");
        username = keyboard.nextLine().trim();
        if (!employeeMap.containsKey(username)) {
            System.out.println("That username does not exist! Try again.");
            loginAsEmployee();
        }
        System.out.println("Enter your password: ");
        password = keyboard.nextLine().trim();
        Employee employee = employeeMap.get(username);
        if ((employee.password).equals(password) && employee.validateUser.equalsIgnoreCase("employee")) {
            System.out.println("Successfully logged in.");
            System.out.println("Welcome, " + employee.validateUser + ": " + employee.firstName + " " + employee.lastName);
            viewEmployeeMenu(username);
        } else
            System.out.println("Incorrect password. Try again.");
        loginAsEmployee();
    }


    private static void viewEmployeeMenu(String username) {
        System.out.println("""
                \nWhat do you want to do?\s
                \t1. Create a Protocol;\s
                \t2. View Clients List;\s
                \t3. Return to login menu;\s
                \t4. Exit.""");
        enterEmployeeChoice(username);
    }


    private static void enterEmployeeChoice(String username) {
        System.out.print("Choose an option from 1 to 4: ");
        String choice = scanner.next().trim();
        switch (choice) {
            case "1" -> {
                Employee.createClientProtocol(username);
                viewEmployeeMenu(username);
            }
            case "2" -> {
                User.viewClientsList();
                viewEmployeeMenu(username);
            }
            case "3" -> LoginMenu.seeLoginMenu();
            case "4" -> LoginMenu.loggingOut();
            default -> {
                System.out.println("Invalid choice. Try again!");
                enterEmployeeChoice(username);
            }
        }
    }

}
