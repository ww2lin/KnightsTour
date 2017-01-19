Based on the chess piece passed to the board, the board will generate differnt outputs
#Knight -> knight's tour
Recursion/DFS implementation of the [Knight's tour](https://en.wikipedia.org/wiki/Knight's_tour)

Note: a 8x8 board will take a LONG time to process.

example solution for a 5x5 board:

~~~~
  1 12  3 18 21

  4 17 20 13  8

 11  2  7 22 19

 16  5 24  9 14

 25 10 15  6 23
~~~~

#Queen -> 8 queen problem
brute force implementation of trying to find a solution for the [N-Queen problem](https://en.wikipedia.org/wiki/Eight_queens_puzzle)
Note: the ordering of the queen matters in this implemtations.
For example the below two solution are counted as difference since the ordering is different:
~~~~
  0  0  2  0
  3  0  0  0
  0  0  0  1
  0  4  0  0
~~~~
~~~~
  0  0  2  0
  3  0  0  0
  0  0  0  4
  0  1  0  0
~~~~

