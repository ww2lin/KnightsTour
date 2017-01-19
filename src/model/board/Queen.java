package model.board;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import model.move.Position;

public class Queen extends ChessPiece {

    private final int numberOfQueens;

    public Queen(int row, int col, int numberOfQueens) {
        super(row, col);
        this.numberOfQueens = numberOfQueens;
    }

    /**
     * generate the move possible from the queen
     */
    @Override
    protected Position[] getMovePool() {
        Set<Position> positions = new HashSet<>();
        // generate the row
        for (int i = -maxRow + 1; i < maxRow; ++i) {
            positions.add(new Position(i, 0));
        }

        // generate the cols
        for (int i = -maxCol + 1; i < maxCol; ++i) {
            positions.add(new Position(0, i));
        }

        // generate diagonal
        for (int i = -maxRow + 1, j = -maxCol +1; i < maxRow && j < maxCol; ++i, ++j){
            positions.add(new Position(i, j));
        }

        // generate reverse diagonal
        for (int i = -maxRow + 1, j = maxCol -1; i < maxRow && j > -maxCol; ++i, --j){
            positions.add(new Position(i, j));
        }
        return positions.toArray(new Position[positions.size()]);
    }

    /**
     * Check to see we have n queens on the board and they are in valid positions
     * @param board
     * @return
     */
    @Override
    public State getState(int[][] board) {
        // Idea here is simply loop through board
        // When we encounter a queen
        //  1. get all the moves that the current queen can make denote it queenMoves
        //  2. check if there exist a queen within the index from queenMoves
        //  3. if no queens are found AND number of queen seen so far equals numberOfQueens, then return true
        int queensFound = 0;
        for (int i = 0; i < maxRow; ++i){
            for (int j = 0; j < maxCol; ++j){
                if (board[i][j] > 0) {
                    ++queensFound;
                    Position currentPosition = new Position(i, j);
                    // found a queen, check all the possible moves it has
                    List<Position> queenMoves = super.getNextAvailableMoves(currentPosition);
                    // Validate all those positions.

                    for (Position position : queenMoves) {
                        // another queen found, this is a dead end
                        if (!currentPosition.equals(position) && board[position.row][position.col] > 0) {
                            return State.DEAD_END;
                        }
                    }

                    if (queensFound == numberOfQueens) {
                        return State.DONE;
                    }
                }
            }
        }
        // not all queens has been placed, recurse and keep trying.
        return State.RECURSE;
    }

    @Override
    public List<Position> getNextAvailableMoves(Position currentPosition) {
        // Since no queens can be placed under another queen's movement
        // We will have to exclude the moves that the current queen can make
        Set<Position> invalidMoves = new HashSet<>(super.getNextAvailableMoves(currentPosition));
        List<Position> nextMoves = new LinkedList<>();
        for (int i = 0; i < maxRow; ++i) {
            for (int j = 0; j < maxCol; ++j) {
                Position position = new Position(i, j);
                if (!invalidMoves.contains(position)){
                    nextMoves.add(position);
                }
            }
        }
        return nextMoves;
    }
}
