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
        protocolList = ReadFile.readProtocolFile();
        System.out.println("Enter the employee name for search: ");

    }

    public static void searchProtocolByWeek() {
        protocolList = ReadFile.readProtocolFile();
        System.out.println("Enter a number of week to search: ");
        String searchWeek = scanner.next();
        Protocol protocolLastIndex = protocolList.get(protocolList.size()-1);
        if (checkMethodsInputIfDigit(searchWeek) ){
            for (Protocol pr : protocolList) {
                try {
                    if (searchWeek.equals(pr.weekOfYear)) {
                        System.out.println(pr);
                    }else if(Integer.parseInt(searchWeek) > Integer.parseInt(protocolLastIndex.weekOfYear)) {
                        System.out.println("Last number of week in protocol list is \"" + Integer.parseInt(protocolLastIndex.weekOfYear) + "\" Please enter a number lower than this!!!"  );
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

    protected static void askForANewSearch() {
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

    protected static boolean checkMethodsInputIfDigit(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                System.out.println("INVALID INPUT!!! ENTER A NUMBER!!!");
                askForANewSearch();
                return false;
            }
        }
        return true;
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

