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

  public Pivit(int size) {
    if(size != 6 && size != 8) System.exit(1);
    generateBoard(size);
  }

  private void generateBoard(int size) {
    board.add(new Piece(1,1,"A","x","minion"));
  }

  private void movePiece() {

  }

  private void removePiece() {

  }

  private void printBoard() {

  }

  private void debugBoard() {
    for(Piece p : this.board) {
      p.debug();
    }
  }

}
