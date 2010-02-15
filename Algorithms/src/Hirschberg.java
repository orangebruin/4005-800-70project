
public class Hirschberg extends LCS {

	@Override
	public String Run(String S, String T) {

		//Start clock
		StartClock();

		//Run algorithm
		String lcs = algoC(S.length(), T.length(), S, T);

		//Stop clock
		StopClock();

		//Return LCS
		return lcs;
	}

	private String algoC(int m, int n, String a, String b){
		//Check recursion conditions
		if( n == 0 ){
			//Return empty string if one string is the empty string
			return "";
		} else if( m == 1){

			//Check if character is in both strings
			char temp = a.charAt(0);
			for( int j = 0; j < n; j++ ){
				if( temp == b.charAt(j) ){
					//Return character as a string
					return "" + temp;
				}
			}

			//Return empty string if character is not in both strings
			return "";
		} else {
			//Get floor of half of string a
			int i = m / 2;

			//Find lengths of LCS's of divided string a
			int L1[] = algoB(i, n, a.substring(0, i), b);
			String aRev = new String ( new StringBuffer(a).reverse() );
			String bRev = new String ( new StringBuffer(b).reverse() );
			int L2[] = algoB(m-i, n, aRev.substring(0, m-i), bRev );

			//Find split point for recursive calls
			int k = 0, M = L1[0] + L2[0];

			//Find split point
			for( int j = 0; j < L2.length; j++ ){
				if( (L1[j]+L2[n-j]) > M ){
					M = L1[j]+L2[n-j];
					k = j;
				}
			}

			//Make recursive calls
			String C1 = algoC(i, k, a.substring(0, i), b.substring(0, k));
			String C2 = algoC(m-i, n-k, a.substring(i), b.substring(k));
			recursiveCalls += 2;

			//Return Strings
			return C1+C2;
		}
	}

	private int[] algoB(int m, int n, String a, String b){
		int mat[][] = new int[2][n+1];

		//Initialize table
		for( int j = 0; j < n+1; j++ ){
			mat[1][j] = 0;
		}

		//Find length of LCS in linear space
		for( int i = 1; i < m+1; i++ ){
			for( int j = 1; j < n+1; j++ ){
				mat[0][j] = mat[1][j];
			}

			for( int j = 1; j < n+1; j++ ){
				if( a.charAt(i-1) == b.charAt(j-1) ){
					mat[1][j] = mat[0][j-1] + 1;
				} else {
					if( mat[1][j-1] > mat[0][j] )
						mat[1][j] = mat[1][j-1];
					else
						mat[1][j] = mat[0][j];
				}
			}
		}

		//Print results
//		for( int j = 0; j < n; j++ )
//			System.out.printf("%d ", mat[1][j] );

		//Return values
		return mat[1];
	}
}
