import java.util.Random;

public class Benchmark {

	private static final int NUM_RUNS = Integer.parseInt(System.getProperty("nruns", "10") );
	private static final long SEED = Long.parseLong(System.getProperty("seed", "0") );
	private static final long RUNNING_TIME = Long.parseLong(System.getProperty("running_time", "10000"));
	private static final boolean VERBOSE = Boolean.parseBoolean(System.getProperty("verbose", "false"));
	private static final long INCREMENT_THRESHOLD = Long.parseLong(System.getProperty("it", "250") );
	private static final int INCREMENT_AMOUNT = Integer.parseInt(System.getProperty("ia", "10") );


	public static void main(String[] args) {
		LCS alg = null;

		if( args.length < 2 || args.length > 3){
			System.err.println("Usage: LCS algorithm string1 string2 or \n");
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

		switch( args.length ){
			case 3:
				timing( alg, args[1], args[2]);
				break;
			case 2:
				benchmark( alg, args[1] );
		}

	}

	private static void timing( LCS alg, String str1, String str2 ){
		long time=0;
		int recursiveCalls=0;
		String out;
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

	private static void benchmark(LCS alg, String characterSet){
		long totalTime = 0;
		long time=0;
		long prevTime=0;
		int size=10;
		int increment=10;
		String out;
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
				time = alg.GetRunningTime();
				totalTime += alg.GetRunningTime();
				if( time - prevTime < INCREMENT_THRESHOLD )
					increment += INCREMENT_AMOUNT;
				size += increment;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Max String Size (2x): " + size + " Total Time: " + totalTime + " Last Run Time: "+ time);
	}

	private static String randomString( String characterSet, int length){
		Random rand = new Random(SEED);
		String out = "";
		for( int i=0; i < length; i++)
			out += characterSet.charAt(rand.nextInt(characterSet.length()));
		return out;
	}


}
