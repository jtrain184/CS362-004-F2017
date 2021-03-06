CFLAGS = -Wall -fpic -coverage -lm

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)
#To run playdom you need to entere: ./playdom <any integer number> like ./playdom 10*/
testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

runtests: testDrawCard 
	./testDrawCard &> unittestresult.out
	gcov dominion.c >> unittestresult.out
	cat dominion.c.gcov >> unittestresult.out

unittestresults.out: cardtest4.c dominion.c
	echo "Result for running smithy tests:" > unittestresults.out
	echo "cardtest4.c:" >> unittestresults.out
	gcc -o cardtest4 -g  cardtest4.c dominion.o rngs.o $(CFLAGS)
	./cardtest4  >> unittestresults.out
	gcov -b dominion.c >> unittestresults.out

	echo "Result for running adventurer tests:" >> unittestresults.out
	echo "cardtest1.c:" >> unittestresults.out
	gcc -o cardtest1 -g  cardtest1.c dominion.o rngs.o $(CFLAGS)
	./cardtest1  >> unittestresults.out
	gcov -b dominion.c >> unittestresults.out

	echo "Result for running village tests:" >> unittestresults.out
	echo "cardtest2.c:" >> unittestresults.out
	gcc -o cardtest2 -g  cardtest2.c dominion.o rngs.o $(CFLAGS)
	./cardtest2  >> unittestresults.out
	gcov -b dominion.c >> unittestresults.out

	echo "Result for running Great Hall tests:" >> unittestresults.out
	echo "cardtest3.c:" >> unittestresults.out
	gcc -o cardtest3 -g  cardtest3.c dominion.o rngs.o $(CFLAGS)
	./cardtest3  >> unittestresults.out
	gcov -b dominion.c >> unittestresults.out

adventurer:
	gcc -fprofile-arcs -ftest-coverage -Wall -std=c99 dominion.c randomtestadventurer.c rngs.c -o randomtestadventurer -lm -g

runAdventurer: adventurer
	./randomtestadventurer 
	gcov -b dominion.c 

randomtestcard1: randomtestcard1.c dominion.o
	gcc -o randomtestcard1 -g randomtestcard1.c dominion.o rngs.o $(CFLAGS)
	./randomtestcard1
	gcov -b dominion.c

randomtestcard2: randomtestcard2.c dominion.o
	gcc -o randomtestcard2 -g randomtestcard2.c dominion.o rngs.o $(CFLAGS)
	./randomtestcard2
	gcov -b dominion.c

player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

all: playdom player testDrawCard testBuyCard badTestDrawCard

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe 
