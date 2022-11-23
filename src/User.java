import java.util.List;

public abstract class User {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String city;
    protected String username;
    protected String password;
    protected String validateUser;
    static List<Client> clients;


    public User(String[] parts) {
        firstName = parts[0];
        lastName = parts[1];
        email = parts[2];
        city = parts[3];
        username = parts[4];
        password = parts[5];
        validateUser = parts[6];
    }

    protected static void viewClientsList() {
        clients = ReadFile.readClientFile();
        if (!clients.isEmpty()) {
            System.out.println("Clients list: ");
            int numeric = 1;
            for (Client client : clients) {
                System.out.println(numeric + ". " + client);
                numeric++;
            }
        } else {
            System.out.println("Client's list is empty!");
        }
    }

}
