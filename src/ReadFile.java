import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReadFile {
    static List<Client> clients;
    static List<Employee> employees;
    static List<Protocol> protocolList;

    protected static List<Client> readClientFile() {
        clients = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Clients.csv"))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] detailed = currentLine.split(",");
                String name = detailed[0];
                String project = detailed[1];
                String expirationDate = detailed[2];
                clients.add(new Client(name, project, expirationDate));
            }
//            clients.sort(Comparator.comparing(Client::getContractExpirationDate).thenComparing(Client::getClientName).thenComparing(Client::getProjectName));
            sortClientsListByExpirationDate();

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException | ArrayIndexOutOfBoundsException ioe) {
            //ioe.printStackTrace();
            System.out.println(ioe.getMessage());
        }
        return clients;
    }

    private static void sortClientsListByExpirationDate() {
        clients.sort((client1, client2) -> {
            String pattern = "dd.MM.yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            try {
                Date date1 = simpleDateFormat.parse(client1.getContractExpirationDate());
                Date date2 = simpleDateFormat.parse(client2.getContractExpirationDate());
                return date1.compareTo(date2);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return 0;
        });
    }


    protected static List<Employee> readEmployeeFile() {
        employees = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Employees.csv"))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] detailed = currentLine.split(",");
                String first_name = detailed[0];
                String last_name = detailed[1];
                String email = detailed[2];
                String country = detailed[3];
                String username = detailed[4];
                String password = detailed[5];
                String validateEmployee = detailed[6];
                employees.add(new Employee(new String[]{first_name, last_name, email, country, username, password, validateEmployee}));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return employees;
    }

    protected static List<Protocol> readProtocolFile() {
        protocolList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Protocol.csv"))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] detailed = currentLine.split(",");
                String employeeName = detailed[0] + " " + detailed[1];
                String week = detailed[2];
                String dateAndTime = detailed[3] + " " + detailed[4];
                String client = detailed[5] + " " + detailed[6];
                String hourOfWork = detailed[7];
                protocolList.add(new Protocol(employeeName, week, dateAndTime, client, hourOfWork));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | IndexOutOfBoundsException ioe) {
            System.out.println(ioe.getMessage());
            // ioe.printStackTrace();
        }
        return protocolList;
    }

}
