#include <stdio.h>
#include <math.h>

char str1[9]={'q','a', 'b', 'c', 'b', 'b', 'd', 'a', 'b'};
char str2[8]={'l','b', 'b', 'd', 'c', 'a', 'b', 'a'};

int i, j, counter = 0;

int mat[9][8];

char lcs[9];

void algoB(int m, int n, char *str1, char *str2){
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

/*char algoC( int m, int n, char *str1, char *str2 ){
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

int main (int argc, const char * argv[]) {
    
	
	
	for( i = 0; i < 9; i++ ){
		mat[i][0] = 0;
	}
	
	for( j = 0; j < 8; j++){
		mat[0][j] = 0;
	}
	
	//Algo A
	for( i = 1; i < 9; i++ ){
		for( j = 1; j < 8; j++ ){
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
	
	for( i = 0; i < 9; i++ ){
		for( j = 0; j < 8; j++){
			printf("%d ", mat[i][j]);
		}
		printf("\n");
	}//End of Algo A
	
	//Algo B
	//algoB(9, 8, str1, str2);
	
    return 0;
}
