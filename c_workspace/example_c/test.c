#include <stdio.h>
#include <stdlib.h>

int main(void){

    int all_students[30];
    int students_submitted[28];

    for (int i=0; i<sizeof(all_students)/sizeof(all_students[0]); i++){
        all_students[i] = i+1;
    }

    for (int i=0; i<sizeof(students_submitted)/sizeof(students_submitted[0]); i++){
        scanf("%d", students_submitted[i]);
    }

    for (int i=0; i<sizeof(students_submitted)/sizeof(students_submitted[0]); i++){
        
    }

    return 0;
}

quick_sort(