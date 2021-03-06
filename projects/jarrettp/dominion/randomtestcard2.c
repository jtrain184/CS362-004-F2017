#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <time.h>
#include "rngs.h"

#define DEBUG 0


int random_number(int min_num, int max_num){
    int result=0,low_num=0,hi_num=0;
    if(min_num<max_num)
    {
        low_num=min_num;
        hi_num=max_num+1; // this is done to include max_num in output.
    }else{
        low_num=max_num+1;// this is done to include max_num in output.
        hi_num=min_num;
    }
    srand(time(NULL));
    result = (rand()%(hi_num-low_num))+low_num;
    return result;
}

int main(int argc, char const *argv[]){
	
	struct gameState game;

	int i, j;
	int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, sea_hag, tribute, smithy};
	int myCard;
	int currentplayer = 0;
	int cardsInHand = 0;	

	//village specific variables
	int handpos = 0, choice1 = 0, choice2 = 0, choice3 = 0, bonus = 0;


	for (i = 0; i < random_number(0, 100); i ++){ //Loops a random number of times <100
		initializeGame(random_number(2,4), k, random_number(1, 9), &game); //initializes a game with a random seed
															//between 1-9

		for (j = 0; j < 10000; j++){ // run 10000 times with random variables in this game state
			currentplayer = game.whoseTurn;
			cardsInHand = game.handCount[currentplayer];  //before smithy effect
			cardEffect(village, choice1, choice2, choice3, &game, handpos, &bonus);
			

			if(game.handCount[currentplayer]  != cardsInHand + 1)  //Did we draw one card?
				printf("Error: village did not properly draw one card. Random Game[%d] iteration[%d]\n", i, j);
			    
			
			game.outpostPlayed = 0;
  			game.phase = 0;
			game.numActions = 1;
			game.numBuys = 1;
			game.playedCardCount = 0;
			game.whoseTurn = random_number(2, game.numPlayers);
			game.handCount[game.whoseTurn] = 0;
		}
	}

	return 0;
}

