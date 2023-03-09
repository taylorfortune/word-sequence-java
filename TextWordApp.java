//when given text(s), will return a list of 100 most common three word sequences - - The program accepts as arguments a list of one or more file paths
// (e.g. ./solution.rb file1.txt file2.txt ...).
// - The program also accepts input on stdin (e.g. cat file1.txt |
// ./solution.rb).
// - The program outputs a list of the 100 most common three words
// sequences, along with a count of how many times each occurred in the text.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextWordApp {
    public static final String WORD_PATTERN = "((\\a+'\\a+)|(\\a+-?\\a+)|(\\a+))";
    public static boolean PROCESS_WHOLE_FILE = false;

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            processStdIn();
        } else if (args != null && args.length > 0) {
            for (String file : args) {
                processFile(file);
            }
        } else {
            System.out.println("No input detected. Exiting... ");
        }
    }
    // processing through the whole text file
    private static void processStdIn() {
        System.out.println("Processing stdin...");

        if (PROCESS_WHOLE_FILE) {
            processStdInWholeFile();
        } else {
            processStdInLineByLine();
        }
    }


    private static void processFile(String fileName) {
        System.out.println("Processing: " + fileName);

        if (PROCESS_WHOLE_FILE) {
            processWholeFile(fileName);
        } else {
            processFileLineByLine(fileName);
        }
    }

    //HERE OUTPUTTING THE TABLE for the common three word phases and how often they will show up in the file as a text
    //inputting a stream method for the file and StdIn

    private static void printTopPhrases(Map<String, Integer> phrases, int limit, String inputStream) {
        if (phrases.size() > 0) {
            System.out.println("Phrase | Count");
            phrases.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(100)
                    .forEach(entry -> {
                        System.out.println(String.format("%-40s | %d",entry.getKey(), entry.getValue()));
                    });
        } else {
            System.out.println("No three word phrases detected.");
        }
        System.out.println("Finished Processing -> " + inputStream + System.getProperty("line.separator"));
    }

    //here processing the file line by line for the most common three word sequences. inporting a try and catch statement for the file to be caught
    private static void processStdInLineByLine() {
        InputStreamReader isReader = new InputStreamReader(System.in);
        try (BufferedReader br = new BufferedReader(isReader)) {
            Map<String, Integer> phrases = processContent(br);

            printTopPhrases(phrases, 100, "StdIn");
        } catch (IOException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }
    }
//processing the file line by each line to output all the most common three word sequences.

    private static void processFileLineByLine(String fileName) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            Map<String, Integer> phrases = processContent(br);

            // Output
            printTopPhrases(phrases, 100, fileName);
        } catch (IOException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }
    }
    //data using line by line and mapping over the containing every three word phases

    private static Map<String, Integer> processContent(BufferedReader br) throws IOException {
        Map<String, Integer> phrases = new HashMap<String, Integer>();
        String currentLine = null;

        List<String> matches = new LinkedList<String>();
        // here trimming the lines for the three word phases and taking the length of it.
        while ((currentLine = br.readLine()) != null) {
            if (currentLine.trim().length() == 0) {
                continue;
            }
            matches.addAll(getMatchesAsLinkedList(currentLine));

            while (matches.size() >= 3) {
                insertPhrase(phrases, getPhrase(matches.get(0), matches.get(1), matches.get(2)));
                matches.remove(0);
            }
        }
        return phrases;
    }

    //inserting the phrases and mapping them through the text to be inserted.
    private static void insertPhrase(Map<String, Integer> phrases, String phrase) {
        if (phrases.containsKey(phrase)) {
            phrases.put(phrase, phrases.get(phrase) + 1);
        } else {
            phrases.put(phrase, 1);
        }
    }

    private static String getPhrase(String word1, String word2, String word3) {
        StringBuilder phrase = new StringBuilder();
        return phrase.append(word1).append(" ").append(word2).append(" ").append(word3).toString();
    }

    // allowing the file to be able to go through the line of text that returns correct words and removes the punctuation
    // returning a list of words that matches as correct
    public static List<String> getMatchesAsList(String line) {
        List<String> matches = new ArrayList<String>();
        Pattern y = Pattern.compile(WORD_PATTERN);
        Matcher z = y.matcher(line);
        while (z.find()) {
            matches.add(z.group().toLowerCase());
        }
        return matches;
    }

    //processing through the line of text and to the linked list as correct words and removes punctation
    public static LinkedList<String> getMatchesAsLinkedList(String line) {
        LinkedList<String> matches = new LinkedList<String>();
        Pattern y = Pattern.compile(WORD_PATTERN);
        Matcher z = y.matcher(line);
        while (z.find()) {
            matches.add(z.group().toLowerCase());
        }
        return matches;
    }
    // processing through the whole file by reading the content in the file then outputting it through the three word phrases
    public static void processStdInWholeFile() {
        InputStreamReader isReader = new InputStreamReader(System.in);
        try (BufferedReader br = new BufferedReader(isReader)) {
            Map<String, Integer> phrases = processAllContent(br);

            // Output
            printTopPhrases(phrases, 100, "StdIn");
        } catch (IOException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }
    }
    //processing through the entire file and outputting the common three word sequences.
    public static void processWholeFile(String fileName) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            Map<String, Integer> phrases = processAllContent(br);

            printTopPhrases(phrases, 100, fileName);
        } catch (IOException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> processAllContent(BufferedReader br) throws IOException {
        Map<String, Integer> phrases = new HashMap<String, Integer>();
        String line = "";
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        String contents = sb.toString().trim();

        // here getting a list of strings that match our definition of words
        List<String> matches = getMatchesAsList(contents);

        // Processing the words through the file and getting it
        for (int i = 0; i < matches.size() - 2; i++) {
            insertPhrase(phrases, getPhrase(matches.get(i), matches.get(i + 1), matches.get(i + 2)));
        }
        return phrases;
    }
}