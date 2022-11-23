import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSearch {

    public static void parseFile(String fileName, String searchStr) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        String line;
        while (scanner.hasNext()) {
            line = scanner.nextLine().trim();
            if (line.contains(searchStr)) {
                System.out.println(line);
            }
        }


    }


}
