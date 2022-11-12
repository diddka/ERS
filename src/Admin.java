import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin extends User {

    public Admin(String[] parts) {
        super(parts);
    }

    public static void addClient() {
        String clientsFile = "Clients.csv";
        try {
            WriteFile.writeNewClients(Client.input());
        } catch (Exception e) {
            System.out.println("Can't write to file " + clientsFile);
        }
    }

    public static void registerNewEmployee() {
        String employeesFile = "employees.csv";
        try {
            WriteFile.writeNewEmployees(Employee.input());
        } catch (Exception e) {
            System.out.println("Can't write to file " + employeesFile);
        }
    }

    public static void viewEmployeesList() {
        System.out.println("Employees list: ");
        for (Employee employee : ReadFile.readEmployeeFile()) {
            System.out.println(employee);
        }
    }

    public static void searchEmployeeByName() {
        System.out.println("Enter the employee name for search: ");

    }

    public static void searchProtocolByWeek() {
        System.out.println("Enter a number of week to search: ");

    }

    public static void searchByEverythingInFile() {
        System.out.println("Enter that you want to search: ");
        Scanner scanner = new Scanner(System.in);
        String search = scanner.nextLine();
        try {
            FileSearch.parseFile("Protocol.csv", search);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}

