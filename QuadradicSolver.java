import javax.swing.JOptionPane;

public class QuadradicSolver {

	public static void main(String[] args) {
		double a = 0;
		double b = 0;
		double c = 0;
		double r = 0;
		String quit = "";
		while (true) {
			try {
				a = Double.valueOf(JOptionPane.showInputDialog("Enter A: "));
				b = Double.valueOf(JOptionPane.showInputDialog("Enter B: "));
				c = Double.valueOf(JOptionPane.showInputDialog("Enter C: "));
				r = root(a,b,c);
				JOptionPane.showMessageDialog(null,"The root is " + r);
				
				
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null,"Please enter numbers");
			}
			catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null,e);
			}
			quit = JOptionPane.showInputDialog("Would you like to contiune?");
			quit = quit.toUpperCase();
			if (quit.equals("NO") || quit.equals("N") || quit.equals("QUIT")) {
				break;
			}
		}

	}
	/**
	 * Returns the larger of the two roots of the quadratic equation
	 * A*x*x + B*x + C = 0, provided it has any roots.  If A == 0 or
	 * if the discriminant, B*B - 4*A*C, is negative, then an exception
	 * of type IllegalArgumentException is thrown.
	 */
	static public double root( double A, double B, double C )
	                              throws IllegalArgumentException {
	    if (A == 0) {
	      throw new IllegalArgumentException("A can't be zero.");
	    }
	    else {
	       double disc = B*B - 4*A*C;
	       if (disc < 0)
	          throw new IllegalArgumentException("Discriminant < zero.");
	       return  (-B + Math.sqrt(disc)) / (2*A);
	    }
	}
}
