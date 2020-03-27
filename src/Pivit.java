import java.util.ArrayList;

/**
 * pivit
 */
public class Pivit {


  public static void main(String[] args) {
    size = Integer.parseInt(args[0]);
    Pivit game = new Pivit(size);
    game.debugBoard();
  }

  int size;
  ArrayList<Piece> board = new ArrayList<>();

  public Pivit(int size) {
    if (size != 6 && size != 8)
      System.exit(1);
    generateBoard(size);
  }

  private void generateBoard(int size) {
    board.add(new Piece(1, 1, "A", "x", "minion"));
  }

  private boolean movePiece(Piece piece, int distance ) {
    
    System.out.println("Moving piece from player " + piece.player);
    System.out.println("From position []");
    System.out.println("To position []");

    if (piece == null){
      System.out.println("That piece doesn't exist");
      return false;
    }

  if (piece.direction == "x"){
    if (piece.x+distance < 0)
      return false;

    if (piece.x+distance < this.size)
      return false;
    if (piece.type == "minion" && Math.abs(distance)%2 == 0)
      System.out.println("Minions can only move in odd distances");
      return false;

    // else
    //   inc = distance-piece.x;
    //   i = piece.x + inc;

    //   while (Math.abs(i - dx) > 1)
    //     if getPiece(self.board,i,piece.y) != None:
    //       print(getPiece(self.board,i,piece.y))
    //       return false;
    //     i += inc

    //   destPiece = getPiece(self.board,dx,piece.y)
    //   if destPiece != None:
    //     if destPiece.player == piece.player:
    //       return false;
    //     else:
    //       self.removePiece(dx,piece.y)
    //       debugBoard(self.board)
    //   piece.x = dx
    //   return true;
    // return;
  }

  else if (piece.direction == 'y')
    return;

  }

  private void removePiece() {

  }

  private void printBoard() {

  }

  private void debugBoard() {
    for (Piece p : this.board) {
      p.debug();
    }
  }

}
