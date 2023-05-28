import java.io.File;
import java.util.Scanner;

/**
 * This program lists the files in a directory specified by
 * the user.  The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends.
 */
public class DirectoryList {


   public static void main(String[] args) {

      String directoryName;  // Directory name entered by the user.
      File directory;        // File object referring to the directory.
      Scanner scanner;       // For reading a line of input from the user.

      scanner = new Scanner(System.in);  // scanner reads from standard input.

      System.out.print("Enter a directory name: ");
      directoryName = scanner.nextLine().trim();
      directory = new File(directoryName);

      if (directory.isDirectory() == false) {
         if (directory.exists() == false)
            System.out.println("There is no such directory!");
         else
            System.out.println("That file is not a directory.");
      }

      else {
    	  System.out.println("Files in directory \"" + directory + "\":");
      	printDir(directory, 0);
	  }
      scanner.close();

   } // end main()
   
   public static void printDir(File directory, int depth) {
	   // sub routine that prints all of the files in a directory
	   // Depth keeps track of how many directories in you are
	      String[] files;        // Array of file names in the directory.
	      File test;
		String dashes;
         files = directory.list();
         for (int i = 0; i < files.length; i++) {
        	test = new File(directory, files[i]);
        	if (test.isDirectory()) {
        		// Creates dash for directory name
        		dashes = "";
        		for (int j=0; j<depth-1; j++) {
        			dashes += "-";
        		}
        		System.out.println("   " + dashes + "dir: "+ files[i]);
        		printDir(test, depth + 1);
        	}
        	else {
        		// Creates a set of dashes to help show folder structure
        		dashes = "";
        		for (int j=0; j<depth; j++) {
        			dashes += "-";
        		}
            System.out.println("   " + dashes + files[i]);
        	}
         }
   }

} // end class DirectoryList
