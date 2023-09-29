import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter '1' to input text directly or '2' to provide a file path: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        String text = "";
        
        if (choice == 1) {
            System.out.println("Enter your text: ");
            text = scanner.nextLine();
        } else if (choice == 2) {
            System.out.println("Enter the file path: ");
            String filePath = scanner.nextLine();
            
            try {
                text = readFile(filePath);
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please check the file path.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid choice. Please enter '1' or '2'.");
            scanner.close();
            return;
        }
        
        // Split the text into words using space or punctuation as delimiters
        String[] words = text.split("[\\s\\p{Punct}]+");
        
        // Initialize a counter variable for words
        int wordCount = 0;
        
        // Create a map to store word frequencies
        Map<String, Integer> wordFrequency = new HashMap<>();
        
        // Ignore common stop words (you can customize this list)
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "and", "of", "in", "a", "to", "is"));
        
        // Iterate through the array of words
        for (String word : words) {
            // Increment the word counter
            wordCount++;
            
            // Convert the word to lowercase to make it case-insensitive
            word = word.toLowerCase();
            
            // Check if the word is not a stop word
            if (!stopWords.contains(word)) {
                // Update word frequency
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
        
        // Display the total count of words
        System.out.println("Total word count: " + wordCount);
        
        // Display the number of unique words
        System.out.println("Unique word count: " + wordFrequency.size());
        
        // Display word frequencies
        System.out.println("Word frequencies:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        scanner.close();
    }
    
    // Helper method to read a file and return its content as a string
    private static String readFile(String filePath) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(filePath));
        StringBuilder content = new StringBuilder();
        
        while (fileScanner.hasNextLine()) {
            content.append(fileScanner.nextLine()).append("\n");
        }
        
        fileScanner.close();
        return content.toString();
    }
}