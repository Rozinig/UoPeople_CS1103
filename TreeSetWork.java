import java.util.*;

public class TreeSetWork {

	
	public static void main(String[] args){
		while (true) {
			try {
				//Enter and Try an expression
				System.out.println("Please enter a set expression");
				compute();
			}
			catch (Exception e){
				// If the expression fails Tell why and allow another try
				System.out.println("There was an error:" +e.getMessage());
				System.out.println("Try again");
			}
		}
		
	} // End Main
	private static void compute(){
		TreeSet<Integer> A, B;
		char c;
		
		//Read the first set
		A = readSet();
		System.out.println("Set A is: " + A);
		
		// Read the operator
		TextIO.skipBlanks();
		c = TextIO.getAnyChar();
		if (c != '*' && c!= '+' && c!= '-') {
			throw new IllegalArgumentException("Expected an Operator: +,-, or *");
		}
		
		//Read the second set
		B = readSet();
		System.out.println("Set B is: " + B);
		
		//Computer the new set
		if (c == '*') {
			A.retainAll(B);
		}
		else if (c == '+'){
			A.addAll(B);
		}
		else {
			A.removeAll(B);
		}

		//output the new set 
		TextIO.skipBlanks();
		c = TextIO.getAnyChar();
		if (c == '\n') {
			System.out.println(A);
		}
		else {
			throw new IllegalArgumentException("Expected end of line got: " + c);
		}
		
		
		
	}
	private static TreeSet<Integer> readSet(){
		TreeSet<Integer> set = new TreeSet<>();
		TextIO.skipBlanks();
		
		// Check start of set
		char c;
		c = TextIO.getAnyChar();
		if (c != '['){
			throw new IllegalArgumentException("Expected a [ to start a set.");
		}
		
		//starting with an empty string read all of the numbers of the integer and then add them to the set
		String s;
		while (true) {
			s = "";
			while (true) {
				TextIO.skipBlanks();
				c = TextIO.getAnyChar();
				if (c == ',') {
					if (s != "") {
						set.add(Integer.parseInt(s));
						break;
					}
				}
				else if (Character.isDigit(c)) {
					s += c;
				}
				else if (c == ']') {
					if (s != "") {
						set.add(Integer.parseInt(s));
					}
					return set;
				}
				else {
					throw new IllegalArgumentException("Expected a number or a comma.");
				}
				
			}
			
			
		}
	}
	
}
