import java.io.*;

public class WriteFile {

    public static void writeNewClients(Client clients) {
        try {
            PrintWriter fileWrite = new PrintWriter(new BufferedWriter(new FileWriter("Clients.csv", true)));
            fileWrite.write((clients.clientName + "," + clients.projectName + "," + clients.contractExpirationDate) + System.lineSeparator());
            fileWrite.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeNewEmployees(Employee employee) {
        try {
            BufferedWriter fileWrite = new BufferedWriter(new FileWriter("Employees.csv", true));
            fileWrite.write((employee.firstName + "," + employee.lastName + "," + employee.email + "," + employee.city + "," + employee.username + "," + employee.password) + System.lineSeparator());
            fileWrite.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void writeEmployeeProtocol(Protocol protocol, String firstName, String lastName) {
        try {
            BufferedWriter fileWrite = new BufferedWriter(new FileWriter("Protocol.csv", true));
            fileWrite.write((firstName + "," + lastName + "," + protocol.weekOfYear + "," + protocol.date + "," + protocol.clientsFromList + "," + protocol.hoursOfWorkForThisClient) + System.lineSeparator());
            fileWrite.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

