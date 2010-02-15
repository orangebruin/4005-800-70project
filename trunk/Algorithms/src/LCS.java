
public abstract class LCS {
	protected long startTime=0;
	protected long endTime=0;
	protected int recursiveCalls=0;

	public abstract String Run( String S, String T );

	protected void StartClock(){
		if( startTime != 0)
			throw new IllegalStateException("Error: Start time already initialized");
		startTime = System.currentTimeMillis();


	}

	protected void StopClock(){
		if( endTime != 0)
			throw new IllegalStateException("Error: Start time already initialized");
		endTime = System.currentTimeMillis();
	}

	public int GetRecursiveCalls(){
		return recursiveCalls;
	}

	public void ClearRunningTime(){
		startTime = 0;
		endTime = 0;
		recursiveCalls = 0;
	}

	public long GetRunningTime(){
		if( startTime == 0 || endTime == 0 )
			throw new IllegalStateException("Start Clock or end Clock must be used");
		return endTime - startTime;
	}
}
