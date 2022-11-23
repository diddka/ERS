import java.io.*;

public class WriteFile {

    protected static void writeNewClients(Client clients) {
        try {
            PrintWriter fileWrite = new PrintWriter(new BufferedWriter(new FileWriter("Clients.csv", true)));
            fileWrite.write((clients.getClientName() + "," + clients.getProjectName() + "," + clients.getContractExpirationDate()) + System.lineSeparator());
            fileWrite.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected static void writeNewEmployees(Employee employee) {
        try {
            BufferedWriter fileWrite = new BufferedWriter(new FileWriter("Employees.csv", true));
            fileWrite.write((employee.firstName + "," + employee.lastName + "," + employee.email + "," + employee.city + "," + employee.username + "," + employee.password + "," + employee.validateUser) + System.lineSeparator());
            fileWrite.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    protected static void writeEmployeeProtocol(Protocol protocol, String firstName, String lastName) {
        try {
            BufferedWriter fileWrite = new BufferedWriter(new FileWriter("Protocol.csv", true));
            fileWrite.write((firstName + "," + lastName + "," + protocol.weekOfYear + "," + protocol.date + "," + protocol.clientsFromList + "," + protocol.hoursOfWorkForThisClient) + System.lineSeparator());
            fileWrite.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

