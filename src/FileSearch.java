import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSearch {

    public static void parseFile(String fileName, String searchStr) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            if (line.contains(searchStr)) {
                System.out.println(line);
            }
        }
    }
}
