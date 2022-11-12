import java.util.Scanner;

public class Client {
    protected String clientName;
    protected String projectName;
    protected String contractExpirationDate;

    public Client(String name, String projectName, String contractExpirationDate) {
        this.clientName = name;
        this.projectName = projectName;
        this.contractExpirationDate = contractExpirationDate;
    }

    public String getClientName() {
        return clientName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getContractExpirationDate() {
        return contractExpirationDate;
    }

    protected static Client input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a Client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter a project name: ");
        String projectName = scanner.nextLine();
        System.out.print("Enter the contract expiration date: ");
        String contractExpirationDate = scanner.nextLine();
        return new Client(name, projectName, contractExpirationDate);
    }

    @Override
    public String toString() {
        return "The client name is ~ " + clientName + ", with a project: " + projectName + " and contract expiration date: " + contractExpirationDate;
    }
}
