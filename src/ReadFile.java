import java.io.*;
import java.util.*;

public class ReadFile {

    public static List<Client> readClientFile() {
        List<Client> clients = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Clients.csv"))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] detailed = currentLine.split(",");
                String name = detailed[0];
                String project = detailed[1];
                String expirationDate = detailed[2];
                clients.add(new Client(name, project, expirationDate));
            }

            clients.sort(Comparator.comparing(Client::getContractExpirationDate).thenComparing(Client::getClientName).thenComparing(Client::getProjectName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return clients;
    }

    public static List<Employee> readEmployeeFile() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("employees.csv"))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] detailed = currentLine.split(",");
                String first_name = detailed[0];
                String last_name = detailed[1];
                String email = detailed[2];
                String country = detailed[3];
                String username = detailed[4];
                String password = detailed[5];

                employees.add(new Employee(new String[]{first_name, last_name, email, country, username, password}));
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return employees;
    }


}
