
public class Benchmark {
	public static void main(String[] args){
		LCS naieve = new NaiveLCS();
		System.out.println("Result: " + naieve.Run( "ACDERWDFBCDF", "BCDF") + " time: " + naieve.GetRunningTime() );

	}

}
