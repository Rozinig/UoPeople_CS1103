
public class RandomSentences {

	   static final String[] conjunctions = { "and", "or", "but", "because"};

	   static final String[] properNouns = { "Fred", "Jame", "Richard Nixon", "Miss America"};
	   
	   static final String[] commonNouns = { "man", "woman", "fish", "elephant", "unicorn"};
	   
	   static final String[] determiners = { "a", "the", "every", "some"};
	   
	   static final String[] adjective = { "big", "tiny", "pretty", "bald"};
	   
	   static final String[] intransitiveVerbs = { "runs", "jumps", "talks", "sleeps"};

	   static final String[] transitiveVerbs = { "loves", "hates", "sees", "knows", "looks for", "finds"};
	                                                                   

	   public static void main(String[] args) {
	      while (true) {
	         String sentence = sentence();
		     System.out.println(sentence + ".\n\n");
	         try {
	             Thread.sleep(3000);
	         }
	         catch (InterruptedException e) {
	         }
	      }
	   }
	   
	   static String sentence() {
	      String words = nounPhrase() + " " +verbPhrase();
	      if (Math.random() > 0.5)
		      return words;
	      return words + " " + randomItem(conjunctions) + " " + sentence();
	   }
	   
	   static String nounPhrase() {
	      String words = null;
	      if (Math.random() > 0.5)
	         words = randomItem(properNouns);
	      else {
	         words = randomItem(determiners);
	         if (Math.random()> 0.5)
	            words += " " + randomItem(adjective);
	         words += " " + randomItem(commonNouns);
	         if (Math.random()> 0.8)
	            words += " who " + verbPhrase();
	      }
	      return words;
	   }

	   static String verbPhrase(){
	      if (Math.random()< 0.25)
	         return randomItem(intransitiveVerbs);
	      if (Math.random()< 0.33)
	         return randomItem(transitiveVerbs) + " " + nounPhrase();
	      if (Math.random()< 0.5)
	         return "is " + randomItem(adjective);
	      return "believes that " + sentence(); 
	   }

	   static String randomItem(String[] listOfStrings){
	      int i = (int)(Math.random()*listOfStrings.length);
	      return listOfStrings[i];
	   }
}
