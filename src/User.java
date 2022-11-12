public abstract class User {

    public String firstName, lastName, email, city, username, password;

    public User(String[] parts) {
        firstName = parts[0];
        lastName = parts[1];
        email = parts[2];
        city = parts[3];
        username = parts[4];
        password = parts[5];
    }


    public static void viewClientsList() {
        System.out.println("Clients list: ");
        int numeric = 1;
        for (Client client : ReadFile.readClientFile()) {
            System.out.println(numeric + ". " + client);
            numeric++;
        }
    }

}
