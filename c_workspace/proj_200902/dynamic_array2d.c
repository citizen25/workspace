#include <stdio.h>
#include <stdlib.h>

int main(void){
	int x=1;
	int row, col;
	int **dbptr;

	printf("row: ");
	scanf("%d", &row);
	printf("col: ");
	scanf("%d", &col);

	dbptr = (int **)malloc(sizeof(int *)*row);
	for(int i=0; i<row; i++){
		dbptr[i] = (int *)malloc(sizeof(int)*col);
	}

	for(int i=0; i<row; i++){
		dbptr[i] = (int *)malloc(sizeof(int)*col);
	}

	for(int i=0; i<row; i++){
		for(int j=0; j<col; j++){
			printf("%dth row & %dth col : ", i+1, j+1);
			scanf("%d", &dbptr[i][j]);
			printf("\n");
		}
		printf("\n");
	}

	for(int i=0; i<row; i++){
		for(int j=0; j<col; j++){
			printf("%d ", dbptr[i][j]);		
		
		}
		printf("\n");
	}


	for(int i=0; i<row; i++){
		free(dbptr[i]);
	}
	free(dbptr);


	return 0;
	
}
