/**
 * DP.java
 *
 * This class runs the dynamic programming longest common subsequence algorithm.
 *
 * @author Alex Brooks
 * @author Kevin Cheek
 */

public class DP extends LCS {

	/**
	 * This method performs the algorithm.
	 *
	 * @param S - The first input string
	 * @param T - The second input string
	 * @return	- The longest common subsequence of the two strings
	 */
	@Override
	public String Run(String S, String T) {
		int cur, len1 = S.length(), len2 = T.length();
		int mat[][] = new int[len1+1][len2+1];

		//Start the timer
		StartClock();

		//Initialize table
		for(int i = 0; i < len1+1; i++ ){
			mat[i][0] = 0;
		}

		for(int j = 0; j < len2+1; j++){
			mat[0][j] = 0;
		}

		//Fill in table
		for( int i = 1; i < len1+1; i++ ){
			for( int j = 1; j < len2+1; j++ ){
				if( S.charAt(i-1) == T.charAt(j-1) ){
					mat[i][j] = mat[i-1][j-1] + 1;
				} else {
					if( mat[i][j-1] > mat[i-1][j] )
						mat[i][j] = mat[i][j-1];
					else
						mat[i][j] = mat[i-1][j];
				}
			}
		}


		//Find LCS
		cur = mat[len1][len2];
		char lcs[] = new char[cur];
		cur--;
		int x = len1;
		int y = len2;
		while( mat[x][y] != 0 ){
			if((mat[x-1][y] == mat[x][y]-1) &&
			   (mat[x][y-1] == mat[x][y]-1)){
				lcs[cur] = S.charAt(x-1);
				cur--;
				x--;
				y--;
			} else if( mat[x-1][y] >= mat[x][y-1] ){
				x--;
			} else {
				y--;
			}
		}

		//Print table
//		for( int i = 0; i < len1+1; i++ ){
//			for( int j = 0; j < len2+1; j++){
//				System.out.printf("%d ", mat[i][j]);
//			}
//			System.out.printf("\n");
//		}
//		System.out.println(lcs);

		//Stop the timer
		StopClock();

		return new String(lcs);
	}

}
