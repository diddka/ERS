import java.util.Scanner;

public class Validation {
    static Scanner scanner = new Scanner(System.in);

    protected static String checkIsValidInput() {
        String input = null;
        boolean isValidInput = true;
        while (isValidInput) {
            input = scanner.nextLine().trim();
            if (checkInputIsNumber(input)) {
                continue;
            }
            if (input.trim().isEmpty() || input.equals("")) {
                System.out.println("Input cannot be empty! Try again!");
                continue;
            }
            isValidInput = false;
        }
        return input;
    }

    private static boolean checkInputIsNumber(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                System.out.println("Input cannot be an integer! Try again!");
                return true;
            }
        }
        return false;
    }

    protected static String checkUserStatus() {
        String status = null;
        boolean validStatus = true;
        while (validStatus) {
            status = scanner.nextLine().trim();
            if (!(status.equalsIgnoreCase("admin") || status.equalsIgnoreCase("employee"))) {
                System.out.println("Please, enter a valid status:");
                continue;
            }
            validStatus = false;
        }
        return status;
    }

    protected static String checkInputIsEmpty() {
        String input = null;
        boolean isValidInput = true;
        while (isValidInput) {
            input = scanner.nextLine().trim();
            if (input.trim().isEmpty() || input.equals("")) {
                System.out.println("Input cannot be empty! Try again!");
                continue;
            }
            isValidInput = false;
        }
        return input;
    }
}
