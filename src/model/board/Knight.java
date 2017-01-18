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
}
