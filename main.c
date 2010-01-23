#include <stdio.h>
#include <math.h>
#include <stdlib.h>

char *str1;
char *str2;

int len1 = 1, len2 = 1;

void dynamicProgramming(){
	int i, j, cur;
	int mat[len1][len2];
	char lcs[len1];
	
	//Init table
	for( i = 0; i < len1; i++ ){
		mat[i][0] = 0;
	}
	
	for( j = 0; j < len2; j++){
		mat[0][j] = 0;
	}
	
	//Fill in table
	for( i = 1; i < len1; i++ ){
		for( j = 1; j < len2; j++ ){
			if( str1[i] == str2[j] ){
				mat[i][j] = mat[i-1][j-1] + 1;
			} else {
				if( mat[i][j-1] > mat[i-1][j] )
					mat[i][j] = mat[i][j-1];
				else
					mat[i][j] = mat[i-1][j];
			}
		}
	}
	
	//Print table
	for( i = 0; i < len1; i++ ){
		for( j = 0; j < len2; j++){
			printf("%d ", mat[i][j]);
		}
		printf("\n");
	}
	
	//Find LCS
	cur = mat[len1-1][len2-1];
	lcs[cur] = '\0';
	cur--;
	i = len1-1;
	j = len2-1;
	while( mat[i][j] != 0 ){
		if((mat[i-1][j] == mat[i][j]-1) &&
		   (mat[i][j-1] == mat[i][j]-1)){
			lcs[cur] = str1[j];
			cur--;
			i--;
			j--;
		} else if( mat[i-1][j] >= mat[i][j-1] ){
			i--;
		} else {
			j--;
		}
	}
	
	printf("LCS: %s\n", lcs);
}

/*void algoB(int m, int n, char *str1, char *str2){
	for( j = 0; j < n; j++ ){
		mat[1][j] = 0;
	}
	
	for( i = 1; i < m; i++ ){
		for( j = 0; j < n; j++ ){
			mat[0][j] = mat[1][j];
		}
		
		for( j = 1; j < n; j++ ){
			if( str1[i] == str2[j] ){
				mat[1][j] = mat[0][j-1] + 1;
			} else {
				if( mat[1][j-1] > mat[0][j] )
					mat[1][j] = mat[1][j-1];
				else
					mat[1][j] = mat[0][j];
			}
		}
	}
	
	for( j = 0; j < n; j++ )
		printf("%d ", mat[1][j] );
}

char algoC( int m, int n, char *str1, char *str2 ){
 if( n == 0 ){
 return '';
 } else if( m == 1 ){
 for( j = 0; j <= n; j++ ){
 if( str1[1] == str2[j] ){
 return str1[1];
 }
 }
 return '';
 } else {
 i = floor( ((float)m)/2.0 );
 for( 
 }
 }*/

int main (int argc, char * argv[]) {
	
	int algo, counter = 0;
	
	//Check command line arguments
	if( argc != 4 ){
		//Print usage message
		fprintf(stderr, "Usage: LCS algorithm string1 string2\n");
		return -1;
	}
	
	//Get algorithm
	algo = atoi( argv[1] );
	
	//Set strings
	str1 = argv[2];
	str2 = argv[3];
	
	printf("%s\n", str1);
	printf("%s\n", str2);
	
	//Get length of strings
	//while( str1[counter] != '\0' )
		len1 = 9;
	printf("1\n");
	counter = 0;
	//while( str2[counter] != '\0' )
		len2 = 8;
	printf("2\n");
	//Go to given algorithm method
	switch( algo ){
		case 1:
			//naive recursive
			break;
		case 2:
			//recursive with memoization
			break;
		case 3:
			//dynamic programming
			dynamicProgramming();
			break;
		case 4:
			//Quadratic-time linear-space
			break;
		default:
			fprintf(stderr, "ERROR: Invalid Algorithm\n");
	}
	
	//Algo B
	//algoB(9, 8, str1, str2);
	
    return 0;
}
