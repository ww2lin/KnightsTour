package model.board;

import java.util.LinkedList;
import java.util.List;
import model.move.Position;

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
        for (int i = 0; i < chessPiece.maxRow; ++i) {
            for (int j = 0; j < chessPiece.maxCol; ++j) {
                int[][] ans = findPath(new int[boardSizeRow][boardSizeCol], new Position(i,j), 1);
                if (ans != null) {
                    return ans;
                }
            }
        }
        return null;
    }

    public List<int[][]> findAllPaths(){
        List<int[][]> paths = new LinkedList<>();
        for (int i = 0; i < chessPiece.maxRow; ++i) {
            for (int j = 0; j < chessPiece.maxCol; ++j) {
                int[][] ans = findPath(new int[boardSizeRow][boardSizeCol], new Position(i,j), 1);
                if (ans != null) {
                    paths.add(ans);
                }
            }
        }
        return paths;
    }


    public int[][] findPathStartingAt(Position position){
        int[][] gameboard = new int[boardSizeRow][boardSizeCol];
        return findPath(gameboard, position, 1);
    }

    private int[][] findPath(int[][] currentBoard, Position currentPosition, int depth) {
        currentBoard[currentPosition.row][currentPosition.col] = depth;
        State state = chessPiece.getState(currentBoard);
        if (state == State.DONE) {
            // Base case: board is filled, we found a path
            return currentBoard;
        } else if (state == State.RECURSE){
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
        }
        // remove the placed piece, if we reached a dead end.
        currentBoard[currentPosition.row][currentPosition.col] = 0;
        return null;
    }

    public void printBoard(int[][] board){
        if (board == null) {
            System.out.println("N/A no solution found.");
            return;
        }
        for (int i = 0; i < boardSizeRow; ++i){
            for (int j = 0; j < boardSizeCol; ++j) {
                System.out.printf("%1$3s",board[i][j]);
            }
            System.out.println();
        }
    }

    public void printBoard(List<int[][]> boards){
        for (int[][] board : boards){
            printBoard(board);
            System.out.println();
        }
    }

}
