#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lib.h"

char* returnedString = NULL;

void connect()
{
    printf("Successfully connected!\n");
}

const char* getPermutations(char* word)
{

    int numberOfPermutations = 1;

    for (int i = 1; i <= strlen(word); i++)
    {
        numberOfPermutations  = numberOfPermutations * i;
    }

   // allocate memory
    returnedString = calloc(numberOfPermutations, sizeof(char) * (strlen(word) + 1)  + 1);

    if (returnedString == NULL)
    {
        printf("Ooops! There went something wrong with memory allocation!\n");
        exit(1);
    }

    permute(word, 0, strlen(word) - 1);

    return returnedString;
}

// Function from web: https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
/* Function to swap values at two pointers */
static void swap(char* x, char* y)
{
    char temp;
    temp = *x;
    *x = *y;
    *y = temp;
}

// Function from web: https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
/* Function to print permutations of string
   This function takes three parameters:
   1. String
   2. Starting index of the string
   3. Ending index of the string. */
static void permute(char* a, int l, int r)
{
    int i;
    if (l == r)
    {
//        printf("%s\n", a);
        char* newLine = calloc(1, sizeof(char));
        *newLine = '\n';
        strcat(returnedString, a);
        strcat(returnedString, newLine);
    }

    else {
        for (i = l; i <= r; i++) {
            swap((a + l), (a + i));
            permute(a, l + 1, r);
            swap((a + l), (a + i)); // backtrack
        }
    }
}
