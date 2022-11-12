import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Database {
    public static Map<String, Employee> load() {
        Map<String, Employee> employeeMap = new HashMap<>();
        try {
            Scanner data_store = new Scanner(new File("employees.csv"));

            while (data_store.hasNextLine()) {
                String[] split_string = data_store.nextLine().split(",");
                Employee employee = new Employee(split_string);
                employeeMap.put(employee.username, employee);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return employeeMap;
    }

}
