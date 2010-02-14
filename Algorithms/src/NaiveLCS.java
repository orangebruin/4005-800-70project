
public class NaiveLCS extends LCS {
	public NaiveLCS(){}

	@Override
	public String Run(String s, String t) {
		String result;
		StartClock();
		result = LCS(s,t);
		StopClock();
		return result;
	}

	protected String LCS( String s, String t){
		if( s.length() == 0 || t.length() == 0)
			return "";
		if( s.charAt(0) == t.charAt(0) ){
			return s.charAt(0) + LCS( s.substring(1), t.substring(1) );
		}
		recursiveCalls++;
		String result1 = LCS( s.substring(1), t );
		recursiveCalls++;
		String result2 = LCS( s, t.substring(1) );
		return ( result1.length() > result2.length() ? result1 : result2);
	}

}
