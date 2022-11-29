import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Employee extends User {
    static Scanner scanner = new Scanner(System.in);

    public Employee(String[] parts) {
        super(parts);
    }

    protected static Employee input() {
        System.out.print("\nEnter a validation status (admin or employee): ");
        String validateEmployee = Validation.checkUserStatus();
        System.out.print("Enter an Employee first name: ");
        String firstName = Validation.checkIsValidInput();
        System.out.print("Enter an Employee last name: ");
        String lastName = Validation.checkIsValidInput();
        System.out.print("Enter an Employee email: ");
        String email = Validation.checkInputIsEmpty();
        System.out.print("Enter an Employee city: ");
        String city = Validation.checkIsValidInput();
        System.out.print("Enter an Employee username: ");
        String username = Validation.checkIsValidInput();
        //System.out.print(firstName + " " + lastName + ", which is an: " + validateEmployee + " has the following unique password: ");
        String password = PasswordHelper.generateRandomPassword();//String.valueOf(PasswordHelper.createUniquePassword());
        System.out.print(firstName + " " + lastName + ", which is an: " + validateEmployee + " has the following unique password: " + password);
        return new Employee(new String[]{firstName, lastName, email, city, username, password, validateEmployee});
    }

    protected static void createClientProtocol(String username) {
        Map<String, Employee> employeeMap = Database.load();
        Employee employee = employeeMap.get(username);
        String week = generateCurrentDateAndWeekForProtocol().get(0);
        String date = generateCurrentDateAndWeekForProtocol().get(1);
        String hours = inputHoursOfWork();
        String selectedClient = selectActualClientFromList(username);
        String protocolFile = "Protocol.csv";
        try {
            WriteFile.writeEmployeeProtocol(new Protocol(week, date, selectedClient, hours), employee.firstName, employee.lastName);
        } catch (Exception e) {
            System.out.println("\n" + username + ", the Protocol can't be write to file " + protocolFile + " " + e.getMessage());
        }
    }

    private static String inputHoursOfWork() {
        System.out.print("\nEnter how many hours you have work for this client? : ");
        int hours = checkHoursOfWork();
        return String.valueOf(hours);
    }

    private static int checkHoursOfWork() {
        boolean isValidInput = true;
        int hours = 0;
        try {
            while (isValidInput) {
                if (scanner.hasNextInt()) {
                    hours = scanner.nextInt();
                    if (hours <= 0 || hours > 8) {
                        System.out.println("Invalid input!!!\n" + "The value of hours must be bigger than zero and less than eight hours! Try again!");
                        continue;
                    }
                } else {
                    scanner.next();
                    System.out.println("Incorrect input!!!\n" + "The value of hours must be an integer! Try again!");
                    continue;
                }
                isValidInput = false;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return hours;
    }

    private static List<String> generateCurrentDateAndWeekForProtocol() {
        List<String> weekAndDate = new ArrayList<>();
        DateTimeFormatter week = DateTimeFormatter.ofPattern("ww");
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy,HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        weekAndDate.add(week.format(now));
        weekAndDate.add(date.format(now));
        return weekAndDate;
    }

    private static String selectActualClientFromList(String username) {
        clients = ReadFile.readClientFile();
        List<Client> actualClientsList = new ArrayList<>();
        checkDateIsBeforeContractExpirationDate(actualClientsList);
        viewActualClientsList(actualClientsList);
        System.out.print("\n" + username + ", let's select a client, to do this enter a number of client: ");
        int number = checkNumberOfClient(actualClientsList);
        String choice = actualClientsList.get(number - 1).toString();
        System.out.println(choice);
        System.out.println("\n" + username + ", your protocol is created!!!");
        return choice;
    }

    private static void checkDateIsBeforeContractExpirationDate(List<Client> actualClientsList) {
        LocalDate now = LocalDate.now();
        String currentDate = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date today = ValidationDate.takeTodayDate(currentDate, formatter);
        Date expirationDate;
        boolean isEquals;
        boolean isBefore;
        for (Client client : clients) {
            try {
                expirationDate = formatter.parse(client.getContractExpirationDate());
            } catch (ParseException e) {
                System.out.println("\nReally bad! The exception message is: " + e.getMessage());
                throw new RuntimeException(e);
            }
            isEquals = today.equals(expirationDate);
            isBefore = today.before(expirationDate);
            if (isEquals || isBefore) {
                actualClientsList.add(client);
            }
        }
    }

    private static void viewActualClientsList(List<Client> actualClientsList) {
        int numeric = 1;
        System.out.println("\nThe actual Clients list is: ");
        for (Client actualClient : actualClientsList) {
            System.out.println(numeric + ". " + actualClient);
            numeric++;
        }
    }

    private static int checkNumberOfClient(List<Client> actualClientsList) {
        boolean isValidInput = true;
        int number = 0;
        try {
            while (isValidInput) {
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    if (number > actualClientsList.size() || number <= 0) {
                        System.out.println("Invalid value.\n" + "The list contains " + actualClientsList.size() + " clients. Please, try again.");
                        continue;
                    }
                } else {
                    scanner.next();
                    System.out.println("Incorrect input.\n" + "The list contains " + actualClientsList.size() + " clients. Please, try again.");
                    continue;
                }
                isValidInput = false;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return number;
    }

    @Override
    public String toString() {
        return "Name: " + super.firstName + " " + super.lastName +
                ", Email: " + super.email +
                ", Country: " + super.city +
                ", Username: " + super.username +
                ", Password: " + "******";
        // ", Validation status: " + super.validateUser;
    }
}
