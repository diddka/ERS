import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Client {
    protected String clientName;
    protected String projectName;
    protected String contractExpirationDate;

    public Client(String name, String projectName, String contractExpirationDate) {
        this.clientName = name;
        this.projectName = projectName;
        this.contractExpirationDate = contractExpirationDate;
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

    public static Client input() {
        System.out.print("\nEnter a Client name: ");
        String name = Validation.checkIsValidInput();
        System.out.print("Enter a Project name: ");
        String projectName = Validation.checkIsValidInput();
        System.out.print("Enter the contract expiration date: ");
        String contractExpirationDate = setContractExpirationDate();
        return new Client(name, projectName, contractExpirationDate);
    }

    protected static String setContractExpirationDate() {
        LocalDate now = LocalDate.now();
        String currentDate = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date today = ValidationDate.takeTodayDate(currentDate, formatter);
        boolean isBefore = true;
        return ValidationDate.validContractExpirationDate(currentDate, formatter, today, isBefore);
    }

    @Override
    public String toString() {
        return "The client name is ~ " + clientName +
                ", with a project: " + projectName +
                " and contract expiration date: " +
                contractExpirationDate;
    }
}
