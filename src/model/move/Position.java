package model.move;

import java.util.Objects;

/**
 * Denote the position that is allowed for the particular piece.
 * With row and col index, which are 0 based.
 */
public class Position {
   public final int row;
   public final int col;

   public Position(int row, int col) {
      this.row = row;
      this.col = col;
   }

   /**
    * Check if the current position is valid -- namely within the board boundary
    * @return true if the position is within the board
    */
   public boolean isValidPosition(int rowSize, int colSize){
      return row >=0 && row < rowSize && col >= 0 && col < colSize;
   }

   public static Position add(Position p1, Position p2){
      return new Position(p1.row + p2.row, p1.col + p2.col);
   }

   @Override
   public int hashCode() {
      return Objects.hash(row, col);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null){
         return false;
      } else if (this == obj){
         return true;
      } else if (getClass() != obj.getClass()){
         return false;
      }
      Position position = (Position) obj;

      return row == position.row && col == position.col;
   }
}
