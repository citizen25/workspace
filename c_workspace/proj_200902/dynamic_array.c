#include <stdio.h>
#include <stdlib.h>

int main(void){

	int size;
	printf("size of array : ");
	scanf("%d", &size);
	int *pa = (int *)malloc(sizeof(int)*size);

	for(int i = size; i != 0; i--){
		printf("%dth number of array : ", size-i+1);
		scanf("%d", &pa[i]);
	}

	for(int i = size; i != 0; i--){
		printf("%d ", pa[i]);
	}

	free(pa);

	return 0;
}
