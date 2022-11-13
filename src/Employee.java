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
        String week = generateCurrentDateAndWeekForProtocol().get(0);
        String date = generateCurrentDateAndWeekForProtocol().get(1);
        String hours = inputHoursOfWork();
        String selectedClient = selectClientFromList(username);
        String protocolFile = "Protocol.csv";
        try {
           WriteFile.writeEmployeeProtocol(new Protocol(week,date,selectedClient,hours), employee.firstName, employee.lastName);
        } catch (Exception e) {
            System.out.println(username + ", the Protocol can't be write to file " + protocolFile);
        }
    }

    private static String inputHoursOfWork() {
        System.out.print("Enter how many hours you have work for this client? : ");
        String hours = scanner.next();
        return hours;
    }
    private static List<String> generateCurrentDateAndWeekForProtocol(){
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
        System.out.println("Let's select a client, to do this enter a number of client: ");
        int number = scanner.nextInt();
        String employeeChoice = clientList.get(number - 1).toString();
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
