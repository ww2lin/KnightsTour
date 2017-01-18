import model.board.GameBoard;
import model.board.Knight;
import model.move.Position;

public class Main {

    public static void main(String args[]){
        int row = 5;
        int col = 5;

        Knight knight = new Knight(row, col);
        GameBoard gameBoard = new GameBoard(knight);

        gameBoard.printBoard(gameBoard.findPathStartingAt(new Position(0,0)));

    }
}
