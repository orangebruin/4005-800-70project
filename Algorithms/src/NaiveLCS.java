/**
 * NaiveLCS.java
 *
 * This class runs the naive recursive longest common subsequence algorithm
 *
 * @author Alex Brooks
 * @author Kevin Cheek
 */

public class NaiveLCS extends LCS {

	/**
	 * This method performs the algorithm.
	 *
	 * @param S - The first input string
	 * @param T - The second input string
	 * @return	- The longest common subsequence of the two strings
	 */
	@Override
	public String Run(String s, String t) {
		String result;

		//Start the timer
		StartClock();

		//Find the LCS
		result = LCS(s,t);

		//Stop the timer
		StopClock();

		//Return the LCS
		return result;
	}

	/**
	 * Finds the LCS using the naive recursive algorithm.
	 *
	 * @param s - The first input string
	 * @param t - The second input string
	 * @return the longest common subsequence
	 */
	protected String LCS( String s, String t){
		if( s.length() == 0 || t.length() == 0)
			return "";
		if( s.charAt(0) == t.charAt(0) ){
			recursiveCalls++;
			return s.charAt(0) + LCS( s.substring(1), t.substring(1) );
		}
		recursiveCalls += 2;
		String result1 = LCS( s.substring(1), t );
		String result2 = LCS( s, t.substring(1) );
		return ( result1.length() > result2.length() ? result1 : result2);
	}

}
