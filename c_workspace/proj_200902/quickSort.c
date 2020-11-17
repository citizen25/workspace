#include <stdio.h>

int data[10] = {4, 1, 2, 3, 9, 7, 8, 6, 10, 5};

void quick_sort(int *data, int start, int end){

	if(start >= end){
		// when the element is only one
		return;
	}

	int pivot = start;
	int i = pivot + 1; // left start point
	int j = end; // right start point
	int temp;

	while(i <= j){
		// repeat until pointers cross
		while(i < end && data[i] <= data[pivot]){
			i++;	
		}
		while(j > start && data[j] >= data[pivot]){
			j--;
		}

		if(i > j){
			// cross
			temp = data[j];
			data[j] = data[pivot];
			data[pivot] = temp;
		}else{
			// swap ith and jth
			temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}
	}

	// split calculation
	quick_sort(data, start, j-1);
	quick_sort(data, j+1, end);
}

int main(void){
	quick_sort(data, 0, 9);

	// check the result
	for(int i=0; i<10; i++){
		printf("%d", data[i]);
	}	

	return 0;
}
