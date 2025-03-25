import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Task3 {
    private static final String INPUT_FILE = "src/resources/words.txt";
    private static final String SERIALIZED_FILE = "src/resources/word_counts.ser";

    public static void runTask3() {
        System.out.println("Running Task 3 (Word Frequency Counting)");

        Map<String, Integer> wordCountMap = countWordFrequency(INPUT_FILE);
        serializeWordCount(wordCountMap, SERIALIZED_FILE);

        Map<String, Integer> deserializedMap = deserializeWordCount(SERIALIZED_FILE);
        if (deserializedMap != null) {
            printWordFrequencies(deserializedMap);
        }

        System.out.println("Task 3 completed.\n");
    }

    private static Map<String, Integer> countWordFrequency(String fileName) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase();
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return wordCountMap;
    }

    private static void serializeWordCount(Map<String, Integer> wordCountMap, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(wordCountMap);
            System.out.println("Word frequencies have been serialized to " + fileName);
        } catch (IOException e) {
            System.out.println("Error serializing data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Integer> deserializeWordCount(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<String, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing data: " + e.getMessage());
            return new HashMap<>();
        }
    }

    private static void printWordFrequencies(Map<String, Integer> wordCountMap) {
        wordCountMap.forEach((word, count) -> System.out.println(word + " " + count));
    }
}
