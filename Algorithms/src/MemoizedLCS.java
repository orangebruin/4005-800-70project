import java.util.HashMap;

import com.sun.tools.javac.util.Pair;


public class MemoizedLCS extends LCS{

	protected HashMap<Pair<String,String>, String> mTable;

	public MemoizedLCS(){
		mTable = new HashMap<Pair<String,String>, String>();
	}

	@Override
	public String Run(String s, String t) {
		String result;
		StartClock();
		result = MLCS(s,t);
		StopClock();
		return result;
	}

	protected String MLCS( String s, String t){
		if( s.length() == 0 || t.length() == 0)
			return "";

		String result = mTable.get(new StringPair(s,t));
		if( result != null )
			return result;

		if( s.charAt(0) == t.charAt(0) ){
			recursiveCalls++;
			return s.charAt(0) + MLCS( s.substring(1), t.substring(1) );
		}

		recursiveCalls++;
		String result1 = MLCS( s.substring(1), t );

		recursiveCalls++;
		String result2 = MLCS( s, t.substring(1) );

		result = (result1.length() > result2.length() ? result1 : result2);
		mTable.put(new Pair<String,String>(s,t), result);
		return result;
	}



}
