import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Task2 {
    private static final String INPUT_FILE = "src/resources/file1.txt";
    private static final String OUTPUT_FILE = "src/resources/users.json";

    public static void runTask2() {
        System.out.println("Running Task 2 (User JSON Serialization)");

        List<User> users = readUsersFromFile(INPUT_FILE);
        writeUsersToJson(users, OUTPUT_FILE);

        System.out.println("Task 2 completed.\n");
    }

    private static List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            br.readLine(); // Пропускаємо заголовок

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    users.add(new User(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
        }

        return users;
    }

    private static void writeUsersToJson(List<User> users, String fileName) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(fileName))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }
}
