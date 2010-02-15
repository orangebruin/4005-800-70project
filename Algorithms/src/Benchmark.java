/**
 * Benchmark.java
 *
 * This class serves as the main class for running each of the LCS algorithms.
 *
 * @author Alex Brooks
 * @author Kevin Cheek
 */

import java.util.Random;

public class Benchmark {

	//Properties governing the execution of the program
	private static final int NUM_RUNS = Integer.parseInt(System.getProperty("nruns", "10") );
	private static final long SEED = Long.parseLong(System.getProperty("seed", "0") );
	private static final long RUNNING_TIME = Long.parseLong(System.getProperty("running_time", "10000"));
	private static final boolean VERBOSE = Boolean.parseBoolean(System.getProperty("verbose", "false"));
	private static final boolean CSV = Boolean.parseBoolean(System.getProperty("csv", "false"));
	private static final long INCREMENT_THRESHOLD = Long.parseLong(System.getProperty("it", "250") );
	private static final int INCREMENT_AMOUNT = Integer.parseInt(System.getProperty("ia", "10") );

	/**
	 * The main method.
	 *
	 * @param args - the command line agruments
	 */
	public static void main(String[] args) {
		LCS alg = null;

		//Check if number of arguments is valid
		if( args.length < 2 || args.length > 3){
			System.err.println("Usage: LCS algorithm string1 string2 or ");
			System.err.println("Usage: LCS algorithm character_set");
			System.exit(1);
		}


		//Go to given algorithm method
		switch( Integer.parseInt(args[0]) ){
			case 1:
				alg = new NaiveLCS();
				break;
			case 2:
				alg = new MemoizedLCS();
				break;
			case 3:
				alg = new DP();
				break;
			case 4:
				alg = new Hirschberg();
				break;
			default:
				//Invalid state
				System.err.println("ERROR: Invalid Algorithm\n");
		}

		//Choose selected experiment
		switch( args.length ){
			case 3:
				timing( alg, args[1], args[2]);
				break;
			case 2:
				benchmark( alg, args[1] );
		}

	}

	/**
	 * Determine the amount of time needed to find the LCS of two given strings
	 * using the given algorithm. It is found by using the average time over a
	 * number of runs. 10 runs is the default.
	 *
	 * @param alg  - The LCS algorithm to be used
	 * @param str1 - The first input string
	 * @param str2 - The second input string
	 */
	private static void timing( LCS alg, String str1, String str2 ){
		long time=0;			//The total time taken
		int recursiveCalls=0;	//The number of recursive calls
		String out;				//The longest common subsequence

		for( int i=0; i < NUM_RUNS; i++){
			out = alg.Run(str1, str2);
			if( VERBOSE ){
				System.out.println("LCS: "+ out + "\n");
			}
			time += alg.GetRunningTime();
			recursiveCalls = alg.GetRecursiveCalls();
			alg.ClearRunningTime();
		}
		System.out.println("NumRuns: " + NUM_RUNS + " Average Time: " + (time / NUM_RUNS) +
				" Total Time: " + time + " Recursive Calls (Last Run): " + recursiveCalls + "\n");
	}

	/**
	 * Finds the length of the longest inputs that can be processed in a
	 * certain amount of time by the given algorithm.
	 * The default time is 10 seconds.
	 *
	 * @param alg			- The LCS algorithm
	 * @param characterSet	- The alphabet to be used in creating the
	 * 						  input strings
	 */
	private static void benchmark(LCS alg, String characterSet){
		long totalTime = 0;		//The total time taken for test
		long time = 0;			//Time for individual run
		long prevTime = 0;		//Time for last run
		int size = 10;			//The length of the inputs
		int increment = 10;		//Value length is increased by
		String out;				//The longest common subsequence

		try{
			while( time < RUNNING_TIME ){
				prevTime = time;
				alg.ClearRunningTime();
				out = alg.Run(randomString(characterSet, size), randomString(
						characterSet, size));
				if( VERBOSE ){
					System.out.println("Size: " + size + " Time:  " + time
							+ " LCS: " + out + "\n");
				}
				if( CSV ){
					System.out.println(size + "," + time +"," + alg.GetRecursiveCalls() );
				}
				time = alg.GetRunningTime();
				totalTime += alg.GetRunningTime();
				if( time - prevTime < INCREMENT_THRESHOLD )
					increment += INCREMENT_AMOUNT;
				size += increment;
			}
		}catch(Exception e){
			e.getMessage();
		} finally {
			System.out.println("Max String Size (2x): " + size + " Total Time: " + totalTime + " Last Run Time: "+ time);
		}
	}

	/**
	 * Generates a random string for use in the benchmark method.
	 *
	 * @param characterSet	- The alphabet to be used in creating
	 * 						  the input string
	 * @param length		- the desired length of the string
	 * @return	the randomly created string
	 */
	private static String randomString( String characterSet, int length){
		Random rand = new Random(SEED);
		String out = "";
		for( int i=0; i < length; i++)
			out += characterSet.charAt(rand.nextInt(characterSet.length()));
		return out;
	}


}
