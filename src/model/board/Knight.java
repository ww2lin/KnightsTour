package model.board;

import model.move.Position;

/**
 * Created by AlexLin on 1/17/17.
 */
public class Knight extends ChessPiece {

    public Knight(int row, int col) {
        super(row, col);
    }

    @Override
    protected Position[] getMovePool() {
        Position[] movePool = {
            new Position(-2, -1),
            new Position(-1, -2),

            new Position(-2, 1),
            new Position(-1, 2),

            new Position(2, -1),
            new Position(2, 1),

            new Position(1, -2),
            new Position(1, 2)
        };
        return movePool;
    }

    /**
     * board is filled when every element in the index [row, col] > 0
     * @param board
     * @return true if all the number in the matrix is greater than 0
     */
    public boolean terminated(int[][] board) {
        for (int i = 0; i < maxRow; ++i){
            for (int j = 0; j < maxCol; ++j){
                if (board[i][j] <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public State getState(int[][] board) {
        return terminated(board) ? State.DONE : State.RECURSE;
    }

}
