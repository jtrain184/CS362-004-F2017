/*
 * cardtest1.c
 *
 
 */

/*
 * Include the following lines in your makefile:
 *
 * cardtest4: cardtest4.c dominion.o rngs.o
 *      gcc -o cardtest1 -g  cardtest4.c dominion.o rngs.o $(CFLAGS)
 */


#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>

#define TESTCARD "adventurer"

int main() {
    int newCards = 2;
    int discarded = 0;
    int xtraCoins = 0;
    int shuffledCards = 0;

    int i, j, m;
    int handpos = 0, choice1 = 0, choice2 = 0, choice3 = 0, bonus = 0;
    int remove1, remove2;
    int seed = 40000;
    int numPlayers = 2;
    int thisPlayer = 0;
	struct gameState G, testG;
	int k[10] = {smithy, embargo, village, minion, mine, cutpurse,
			sea_hag, tribute, steward, council_room};

	// initialize a game state and player cards
	initializeGame(numPlayers, k, seed, &G);

	printf("----------------- Testing Card: %s ----------------\n", TESTCARD);

	// ----------- TEST 1: Add two cards (should be treasure) --------------
	printf("TEST 1: Adventurer card draw (expect 2 new cards)\n");

	// copy the game state to a test case
	memcpy(&testG, &G, sizeof(struct gameState));
	cardEffect(adventurer, choice1, choice2, choice3, &testG, handpos, &bonus);

	xtraCoins = 0;
	printf("hand count = %d, expected = %d\n", testG.handCount[thisPlayer], G.handCount[thisPlayer] + newCards - discarded);
	printf("deck count = %d, expected = %d\n", testG.deckCount[thisPlayer], G.deckCount[thisPlayer] - newCards + shuffledCards);
	printf("coins = %d, expected = %d\n", testG.coins, G.coins + xtraCoins);

		// ----------- TEST 2: Add two cards (should be treasure) --------------
	printf("TEST 2: Adventurer card effect - expect to see two new treasure cards\n");
    printf("Hand before adventurer: %d, %d, %d, %d, %d, %d, %d\n",G.hand[0][0], G.hand[0][1], G.hand[0][2], G.hand[0][3], G.hand[0][4], G.hand[0][5], G.hand[0][6]);
    printf("Look for an additional two treasure cards of any combination.(Copper = 4, silver = 5, gold = 6.)\n");
    printf("Hand after adventurer: %d, %d, %d, %d, %d, %d, %d\n",testG.hand[0][0], testG.hand[0][1], testG.hand[0][2], testG.hand[0][3], testG.hand[0][4], testG.hand[0][5], testG.hand[0][6]);


	printf("\n >>>>> SUCCESS: Testing complete %s <<<<<\n\n", TESTCARD);


	return 0;
}


