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

    protected static void addClient() {
        String clientsFile = "Clients.csv";
        try {
            WriteFile.writeNewClients(Client.input());
            System.out.println("\nThe client is added!");
        } catch (Exception e) {
            System.out.println("Can't write to file " + clientsFile + " " + e.getMessage());
        }
    }

    protected static void registerNewEmployee() {
        String employeesFile = "Employees.csv";
        try {
            WriteFile.writeNewEmployees(Employee.input());
            System.out.println("\nThe employee is added!");
        } catch (Exception e) {
            System.out.println("Can't write to file " + employeesFile + " " + e.getMessage());
        }
    }

    protected static void viewEmployeesList() {
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

    protected static void searchEmployeeByName() {
        System.out.println("Enter the name of the search employee: ");
        protocolList = ReadFile.readProtocolFile();
        String search = scanner.nextLine().trim();
        checkSearch(search);
    }

    private static void checkSearch(String search) {
        if (!search.isEmpty()) {
            Admin.printTheProtocolOfTheFindedEmployee(search);
        } else {
            System.out.println("The search cannot be empty!");
        }
    }

    private static void printTheProtocolOfTheFindedEmployee(String search) {
        if (checkInputIsString(search)) {
            for (Protocol protocol : protocolList) {
                if (search.equals(protocol.getEmployeeName())) {
                    System.out.println(protocol);
                } else if (checkSearchExistEmployee(search)) {
                    System.out.println("The employee doesn't exist!");
                    break;
                }
            }
        }
        AdminMenu.viewAdminStatisticMenu();
    }

    private static boolean checkInputIsString(String input) {
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
        for (Protocol protocol : Admin.protocolList) {
            if (search.equals(protocol.getEmployeeName())) {
                return false;
            }
        }
        return true;
    }

    protected static void searchProtocolByWeek() {
        protocolList = ReadFile.readProtocolFile();
        System.out.println("Enter a number of week to search: ");
        String searchWeek = scanner.next();
        Protocol protocolLastIndex = protocolList.get(protocolList.size() - 1);
        Protocol protocolFirstIndex = protocolList.get(1);
        int lastWeek = Integer.parseInt(protocolLastIndex.weekOfYear);
        int firsWeek = Integer.parseInt(protocolFirstIndex.weekOfYear);
        int findWeek = Integer.parseInt(searchWeek);
        if (checkMethodsInputIfDigit(searchWeek)) {
            for (Protocol protocol : protocolList) {
                try {
                    if (searchWeek.equals(protocol.weekOfYear)) {
                        System.out.println(protocol);
                    } else if (findWeek > lastWeek || findWeek <= 0 || findWeek < firsWeek) {
                        System.out.println("There is NO such week number!!! Enter number between \"" + protocolFirstIndex.weekOfYear + "\" and \"" + protocolLastIndex.weekOfYear + "\"");
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    searchProtocolByWeek();
                }
            }
        }
        askForANewSearch();
    }

    private static boolean checkMethodsInputIfDigit(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                System.out.println("INVALID INPUT!!! ENTER A NUMBER!!!");
                Admin.askForANewSearch();
                return false;
            }
        }
        return true;
    }

    private static void askForANewSearch() {
        System.out.println("""
                Did you want a new search by week?
                \t1. Yes
                \t2. No""");
        String answer = scanner.next();
        while (checkMethodsInputIfDigit(answer)) {
            switch (answer) {
                case "1" -> searchProtocolByWeek();
                case "2" -> AdminMenu.viewAdminMenu();
                default -> System.out.println("Incorrect input!");
            }
            askForANewSearch();
        }
    }

    protected static void searchByEverythingInFile() {
        System.out.println("Enter that you want to search: ");
        String search = scanner.nextLine().trim();
        try {
            FileSearch.parseFile("Protocol.csv", search);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }





}