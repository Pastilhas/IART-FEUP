import java.util.ArrayList;

/**
 * pivit
 */
public class Pivit {

  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    Pivit game = new Pivit(size);
    game.debugBoard();
    System.out.println("");
    if (game.movePiece(game.getPiece(0, 1), 3)) {
      game.debugBoard();
      game.printBoard();
    }
    System.out.println("");
    if (game.movePiece(game.getPiece(3, 1), 1)) {
      game.debugBoard();
      game.printBoard();
    }
    System.out.println("");
    if (game.movePiece(game.getPiece(3, 0), 1)) {
      game.debugBoard();
      game.printBoard();
    }
    System.out.println("");
    if (game.movePiece(game.getPiece(3, 0), 1)) {
      game.debugBoard();
      game.printBoard();
    }
    System.out.println("");
    if (game.movePiece(game.getPiece(3, 1), -1)) {
      game.debugBoard();
      game.printBoard();
    }
    System.out.println("");
    if (game.movePiece(game.getPiece(2, 0), 1)) {
      game.debugBoard();
      game.printBoard();
    }
    System.out.println("");
    if (game.movePiece(game.getPiece(2, 1), 1)) {
      game.debugBoard();
      game.printBoard();
    }
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
      if (piece.x == x && piece.y == y)
        return piece;
    }
    return null;
  }

  private void generateBoard(int size) {
    if (size == 6) {
      board.add(new Piece(0, 1, 'A', 'x', 'm'));
      board.add(new Piece(0, 2, 'B', 'x', 'm'));
      board.add(new Piece(0, 3, 'A', 'x', 'm'));
      board.add(new Piece(0, 4, 'B', 'x', 'm'));

      board.add(new Piece(1, 0, 'B', 'y', 'm'));
      board.add(new Piece(2, 0, 'A', 'y', 'm'));
      board.add(new Piece(3, 0, 'B', 'y', 'm'));
      board.add(new Piece(4, 0, 'A', 'y', 'm'));

      board.add(new Piece(5, 1, 'A', 'x', 'm'));
      board.add(new Piece(5, 2, 'B', 'x', 'm'));
      board.add(new Piece(5, 3, 'A', 'x', 'm'));
      board.add(new Piece(5, 4, 'B', 'x', 'm'));

      board.add(new Piece(1, 5, 'B', 'y', 'm'));
      board.add(new Piece(2, 5, 'A', 'y', 'm'));
      board.add(new Piece(3, 5, 'B', 'y', 'm'));
      board.add(new Piece(4, 5, 'A', 'y', 'm'));

      return;
    }

    if (size == 8) {
      board.add(new Piece(0, 1, 'A', 'x', 'm'));
      board.add(new Piece(0, 2, 'B', 'x', 'm'));
      board.add(new Piece(0, 3, 'A', 'x', 'm'));
      board.add(new Piece(0, 4, 'A', 'x', 'm'));
      board.add(new Piece(0, 5, 'B', 'x', 'm'));
      board.add(new Piece(0, 6, 'A', 'x', 'm'));

      board.add(new Piece(1, 0, 'B', 'y', 'm'));
      board.add(new Piece(2, 0, 'A', 'y', 'm'));
      board.add(new Piece(3, 0, 'B', 'y', 'm'));
      board.add(new Piece(4, 0, 'B', 'y', 'm'));
      board.add(new Piece(5, 0, 'A', 'y', 'm'));
      board.add(new Piece(6, 0, 'B', 'y', 'm'));

      board.add(new Piece(7, 1, 'A', 'x', 'm'));
      board.add(new Piece(7, 2, 'B', 'x', 'm'));
      board.add(new Piece(7, 3, 'A', 'x', 'm'));
      board.add(new Piece(7, 4, 'A', 'x', 'm'));
      board.add(new Piece(7, 5, 'B', 'x', 'm'));
      board.add(new Piece(7, 6, 'A', 'x', 'm'));

      board.add(new Piece(1, 7, 'B', 'y', 'm'));
      board.add(new Piece(2, 7, 'A', 'y', 'm'));
      board.add(new Piece(3, 7, 'B', 'y', 'm'));
      board.add(new Piece(4, 7, 'B', 'y', 'm'));
      board.add(new Piece(5, 7, 'A', 'y', 'm'));
      board.add(new Piece(6, 7, 'B', 'y', 'm'));

      return;
    }
  }

  private boolean movePiece(Piece piece, int distance) {

    if (piece == null)
      return false;

    if (piece.direction == 'x') {
      System.out.println("Moving piece from player " + piece.player);
      System.out.println("From position [" + piece.x + "," + piece.y + "]");
      System.out.println("To position [" + (piece.x + distance) + "," + piece.y + "]");

      if ((piece.x + distance) < 0 || (piece.x + distance) > size) {
        System.out.println("Invalid movement");
        return false;
      }

      else if (piece.type == 'm' && Math.abs(distance) % 2 == 0) {
        System.out.println("Minions can only move in odd distances");
        return false;
      }

      else {
        int inc = (int) Math.signum(distance);
        int i = piece.x + inc;
        while (Math.abs(i - (piece.x + distance)) > 1) {
          if (getPiece(i, piece.y) != null) {
            System.out.println(getPiece(i, piece.y));
            return false;
          }
          i += inc;
        }
      }

      Piece destPiece = getPiece(piece.x + distance, piece.y);
      if (destPiece != null) {
        if (destPiece.player == piece.player)
          return false;
        else {
          removePiece(destPiece);
          debugBoard();
        }
      }
      piece.x += distance;
    }

    else if (piece.direction == 'y') {
      System.out.println("Moving piece from player " + piece.player);
      System.out.println("From position [" + piece.x + "," + piece.y + "]");
      System.out.println("To position [" + piece.x + "," + (piece.y + distance) + "]");

      if ((piece.y + distance) < 0 || (piece.y + distance) > size) {
        System.out.println("Invalid movement");
        return false;
      } else if (piece.type == 'm' && Math.abs(distance) % 2 == 0) {
        System.out.println("Minions can only move in odd distances");
        return false;
      } else {
        int inc = (int) Math.signum(distance);
        int i = piece.y + inc;
        while (Math.abs(i - (piece.y + distance)) > 1) {
          if (getPiece(piece.x, i) != null) {
            getPiece(i, piece.y).debug();
            return false;
          }
          i += inc;
        }
      }
      Piece destPiece = getPiece(piece.x, piece.y + distance);
      if (destPiece != null) {
        destPiece.debug();
        if (destPiece.player == piece.player) {
          return false;
        } else {
          removePiece(destPiece);
          debugBoard();
        }
      }
      piece.y += distance;

    }
    rotatePiece(piece);
    return true;
  }

private boolean rotatePiece(Piece piece){
  if(piece.direction == 'x')
    piece.direction = 'y';
  else if (piece.direction == 'y')
    piece.direction = 'x';

    return true;
  }

  private void removePiece(Piece p) {
    board.remove(p);
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
