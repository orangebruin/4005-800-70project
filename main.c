#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
#include "timing.h"

#define DEFAULT_TIME 1000

/* Define Modes */
#define MAX_STRING 1
#define TIMING 0

CPUDEFS

char *str1;
char *str2;
int recur_count;
int len1, len2;
void benchmark( double (*algo)(), int runs );
void FindMaxString( double (*algo)(), double maxTime, char* character_set, int seed );
char* generate_string( char* character_set, int length, int seed);


double dynamicProgramming(){
	double t0, t1;
	
	int i, j, cur;
	int mat[len1+1][len2+1];
	char lcs[len1];
	t0=CPUTIME;
	//Init table
	for( i = 0; i < len1+1; i++ ){
		mat[i][0] = 0;
	}
	
	for( j = 0; j < len2+1; j++){
		mat[0][j] = 0;
	}
	
	//Fill in table
	for( i = 1; i < len1+1; i++ ){
		for( j = 1; j < len2+1; j++ ){
			if( str1[i-1] == str2[j-1] ){
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
	lcs[cur] = '\0';
	cur--;
	i = len1;
	j = len2;
	while( mat[i][j] != 0 ){
		if((mat[i-1][j] == mat[i][j]-1) &&
		   (mat[i][j-1] == mat[i][j]-1)){
			lcs[cur] = str1[i-1];
			cur--;
			i--;
			j--;
		} else if( mat[i-1][j] >= mat[i][j-1] ){
			i--;
		} else {
			j--;
		}
	}
	t1=CPUTIME;
	//Print table
#ifdef VERBOSE	
	for( i = 0; i < len1+1; i++ ){
		for( j = 0; j < len2+1; j++){
			printf("%d ", mat[i][j]);
		}
		printf("\n");
	}
	printf("LCS: %s\n", lcs);
#endif
	return t1-t0;
}


int main (int argc, char * argv[]) {
	recur_count=0;
	int algo;
	double maxTime=0;
	int mode = 0;
	
	
	//Check command line arguments
	if( argc != 4 && argc != 5 ){
		//Print usage message
		fprintf(stderr, "Usage: LCS algorithm string1 string2 or \n");
		fprintf(stderr, "Usage: LCS algorithm character_set seed time");
		return -1;
	}
	
	if( argc == 5 ){
		maxTime = atof(argv[4]); 
		mode = MAX_STRING;
	}else{
		mode = TIMING;
		//Set strings
		str1 = argv[2];
		str2 = argv[3];
		
		//Get length of strings
		len1 = strlen(str1);
		len2 = strlen(str2);
	}
	
	
	//Get algorithm
	algo = atoi( argv[1] );
	
	//Go to given algorithm method
	switch( algo ){
		case 1:
			//naive recursive
			if( mode == MAX_STRING ){
			}else{
			}
			break;
		case 2:
			//recursive with memoization
			//dynamic programming
			if( mode == MAX_STRING ){
			}else{
			}
			
			break;
		case 3:
			//dynamic programming
			if( mode == MAX_STRING ){
				FindMaxString(dynamicProgramming, atof(argv[4]), argv[2], atoi(argv[3]) );
			}else{
				benchmark(dynamicProgramming, 10);
			}
			
			break;
		case 4:
			//Quadratic-time linear-space
			if( mode == MAX_STRING ){
			}else{
			}
			break;
		default:
			//Invalid state
			fprintf(stderr, "ERROR: Invalid Algorithm\n");
	}
	
	return 0;
}

void benchmark( double (*algo)(), int runs ){
	int i=0;
	double time=0;
	for( i=0; i < runs; i++){
		time += algo();
	}
	printf("Total Time: %f Recursive Calls: %d Average Time: %f\n", time, recur_count, (time/runs));
	
}

void FindMaxString( double (*algo)(), double maxTime, char* character_set, int seed ){
	double time=0;
	int length = 2;
	do{
		str1 = generate_string(character_set, length, seed);
		str2 = generate_string(character_set, length, seed);
		len1 = length;
		len2 = length;
		time = algo();
		free( str1 );
		free( str2 );
		length++;
		printf("Length: %d", length);
	}while( time < maxTime );
	printf("Strlength: %d time: %f", length, time);	
}

char* generate_string( char* character_set, int length, int seed){
	char* str = malloc( length );
	int i=0;
	int setLength = strlen( character_set );
	if( str == NULL ){
		fprintf(stderr, "Error unable to allocate memory of length: %d", length);
		exit(1);
	}
	srand ( seed );
	for( i=0; i < length; i++){
		str[i] = character_set[rand() % setLength]; 
	}
	return str;
}


