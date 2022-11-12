import java.util.Scanner;

public class LoginMenu {

    static Scanner scanner = new Scanner(System.in);

    public static void seeMainMenu() {
        System.out.println("Options available: \n\t1. Log as admin; \n\t2. Log as employee; \n\t3. Exit.");
        selectOption();
    }

    public static void selectOption() {
        System.out.print("Choose an option from 1 to 3: ");
        String choice = scanner.next();
        switch (choice) {
            case "1" -> AdminMenu.loginAsAdmin();
            case "2" -> {
                try {
                    EmployeeMenu.loginAsEmployee();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            case "3" -> System.out.println("Logging out... See you next time!");
            default -> {
                System.out.println("Invalid choice. Try again!");
                selectOption();
            }
        }
    }



}

