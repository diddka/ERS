import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Database {
    public static Map<String, Employee> load() {
        Map<String, Employee> employeeMap = new HashMap<>();
        try {
            Scanner data = new Scanner(new File("Employees.csv"));

            while (data.hasNextLine()) {
                String[] split = data.nextLine().split(",");
                Employee employee = new Employee(split);
                employeeMap.put(employee.username, employee);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return employeeMap;
    }

}
