import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Employee extends User {
    static Scanner scanner = new Scanner(System.in);
    static List<Client> clientList;

    public Employee(String[] parts) {
        super(parts);
    }

    protected static Employee input() {
        System.out.print("Enter a validation status (admin or employee): ");
        String validateEmployee = Validation.checkUserStatus();
        System.out.print("Enter an Employee first name: ");
        String firstName = Validation.checkIsValidInput();
        System.out.print("Enter an Employee last name: ");
        String lastName = Validation.checkIsValidInput();
        System.out.print("Enter an Employee email: ");
        String email = Validation.checkIsValidInput();
        System.out.print("Enter an Employee city: ");
        String city = Validation.checkIsValidInput();
        System.out.print("Enter an Employee username: ");
        String username = Validation.checkIsValidInput();
        //System.out.print(firstName + " " + lastName + ", which is an /" + validateEmployee + "/ has the following unique password: ");
        String password = PasswordHelper.generateRandomPassword();//String.valueOf(PasswordHelper.createUniquePassword());
        System.out.print(firstName + " " + lastName + ", which is an /" + validateEmployee + "/ has the following unique password: " + password);
        return new Employee(new String[]{firstName, lastName, email, city, username, password, validateEmployee});
    }

    protected static void createClientProtocol(String username) {
        Map<String, Employee> employeeMap = Database.load();
        Employee employee = employeeMap.get(username);
        String week = generateCurrentDateAndWeekForProtocol().get(0);
        String date = generateCurrentDateAndWeekForProtocol().get(1);
        String hours = inputHoursOfWork();
        String selectedClient = selectClientFromList(username);
        String protocolFile = "Protocol.csv";
        try {
            WriteFile.writeEmployeeProtocol(new Protocol(week, date, selectedClient, hours), employee.firstName, employee.lastName);
        } catch (Exception e) {
            System.out.println(username + ", the Protocol can't be write to file " + protocolFile + " " + e.getMessage());
        }
    }

    private static String inputHoursOfWork() {
        System.out.print("Enter how many hours you have work for this client? : ");
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
                        System.out.println("Invalid input.\n" + "The value of hours must be bigger than zero and less than eight hours. Try again.");
                        continue;
                    }
                } else {
                    scanner.next();
                    System.out.println("Incorrect input.\n" + "The value of hours must be an integer. Try again.");
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

    private static String selectClientFromList(String username) {
        clientList = ReadFile.readClientFile();
        User.viewClientsList();
        System.out.println(username + ", let's select a client, to do this enter a number of client: ");
        int number = checkNumberOfClient();
        String employeeChoice = clientList.get(number - 1).toString();
        System.out.println(employeeChoice);
        System.out.println(username + ", your protocol is created!!!");
        return employeeChoice;
    }

    private static int checkNumberOfClient() {
        boolean isValidInput = true;
        int number = 0;
        try {
            while (isValidInput) {
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    if (number > clientList.size() || number <= 0) {
                        System.out.println("Invalid value.\n" + "The list contains " + clientList.size() + " client. Please, try again.");
                        continue;
                    }
                } else {
                    scanner.next();
                    System.out.println("Incorrect input.\n" + "The list contains " + clientList.size() + " client. Please, try again.");
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
                ", Password: " + "******" +
                ", Validation status: " + super.validateUser;
    }
}
