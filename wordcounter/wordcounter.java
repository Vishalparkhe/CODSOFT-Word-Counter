package wordcounter;


	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.*;

	 class WordCounter {

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Word Counter Program");
	        System.out.print("Enter 'text' to input text or 'file' to input a file: ");
	        String choice = scanner.nextLine();

	        if (choice.equalsIgnoreCase("text")) {
	            System.out.print("Enter your text: ");
	            String input = scanner.nextLine();
	            countWordsAndDisplayStats(input);
	        } else if (choice.equalsIgnoreCase("file")) {
	            System.out.print("Enter the file path: ");
	            String filePath = scanner.nextLine();
	            try {
	                String fileContent = readFile(filePath);
	                countWordsAndDisplayStats(fileContent);
	            } catch (IOException e) {
	                System.err.println("Error reading the file: " + e.getMessage());
	            }
	        } else {
	            System.err.println("Invalid choice. Please enter 'text' or 'file'.");
	        }
	    }

	    private static String readFile(String filePath) throws IOException {
	        StringBuilder content = new StringBuilder();
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                content.append(line).append(" ");
	            }
	        }
	        return content.toString();
	    }

	    private static void countWordsAndDisplayStats(String text) {
	        String[] words = text.split("[\\s\\p{Punct}]+");
	        List<String> stopWords = Arrays.asList("the", "and", "is", "in", "to", "a", "of", "it");

	        int wordCount = 0;
	        Map<String, Integer> wordFrequency = new HashMap<>();

	        for (String word : words) {
	            word = word.toLowerCase();

	            if (!stopWords.contains(word)) {
	                wordCount++;

	                if (wordFrequency.containsKey(word)) {
	                    wordFrequency.put(word, wordFrequency.get(word) + 1);
	                } else {
	                    wordFrequency.put(word, 1);
	                }
	            }
	        }

	        System.out.println("Total words: " + wordCount);
	        System.out.println("Unique words: " + wordFrequency.size());
	        System.out.println("Word Frequency:");

	        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
	            System.out.println(entry.getKey() + ": " + entry.getValue());
	        }
	    }
	}


