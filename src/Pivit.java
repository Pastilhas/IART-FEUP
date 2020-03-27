import java.util.ArrayList;

/**
 * pivit
 */
public class Pivit {

  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    Pivit game = new Pivit(size);
    game.debugBoard();
  }

  ArrayList<Piece> board = new ArrayList<>();
  int size;

  public Pivit(int size) {
    if (size != 6 && size != 8)
      System.exit(1);
    this.size = size;
    generateBoard(size);
    printBoard();
  }

  private Piece getPiece(int x, int y) {
    for (Piece piece : board) {
      if(piece.x == x && piece.y == y)
        return piece;
    }

    // piece not found
    return null;
  }

  private void generateBoard(int size) {
    if (size == 6) {
      board.add(new Piece(0, 1, "A", "x", "minion"));
      board.add(new Piece(0, 2, "B", "x", "minion"));
      board.add(new Piece(0, 3, "A", "x", "minion"));
      board.add(new Piece(0, 4, "B", "x", "minion"));

      board.add(new Piece(1, 0, "B", "y", "minion"));
      board.add(new Piece(2, 0, "A", "y", "minion"));
      board.add(new Piece(3, 0, "B", "y", "minion"));
      board.add(new Piece(4, 0, "A", "y", "minion"));

      board.add(new Piece(5, 1, "A", "x", "minion"));
      board.add(new Piece(5, 2, "B", "x", "minion"));
      board.add(new Piece(5, 3, "A", "x", "minion"));
      board.add(new Piece(5, 4, "B", "x", "minion"));

      board.add(new Piece(1, 5, "B", "y", "minion"));
      board.add(new Piece(2, 5, "A", "y", "minion"));
      board.add(new Piece(3, 5, "B", "y", "minion"));
      board.add(new Piece(4, 5, "A", "y", "minion"));

      return;
    }

    if (size == 8) {
      board.add(new Piece(0, 1, "A", "x", "minion"));
      board.add(new Piece(0, 2, "B", "x", "minion"));
      board.add(new Piece(0, 3, "A", "x", "minion"));
      board.add(new Piece(0, 4, "A", "x", "minion"));
      board.add(new Piece(0, 5, "B", "x", "minion"));
      board.add(new Piece(0, 6, "A", "x", "minion"));

      board.add(new Piece(1, 0, "B", "y", "minion"));
      board.add(new Piece(2, 0, "A", "y", "minion"));
      board.add(new Piece(3, 0, "B", "y", "minion"));
      board.add(new Piece(4, 0, "B", "y", "minion"));
      board.add(new Piece(5, 0, "A", "y", "minion"));
      board.add(new Piece(6, 0, "B", "y", "minion"));

      board.add(new Piece(7, 1, "A", "x", "minion"));
      board.add(new Piece(7, 2, "B", "x", "minion"));
      board.add(new Piece(7, 3, "A", "x", "minion"));
      board.add(new Piece(7, 4, "A", "x", "minion"));
      board.add(new Piece(7, 5, "B", "x", "minion"));
      board.add(new Piece(7, 6, "A", "x", "minion"));

      board.add(new Piece(1, 7, "B", "y", "minion"));
      board.add(new Piece(2, 7, "A", "y", "minion"));
      board.add(new Piece(3, 7, "B", "y", "minion"));
      board.add(new Piece(4, 7, "B", "y", "minion"));
      board.add(new Piece(5, 7, "A", "y", "minion"));
      board.add(new Piece(6, 7, "B", "y", "minion"));

      return;
    }
  }

  private boolean movePiece(Piece piece, int distance ) {
    
    System.out.println("Moving piece from player " + piece.player);
    System.out.println("From position [" + piece.x + ","+ piece.y +"]");
    System.out.println("To position []");
  
    if (piece == null){
      System.out.println("That piece doesn't exist");
      return false;
    }
  
    if (piece.direction == "x"){
      if (piece.x+distance < 0 || (piece.x+distance) < size)
        return false;
  
      else if (piece.type == "minion" && Math.abs(distance)%2 == 0){
        System.out.println("Minions can only move in odd distances");
        return false;
      }
  
      else{
        int inc = (int) Math.signum(distance);
        int i = piece.x + inc;
        while (Math.abs(i - distance) > 1){
          if (getPiece(board,i,piece.y) != null){
            System.out.println(getPiece(board,i,piece.y);
            return false;
          }
        i += inc;
        }
      }

      Piece destPiece = getPiece(board,piece.x+distance,piece.y);
        if (destPiece != null){
          if (destPiece.player == piece.player)
            return false;
          else{
            removePiece(destPiece);
            debugBoard(board);
          }
        }
        piece.x += distance;
        return true;
    }
    
    else if (piece.direction == "y"){

      if (piece.y+distance < 0 || (piece.y+distance) < size)
        return false;
  
      else if (piece.type == "minion" && Math.abs(distance)%2 == 0){
        System.out.println("Minions can only move in odd distances");
        return false;
      }

      else{
        int inc = (int) Math.signum(distance);
        int i = piece.x + inc;
        while (Math.abs(i - distance) > 1){
          if (getPiece(board,piece.x,i) != null){
            System.out.println(getPiece(board,piece.x, i));
            return false;
          }
        i += inc;
        }
      }

    Piece destPiece = getPiece(board,piece.x,distance);
    if (destPiece != null){
      if (destPiece.player == piece.player)
      return false;
      else{
        removePiece(piece.x,distance);
        debugBoard(board);
      }
    }
      
    piece.y += distance; 
    return true;
    }
 }

  // return;

  private void removePiece() {

  }

  private void printBoard() {
    String separatorLine = "+---+---+---+---+---+---+";
    String separatorLeft = "| ";
    String separatorRight = " ";
    if (size == 8) {
      separatorLine += "---+---+";
    }

    for (int y = 0; y < size; y++) {
      System.out.println(separatorLine);
      String line = "";
      for (int x = 0; x < size; x++) {
        line += separatorLeft;
        Piece p = getPiece(x, y);
        if (p == null) {
          line += " ";
        } else {
          line += p.getChar();
        }
        line += separatorRight;
      }
      line += separatorLeft;
      System.out.println(line);
    }
    System.out.println(separatorLine);
  }

  private void debugBoard() {
    for (Piece p : this.board) {
      p.debug();
    }
  }

}
