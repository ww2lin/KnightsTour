package model.board;

import java.util.ArrayList;
import java.util.List;
import model.move.Position;

public abstract class ChessPiece {

    // board size
    protected final int maxRow;
    protected final int maxCol;

    private final Position[] movePool;

    public ChessPiece(int row, int col) {
        maxRow = row;
        maxCol = col;
        movePool = getMovePool();
    }

    /**
     * Given a piece on the 0,0 position of the board,
     * This board will return all the position (can be invalid) the piece can make.
     */
    protected abstract Position[] getMovePool();


    public abstract State getState(int[][] board);

    /**
     * Get a list of available move set based on the current move
     * @param currentPosition
     * @return valid moves the current location can make.
     */
    public List<Position> getNextAvailableMoves(Position currentPosition){
        // The idea here is simply used the 0,0 position and offset everything by currentLocation.
        // Then filtering out the invalid indices.

        List<Position> nextMoves = new ArrayList<>(movePool.length);
        for (Position move: movePool) {
            Position offsettedPosition = Position.add(move, currentPosition);
            if (offsettedPosition.isValidPosition(maxRow, maxCol)) {
                nextMoves.add(offsettedPosition);
            }

        }
        return nextMoves;
    }

    public int getRowSize() {
        return maxRow;
    }

    public int getColSize() {
        return maxCol;
    }
}
