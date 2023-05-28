import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Tester {
    public static void main(String[] args) {
        Set<String> dictionary = readDictionary();
        Scanner in = getInputScanner();
        in.useDelimiter("[^a-zA-Z]+");
        while (in.hasNext()) {
            String word = in.next().toLowerCase();
            if (!dictionary.contains(word)) {
                System.out.println(word + ": " + corrections(word, dictionary));
            }
        }
    }

    public static Set<String> readDictionary() {
        Set<String> dictionary = new HashSet<>();
        try (Scanner filein = new Scanner(new File("/home/rozinig/Downloads/words.txt"))) {
            while (filein.hasNext()) {
                String word = filein.next().toLowerCase();
                dictionary.add(word);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find dictionary file");
            System.exit(1);
        }
        System.out.println("Dictionary size: " + dictionary.size());
        return dictionary;
    }

    public static Scanner getInputScanner() {
        Scanner console = new Scanner(System.in);
        System.out.print("Input file: ");
        String fileName = console.nextLine();
        File inputFile = new File(fileName);
        while (!inputFile.exists() || inputFile.isDirectory()) {
            System.out.print("File not found. Try again: ");
            fileName = console.nextLine();
            inputFile = new File(fileName);
        }
        Scanner in = null;
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open " + fileName);
            System.exit(1);
        }
        return in;
    }

    public static TreeSet<String> corrections(String badWord, Set<String> dictionary) {
        TreeSet<String> candidates = new TreeSet<>();
        for (int i = 0; i < badWord.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String candidate = badWord.substring(0, i) + ch + badWord.substring(i + 1);
                if (dictionary.contains(candidate)) {
                    candidates.add(candidate);
                }
            }
        }
        return candidates;
    }
}

/*
The 'readDictionary()' method reads the words from 'words.txt' and stores them in a 'HashSet'. The words are converted to lowercase before being added to the set. The method returns the set.

The 'getInputScanner()' method prompts the user for the name of an input file and returns a 'Scanner' object that reads from that file. If the file is not found or is a directory, the user is prompted again.

The 'main()' method reads each word from the input file (ignoring non-letter characters) and checks whether it is in the dictionary. If a word is not in the dictionary, it is considered misspelled and passed to the 'corrections()' method along with the dictionary. The return value of 'corrections()' is printed, which is a sorted set of all words in the dictionary that are one character different from the misspelled word.

The 'corrections()' method generates all possible variations of the misspelled word by changing one character at a time and checking whether each variation is in the dictionary. Variations that are in the dictionary are added to a 'TreeSet', which automatically sorts the words. The method returns the sorted set of candidate corrections.

Note that this implementation does not handle words that are two or more characters different from any word in the dictionary. It also does not handle words that are compound words or have spaces
*/