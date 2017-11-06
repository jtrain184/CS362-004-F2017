#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

#define MAX_TESTS 12
#define MAX_TEST_DECK 50
#define MAX_TEST_HAND 15

//This randomly tests Adventurer

int main() {

	  int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, 
	       sea_hag, tribute, smithy};

	  int i, j, n, players, player, handCount, deckCount, seed, address;
	  //struct gameState state;
	  struct gameState state;
	  struct gameState stat;
	  struct gameState sta;

	  printf("Running Random Adventurer Test\n");

	  /*
										--- Author's Note ---
	  So, I had problems running out of memory when I used the same gameState variable more than 12 times, and
	  I honestly don't know why. I momentarily solved this problem by adding more for loops and creating more gamestates;
	  I was still able to get decent coverage, though not up to the amount of tests I originally had in mind.

	  */

	  for (i = 0; i < MAX_TESTS; i++) {

	   players = rand() % 4;

	   seed = rand();		//pick random seed
		
	   initializeGame(players, k, seed, &state);	//initialize Gamestate 

	   //Initiate valid state variables
		  state.deckCount[player] = rand() % MAX_TEST_DECK; //Random deck size
		  state.discardCount[player] = rand() % MAX_TEST_DECK;
		  state.handCount[player] = rand() % MAX_TEST_HAND;


		  //Copy state variables
		  handCount = state.handCount[player];
		  deckCount = state.deckCount[player];

		  //1 in 3 chance of making empty deck for coverage
		  if (seed % 3 == 0) {

			state.deckCount[player] = 0;
		  }
		  cardEffect(adventurer, 1, 1, 1, &state);		//Run adventurer card
	  }
	  

	  printf("Tests Complete\n");

	  return 0;
}