package model.board;

import java.util.List;
import model.move.Position;

/**
 * Created by AlexLin on 1/17/17.
 */
public class GameBoard {
    private final int boardSizeRow;
    private final int boardSizeCol;
    private final ChessPiece chessPiece;

    public GameBoard(ChessPiece chessPiece) {
        this.boardSizeRow = chessPiece.getRowSize();
        this.boardSizeCol = chessPiece.getColSize();
        this.chessPiece = chessPiece;
    }

    public int[][] findPath(){
        int[][] gameboard = new int[boardSizeRow][boardSizeCol];
        return findPath(gameboard, new Position(0,0), 1);
    }

    public int[][] findPathStartingAt(Position position){
        int[][] gameboard = new int[boardSizeRow][boardSizeCol];
        return findPath(gameboard, position, 1);
    }

    private int[][] findPath(int[][] currentBoard, Position currentPosition, int depth) {
        currentBoard[currentPosition.row][currentPosition.col] = depth;

        if (isBoardFilled(currentBoard)) {
            // Base case: board is filled, we found a path
            return currentBoard;
        } else {
            // recursive case: Try DFS search on the board
            List<Position> nextMoves = chessPiece.getNextAvailableMoves(currentPosition);
            for (Position position : nextMoves) {
                // making sure its empty.
                if (currentBoard[position.row][position.col] == 0) {
                    int[][] nextBoard = findPath(currentBoard, position, depth + 1);
                    if (nextBoard != null) {
                        return nextBoard;
                    }
                }
            }
            // remove the placed piece, if we reached a dead end.
            currentBoard[currentPosition.row][currentPosition.col] = 0;
            return null;
        }

    }

    /**
     * board is filled when every element in the index [row, col] > 0
     * @param board
     * @return true if all the number in the matrix is greater than 0
     */
    private boolean isBoardFilled(int[][] board){
        for (int i = 0; i < boardSizeRow; ++i){
            for (int j = 0; j < boardSizeCol; ++j){
                if (board[i][j] <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard(int[][] board){
        if (board == null) {
            System.out.println("null board given");
            return;
        }
        for (int i = 0; i < boardSizeRow; ++i){
            String line = "";
            for (int j = 0; j < boardSizeCol; ++j) {
                System.out.printf("%1$3s",board[i][j]);
            }
            System.out.println();
            for (int k = 0; k < line.length(); ++k) {
                System.out.print('-');
            }
            System.out.println();
        }
    }
}
