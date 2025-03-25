import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class Task1 {
    private static final String INPUT_FILE = "src/resources/file.txt";

    public static void runTask1() {
        System.out.println("Running Task 1 (Phone Numbers Validation)");

        File file = new File(INPUT_FILE);
        if (!file.exists()) {
            System.out.println("Error: File not found - " + file.getAbsolutePath());
            return;
        }

        Pattern pattern = Pattern.compile("^(\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4})$");

        try (BufferedReader br = Files.newBufferedReader(Paths.get(INPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Task 1 completed.\n");
    }
}
