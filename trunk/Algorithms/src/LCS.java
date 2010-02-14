
public abstract class LCS {
	protected long startTime=0;
	protected long endTime=0;
	protected int recursiveCalls=0;

	public abstract String Run( String S, String T );

	protected void StartClock(){
		startTime = System.currentTimeMillis();
	}

	protected void StopClock(){
		endTime = System.currentTimeMillis();
	}

	public int GetRecursiveCalls(){
		return recursiveCalls;
	}

	public long GetRunningTime(){
		if( startTime == 0 || endTime == 0 )
			throw new IllegalStateException("Start Clock or end Clock must be used");
		return endTime - startTime;
	}
}
