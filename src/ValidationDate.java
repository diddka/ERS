import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidationDate {

    static Scanner scanner = new Scanner(System.in);

    protected static boolean isEmptyDate(String contractExpirationDate) {
        return contractExpirationDate.isEmpty() || contractExpirationDate.trim().equals("");
    }
    static boolean isValidDate(String contractExpirationDate, SimpleDateFormat formatter) {
        formatter.setLenient(false);
        try {
            getParse(contractExpirationDate.trim(), formatter);
        } catch (ParseException e) {
            System.out.print("It's not a date! ");
            return false;
        }
        return true;
    }

    protected static String validContractExpirationDate(String currentDate, SimpleDateFormat formatter, Date today, boolean isBefore) {
        String contractExpirationDate = null;
        while (isBefore) {
            contractExpirationDate = scanner.nextLine().trim();
            if (ValidationDate.isEmptyDate(contractExpirationDate)) {
                System.out.println("The date cannot be empty! Try again");
            } else if (!ValidationDate.isValidDate(contractExpirationDate, formatter)) {
                System.out.println("Please enter a valid date! Try again!");
            } else {
                if (ValidationDate.checkDate(currentDate, contractExpirationDate, today, formatter)) {
                    continue;
                }
                isBefore = false;
            }
        }
        return contractExpirationDate;
    }

    protected static Date takeTodayDate(String date, SimpleDateFormat formatter) {
        Date today;
        try {
            today = getParse(date, formatter);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        return today;
    }

    protected static boolean checkDate(String currentDate, String contractExpirationDate, Date today, SimpleDateFormat formatter) {
        Date expirationDate = takeTodayDate(contractExpirationDate, formatter);
        boolean isEquals;
        boolean isAfter;
        isEquals = today.equals(expirationDate);
        isAfter = today.after(expirationDate);
        if (isAfter) {
            System.out.println("Please, enter a correct date, because today date: " + currentDate + " is after " + contractExpirationDate + " Try again.");
            return true;
        } else if (isEquals) {
            System.out.println("Please, enter a correct date, because today date: " + currentDate + " is equals to " + contractExpirationDate + " Try again.");
            return true;
        }
        return false;
    }
    protected static Date getParse(String currentDate, SimpleDateFormat formatter) throws ParseException {
        return formatter.parse(currentDate);
    }


}
