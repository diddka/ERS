import java.util.Scanner;

public class LoginMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void seeLoginMenu() {
        System.out.println("\nOptions available: \n\t1. Log as admin; \n\t2. Log as employee; \n\t3. Exit.");
        selectOption();
    }

    public static void selectOption() {
        System.out.print("Choose an option from 1 to 3: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> AdminMenu.loginAsAdmin();
            case "2" -> EmployeeMenu.loginAsEmployee();
            case "3" -> loggingOut();
            default -> {
                System.out.println("Invalid choice. Try again!");
                selectOption();
            }
        }
    }

    public static void loggingOut() {
        System.out.println("\nLogging out... See you next time!");
        System.exit(0);
    }

}





