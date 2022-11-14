import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Client {

    static Scanner scanner = new Scanner(System.in);
    protected String clientName;
    protected String projectName;
    protected String contractExpirationDate;

    public Client(String name, String projectName, String contractExpirationDate) {
        this.clientName = name;
        this.projectName = projectName;
        this.contractExpirationDate = contractExpirationDate;
    }

    private static String setContractExpirationDate() {
        LocalDate now = LocalDate.now();
        String currentDate = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String contractExpirationDate = null;
        Date today = getTodayDate(currentDate, formatter);
        Date expirationDate;
        boolean isBefore = true;
        try {
            while (isBefore) {
                contractExpirationDate = scanner.nextLine().trim();
                expirationDate = formatter.parse(contractExpirationDate);
                if (checkDate(currentDate, contractExpirationDate, today, expirationDate)) continue;
                isBefore = false;
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            // throw new RuntimeException(e);
        }
        return contractExpirationDate;
    }

    private static boolean checkDate(String currentDate, String contractExpirationDate, Date today, Date expirationDate) {
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

    private static Date getTodayDate(String currentDate, SimpleDateFormat formatter) {
        Date today;
        try {
            today = formatter.parse(currentDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return today;
    }

    public String getClientName() {
        return clientName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getContractExpirationDate() {
        return contractExpirationDate;
    }

    protected static Client input() {
        System.out.print("Enter a Client name: ");
        String name = setClientName();
        System.out.print("Enter a Project name: ");
        String projectName = scanner.nextLine().trim();
        System.out.print("Enter the contract expiration date: ");
        String contractExpirationDate = setContractExpirationDate();
        return new Client(name, projectName, contractExpirationDate);
    }

    private static String setClientName() {
        String name = null;
        boolean isValidName = true;
        while (isValidName) {
            name = scanner.nextLine().trim();
            if (name.trim().isEmpty()) {
                System.out.println("Client name cannot be empty! Try again!");
                continue;
            }
            isValidName = false;
        }
        return name;
    }


    @Override
    public String toString() {
        return "The client name is ~ " + clientName + ", with a project: " + projectName + " and contract expiration date: " + contractExpirationDate;
    }
}
