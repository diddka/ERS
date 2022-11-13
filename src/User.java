import java.util.List;

public abstract class User {

    public String firstName, lastName, email, city, username, password;
    static List<Client> clients;

    public User(String[] parts) {
        firstName = parts[0];
        lastName = parts[1];
        email = parts[2];
        city = parts[3];
        username = parts[4];
        password = parts[5];
    }
    public static void viewClientsList() {
        clients = ReadFile.readClientFile();
        System.out.println("Clients list: ");
        int numeric = 1;
        for (Client client :clients ) {
            System.out.println(numeric + ". " + client);
            numeric++;
        }
    }

}
