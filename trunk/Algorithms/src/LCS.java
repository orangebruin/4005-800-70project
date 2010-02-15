/**
 * LCS.java
 *
 * This is the base class for each of the LCS algorithms.
 *
 * @author Alex Brooks
 * @author Kevin Cheek
 */
public abstract class LCS {

	//Start time for the algorithm run
	protected long startTime=0;

	//End time for the algorithm run
	protected long endTime=0;

	//The number of recursive calls done by the algorithm
	protected long recursiveCalls=0;

	/**
	 * This method performs the algorithm.
	 * It will be overridden by any subclass.
	 *
	 * @param S - The first input string
	 * @param T - The second input string
	 * @return	- The longest common subsequence of the two strings
	 */
	public abstract String Run( String S, String T );

	/**
	 * Starts the timer.
	 */
	protected void StartClock(){
		if( startTime != 0)
			throw new IllegalStateException("Error: Start time already initialized");
		startTime = System.currentTimeMillis();


	}

	/**
	 * Stops the timer.
	 */
	protected void StopClock(){
		if( endTime != 0)
			throw new IllegalStateException("Error: Start time already initialized");
		endTime = System.currentTimeMillis();
	}

	/**
	 * Returns the number of recursive calls made by the algorithm
	 *
	 * @return the number of recursive calls
	 */
	public long GetRecursiveCalls(){
		return recursiveCalls;
	}

	/**
	 * Resets the counters for the next run.
	 */
	public void ClearRunningTime(){
		startTime = 0;
		endTime = 0;
		recursiveCalls = 0;
	}

	/**
	 * This method calculates and returns the running time of the algorithm.
	 *
	 * @return the running time of the algorithm
	 */
	public long GetRunningTime(){
		if( startTime == 0 || endTime == 0 )
			throw new IllegalStateException("Start Clock or end Clock must be used");
		return endTime - startTime;
	}
}
