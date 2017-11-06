#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#define MAX_TESTS 12
#define MAX_TEST_DECK 30
#define MAX_TEST_HAND 15

//This randomly tests Adventurer

int main() {

	  int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, 
	       sea_hag, tribute, smithy};

	  int i, j, n; 
          int players = 0, player = 0, seed = 0;
          int handPos = 0;
          int bonus = 0;
	  struct gameState state;

	  printf("Running Random Adventurer Test\n");


	  for (i = 0; i < MAX_TESTS; i++) {

	   players = rand() % 4;

	   seed = rand();		//pick random seed
		
	   initializeGame(players, k, seed, &state);	//initialize Gamestate 

	   //Initiate valid state variables
		  state.deckCount[player] = rand() % MAX_TEST_DECK; //Random deck size
		  state.discardCount[player] = rand() % MAX_TEST_DECK;
		  state.handCount[player] = rand() % MAX_TEST_HAND;


		  // Chance to make deck empty for coverage
		  if (seed % 3 == 0) {

			state.deckCount[player] = 0;
		  }
		  cardEffect(adventurer, 1, 1, 1, &state,handPos,&bonus);	
              	
	  }
	  

	  printf("Tests Complete\n");

	  return 0;
}
