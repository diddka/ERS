import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.*;

public class Employee extends User {
    static Scanner scanner = new Scanner(System.in);

    public Employee(String[] parts) {
        super(parts);
    }

    protected static Employee input() {
        System.out.print("Enter an Employee first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter an Employee last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter an Employee email: ");
        String email = scanner.nextLine();
        System.out.print("Enter an Employee city: ");
        String city = scanner.nextLine();
        System.out.print("Enter an Employee username: ");
        String username = scanner.nextLine();
        System.out.print("Enter an Employee password: ");
        String password = scanner.nextLine();
        return new Employee(new String[]{firstName, lastName, email, city, username, password});
    }

    public static void createClientProtocol(String username) {
        Map<String, Employee> employeeMap = Database.load();
        Employee employee = employeeMap.get(username);

        String protocolFile = "Protocol.csv";
        try {
            WriteFile.writeEmployeeProtocol(Employee.inputProtocol(username), employee.firstName, employee.lastName);
        } catch (Exception e) {
            System.out.println(username + ", the Protocol can't be write to file " + protocolFile);
        }
    }


    public static String generateWeekOfYearFromInputDate(String date) {
        String[] values = date.split("\\.");
        int day = Integer.parseInt(values[0]);
        int month = Integer.parseInt(values[1]);
        int year = Integer.parseInt(values[2]);
        LocalDate date1 = LocalDate.of(year, month, day);
        int weekOfYear = date1.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        return String.valueOf(weekOfYear);
    }

    public static Protocol inputProtocol(String username) {
        System.out.print("Enter date : ");
        String data = scanner.nextLine();
        System.out.print("Enter how many hours you have work for this client? : ");
        String hours = scanner.next();

        return new Protocol(generateWeekOfYearFromInputDate(data), data, selectClientFromList(username), hours);
    }

    public static String selectClientFromList(String username) {
        User.viewClientsList();
        System.out.println("Let's select a client, to do this enter a number of client: ");
        int number = scanner.nextInt();
        String employeeChoice = ReadFile.readClientFile().get(number - 1).toString();
        System.out.println(employeeChoice);
        System.out.println(username + ", your protocol is created!!!");
        return employeeChoice;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "first_name = '" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + city + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                "} ";
    }
}
