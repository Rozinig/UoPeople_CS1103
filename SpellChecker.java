import java.util.*;
import java.io.*;
import javax.swing.*;

public class SpellChecker {

	public static void main(String[] args) throws FileNotFoundException {
		HashSet<String> dict = createDict();
		System.out.println("The Dictionary contains 'the' " + dict.contains("the"));
		System.out.println("The Dictionary contains " + dict.size() + " words.");
		Scanner testfile = new Scanner(getInputFileNameFromUser());
		testfile.useDelimiter("[^a-zA-Z]+");
		HashMap<String, TreeSet<String>> corrected = new HashMap<>(); 
		while (testfile.hasNext()) {
			String word = testfile.next().toLowerCase();
			if (!dict.contains(word)) {
				corrected.put(word, corrections(word, dict));
			}
		}
		for (Map.Entry<String, TreeSet<String>> entry : corrected.entrySet()) {
			System.out.print(entry.getKey() + " ");
			for (String w : entry.getValue()) {
				System.out.print(w + " ");
			}
			System.out.println();
		}
		

	}
	
	static HashSet<String> createDict() throws FileNotFoundException {
		HashSet<String> dict = new HashSet<>();
		Scanner filein = new Scanner(new File("/home/rozinig/Downloads/words.txt"));
		while (filein.hasNext()) {
			dict.add(filein.next().toLowerCase());
		}
		return dict;
	}
    /**
     * Lets the user select an input file using a standard file
     * selection dialog box.  If the user cancels the dialog
     * without selecting a file, the return value is null.
     */
    static File getInputFileNameFromUser() {
       JFileChooser fileDialog = new JFileChooser();
       fileDialog.setDialogTitle("Select File for Input");
       int option = fileDialog.showOpenDialog(null);
       if (option != JFileChooser.APPROVE_OPTION)
          return null;
       else
          return fileDialog.getSelectedFile();
    }
    static TreeSet<String> corrections(String badWord, HashSet dict){
    	TreeSet<String> corrections = new TreeSet<>();
    	for (int i = 0; i < badWord.length(); i++) {
    		//Delete any one of the letters from the misspelled word. 
    		String s = badWord.substring(0, i) + badWord.substring(i+1);
    		if (dict.contains(s)) {
    			corrections.add(s);
    		}
    		for (char ch = 'a'; ch <= 'z'; ch++) {
    			//Change any letter in the misspelled word to any other letter. 
    			s = badWord.substring(0, i) + ch + badWord.substring(i+1);
        		if (dict.contains(s)) {
        			corrections.add(s);
        		}
        		// Insert any letter at any point in the misspelled word. 
    			s = badWord.substring(0, i) + ch + badWord.substring(i);
        		if (dict.contains(s)) {
        			corrections.add(s);
        		}
    		}
    	}
    	for (int i = 0; i < badWord.length()-1; i++) {
    		//Swap any two neighboring characters in the misspelled word. 
    		String s = badWord.substring(0, i) + badWord.substring(i+1, i+2) + badWord.substring(i, i+1) + badWord.substring(i+2);
            	if (dict.contains(s)) {
            		corrections.add(s);
            	}
    		//Insert a space at any point in the misspelled word 
    		s = badWord.substring(0, i);
    		String t = badWord.substring(i);
    		if (dict.contains(s) && dict.contains(t)) {
    			s = s + " " + t;
    			corrections.add(s);
    		}
    		
    		
    		
    	}
    	
    	
    	return corrections;
    }

}
