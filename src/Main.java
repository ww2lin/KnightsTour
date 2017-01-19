import model.board.GameBoard;
import model.board.Knight;
import model.board.Queen;

public class Main {

    public static void main(String args[]){
        int row = 4;
        int col = 4;

        Knight knight = new Knight(row, col);
        Queen queen = new Queen(row, col, 4);
        GameBoard gameBoard = new GameBoard(queen);

        gameBoard.printBoard(gameBoard.findAllPaths());

    }
}
