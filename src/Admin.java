import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    static Scanner scanner = new Scanner(System.in);
    static List<Employee> employeeList;
    static List<Protocol> protocolList;

    public Admin(String[] parts) {
        super(parts);
    }


    public static void addClient() {
        String clientsFile = "Clients.csv";
        try {
            WriteFile.writeNewClients(Client.input());
            System.out.println("The client is added!");
        } catch (Exception e) {
            System.out.println("Can't write to file " + clientsFile + " " + e.getMessage());
        }
    }

    public static void registerNewEmployee() {
        String employeesFile = "Employees.csv";
        try {
            WriteFile.writeNewEmployees(Employee.input());
        } catch (Exception e) {
            System.out.println("Can't write to file " + employeesFile + " " + e.getMessage());
        }
    }

    public static void viewEmployeesList() {
        employeeList = ReadFile.readEmployeeFile();
        if (!employeeList.isEmpty()) {
            System.out.println("Employees list: ");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        } else {
            System.out.println("Employees list is empty!");
        }
    }

    public static void searchEmployeeByName() {
        System.out.println("Enter the employee name for search: ");
        protocolList = ReadFile.readProtocolFile();
        String search = scanner.nextLine().trim();
        checkSearch(search);
    }

    private static void checkSearch(String search) {
        if (!search.isEmpty()) {
            printTheProtocolOfTheFindedEmployee(search);
        } else {
            System.out.println("The search cannot be empty!");
        }
    }

    private static void printTheProtocolOfTheFindedEmployee(String search) {
        if (checkInputIsDigit(search)) {
            for (Protocol protocol : protocolList) {
                if (search.equals(protocol.employeeName)) {
                    System.out.println(protocol);
                } else if (checkSearchExistEmployee(search)) {
                    System.out.println("The employee doesn't exist!");
                    break;
                }
            }
        }
        AdminMenu.viewAdminStatisticMenu();
    }

    private static boolean checkInputIsDigit(String input) {
        for (char symbol : input.toCharArray()) {
            if (Character.isAlphabetic(symbol)) {
                return true;
            } else {
                System.out.println("Invalid input!!! \nThe search cannot be integer! Please, ENTER an employee name!!!");
                Admin.searchEmployeeByName();
            }
        }
        return false;
    }


    private static boolean checkSearchExistEmployee(String search) {
        for (Protocol protocol : protocolList) {
            if (search.equals(protocol.employeeName)) {
                return false;
            }
        }
        return true;
    }


    public static void searchProtocolByWeek() {
        protocolList = ReadFile.readProtocolFile();
        System.out.println("Enter a number of week to search: ");
    }

    public static void searchByEverythingInFile() {
        System.out.println("Enter that you want to search: ");
        String search = scanner.nextLine().trim();
        try {
            FileSearch.parseFile("Protocol.csv", search);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}

