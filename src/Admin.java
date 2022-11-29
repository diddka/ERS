import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    static Scanner scanner = new Scanner(System.in);
    static List<Employee> employeeList;
    static List<Protocol> protocolList;
    static List<Client> clientList;

    public Admin(String[] parts) {
        super(parts);
    }

    protected static void addClient() {
        String clientsFile = "Clients.csv";
        Client newClient = Client.input();
        try {
            if (checkClientList(newClient)) {
                WriteFile.writeNewClients(newClient);
                System.out.println("\nThe client " + newClient.clientName + " with a project: " + newClient.projectName + " is added!");
            } else {
                System.out.println("\nThis client with the same project name is already exist in the Company's Clients list!!!");
            }

        } catch (Exception e) {
            System.out.println("Can't write to file " + clientsFile + " " + e.getMessage());
        }
    }

    private static boolean checkClientList(Client newClient) {
        clientList = ReadFile.readClientFile();
        for (Client client : clientList) {
            if (newClient.clientName.equals(client.clientName) && newClient.projectName.equals(client.projectName)) {
                return false;
            }
        }
        return true;
    }

    protected static void registerNewEmployee() {
        String employeesFile = "Employees.csv";
        Employee registerNewEmployee = Employee.input();
        try {
            if (checkEmployeeList(registerNewEmployee)) {
                WriteFile.writeNewEmployees(registerNewEmployee);
                System.out.println("\nThe employee " + registerNewEmployee.firstName + " " + registerNewEmployee.lastName + " is added!");
            } else {
                System.out.println("\nThe employee cannot be added!!!");
            }
        } catch (Exception e) {
            System.out.println("Can't write to file " + employeesFile + " " + e.getMessage());
        }
    }

    private static boolean checkEmployeeList(Employee registerNewEmployee) {
        employeeList = ReadFile.readEmployeeFile();
        for (Employee employee : employeeList) {
            if (registerNewEmployee.firstName.equals(employee.firstName) && registerNewEmployee.lastName.equals(employee.lastName) && registerNewEmployee.username.equals(employee.username)) {
                System.out.println("\nThis employee already exist in the Company's Employees list!!!");
                return false;
            } else if (registerNewEmployee.username.equals(employee.username)) {
                System.out.println("\nThis username is in use by another employee. Please, change it!");
                return false;
            }
        }
        return true;
    }

    protected static void viewEmployeesList() {
        employeeList = ReadFile.readEmployeeFile();
        if (!employeeList.isEmpty()) {
            System.out.println("\nEmployees list: ");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        } else {
            System.out.println("Employees list is empty!");
        }
    }

    protected static void searchEmployeeByName() {
        System.out.print("\nEnter the name of the search employee: ");
        protocolList = ReadFile.readProtocolFile();
        String search = scanner.nextLine().trim();
        checkSearch(search);
    }

    private static void checkSearch(String search) {
        if (!search.isEmpty()) {
            Admin.printTheProtocolOfTheFindedEmployee(search);
        } else {
            System.out.println("\nThe search cannot be empty!");
        }
    }

    private static void printTheProtocolOfTheFindedEmployee(String search) {
        List<Protocol> sumList = new ArrayList<>();
        if (checkInputIsString(search)) {
            for (Protocol protocol : protocolList) {
                if (search.equals(protocol.getEmployeeName())) {
                    sumList.add(protocol);
                } else if (checkSearchExistEmployee(search)) {
                    System.out.println("\nThe employee doesn't exist!");
                    AdminMenu.viewAdminStatisticMenu();
                    break;
                }
            }
        }
        System.out.print("\n> > > > > For employee: " + search + " < < < < <\n");
        calculateTime(sumList);
        AdminMenu.viewAdminStatisticMenu();
    }

    private static boolean checkInputIsString(String input) {
        for (char symbol : input.toCharArray()) {
            if (Character.isAlphabetic(symbol)) {
                return true;
            } else {
                System.out.println("\nInvalid input!!! \nThe search cannot be integer! Please, ENTER an employee name!!!");
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
        List<Protocol> sumProtocolHours = new ArrayList<>();
        try {
            protocolList = ReadFile.readProtocolFile();
            System.out.print("\nEnter a number of week to search: ");
            String searchWeek = scanner.next();
            Protocol protocolLastIndex = protocolList.get(protocolList.size() - 1);
            Protocol protocolFirstIndex = protocolList.get(0);
            int lastWeek = Integer.parseInt(protocolLastIndex.weekOfYear);
            int firsWeek = Integer.parseInt(protocolFirstIndex.weekOfYear);
            int findWeek;
            try {
                findWeek = Integer.parseInt(searchWeek);
                checkIfProtocolHasNumberOfWeek(sumProtocolHours, searchWeek, protocolLastIndex, protocolFirstIndex, lastWeek, firsWeek, findWeek);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input! Please, enter a number!");
            }
            System.out.print("\n> > > > > For week: " + searchWeek + " < < < < <\n");
            calculateTime(sumProtocolHours);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        AdminMenu.askForANewSearch();
    }

    private static void checkIfProtocolHasNumberOfWeek(List<Protocol> sumProtocolHours, String searchWeek, Protocol protocolLastIndex, Protocol protocolFirstIndex, int lastWeek, int firsWeek, int findWeek) {
        for (Protocol protocol : protocolList) {
            try {
                if (searchWeek.equals(protocol.weekOfYear)) {
                    sumProtocolHours.add(protocol);
                } else if (findWeek > lastWeek || findWeek <= 0 || findWeek < firsWeek) {
                    System.out.println("\nThere is NO such week number!!! Enter a number between \"" + protocolFirstIndex.weekOfYear + "\" and \"" + protocolLastIndex.weekOfYear + "\"");
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                searchProtocolByWeek();
            }
        }
    }

    private static void calculateTime(List<Protocol> calc) {
        int sum = 0;
        for (Protocol protocol : calc) {
            System.out.println(protocol);
            sum += Integer.parseInt(protocol.hoursOfWorkForThisClient);
        }
        System.out.println(">>>>>>> Total time of work: " + sum + " hours <<<<<<<");
    }

    protected static void searchByEverythingInFile() {
        System.out.print("\nEnter that you want to search: ");
        String search = scanner.nextLine().trim();
        try {
            FileSearch.parseFile("Protocol.csv", search);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}