import java.util.ArrayList;

/**
 * pivit
 */
public class Pivit {

  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    Pivit game = new Pivit(size);
    run(game);
  }

  public static void run(Pivit game) {
    game.printBoard();

    Piece p = game.getPiece(0, 1);
    if(p != null) {
      game.movePiece(p, 1);
      game.printBoard();
    }

    p = game.getPiece(1, 0);
    if(p != null) {
      game.movePiece(p, 1);
      game.printBoard();
    }

    p = game.getPiece(1, 1);
    if(p != null) {
      game.movePiece(p, -1);
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
  }

  private void generateBoard(int size) {
    if (size == 6) {
      board.add(new Piece(0, 1, "A", "x", "m"));
      board.add(new Piece(0, 2, "B", "x", "m"));
      board.add(new Piece(0, 3, "A", "x", "m"));
      board.add(new Piece(0, 4, "B", "x", "m"));

      board.add(new Piece(1, 0, "B", "y", "m"));
      board.add(new Piece(2, 0, "A", "y", "m"));
      board.add(new Piece(3, 0, "B", "y", "m"));
      board.add(new Piece(4, 0, "A", "y", "m"));

      board.add(new Piece(5, 1, "A", "x", "m"));
      board.add(new Piece(5, 2, "B", "x", "m"));
      board.add(new Piece(5, 3, "A", "x", "m"));
      board.add(new Piece(5, 4, "B", "x", "m"));

      board.add(new Piece(1, 5, "B", "y", "m"));
      board.add(new Piece(2, 5, "A", "y", "m"));
      board.add(new Piece(3, 5, "B", "y", "m"));
      board.add(new Piece(4, 5, "A", "y", "m"));

      return;
    }

    if (size == 8) {
      board.add(new Piece(0, 1, "A", "x", "m"));
      board.add(new Piece(0, 2, "B", "x", "m"));
      board.add(new Piece(0, 3, "A", "x", "m"));
      board.add(new Piece(0, 4, "A", "x", "m"));
      board.add(new Piece(0, 5, "B", "x", "m"));
      board.add(new Piece(0, 6, "A", "x", "m"));

      board.add(new Piece(1, 0, "B", "y", "m"));
      board.add(new Piece(2, 0, "A", "y", "m"));
      board.add(new Piece(3, 0, "B", "y", "m"));
      board.add(new Piece(4, 0, "B", "y", "m"));
      board.add(new Piece(5, 0, "A", "y", "m"));
      board.add(new Piece(6, 0, "B", "y", "m"));

      board.add(new Piece(7, 1, "A", "x", "m"));
      board.add(new Piece(7, 2, "B", "x", "m"));
      board.add(new Piece(7, 3, "A", "x", "m"));
      board.add(new Piece(7, 4, "A", "x", "m"));
      board.add(new Piece(7, 5, "B", "x", "m"));
      board.add(new Piece(7, 6, "A", "x", "m"));

      board.add(new Piece(1, 7, "B", "y", "m"));
      board.add(new Piece(2, 7, "A", "y", "m"));
      board.add(new Piece(3, 7, "B", "y", "m"));
      board.add(new Piece(4, 7, "B", "y", "m"));
      board.add(new Piece(5, 7, "A", "y", "m"));
      board.add(new Piece(6, 7, "B", "y", "m"));

      return;
    }
  }

  private Piece getPiece(int x, int y) {
    for (Piece piece : board) {
      if (piece.x == x && piece.y == y)
        return piece;
    }
    return null;
  }

  private void removePiece(Piece p) {
    board.remove(p);
  }

  private boolean isInCorner(Piece piece) {
    if (piece.x == 0 && piece.y == 0)
      return true;
    if (piece.x == (size - 1) && piece.y == 0)
      return true;
    if (piece.x == 0 && piece.y == (size - 1))
      return true;
    if (piece.x == (size - 1) && piece.y == (size - 1))
      return true;
    return false;
  }

  private boolean isEnd() {
    for (Piece piece : board) {
      if (piece.type.equals("m"))
        return false;
    }
    return true;
  }

  public boolean isPossibleMove(Piece piece, int distance) {
    if (piece.direction.equals("x")) {
      if (piece.x + distance < 0 || (piece.x + distance) > size) {
        return false;
      } else if (piece.type.equals("m") && Math.abs(distance) % 2 == 0) {
        return false;
      } else {
        int inc = (int) Math.signum(distance);
        for (int i = inc; Math.abs(i - distance) > 1; i += inc) {
          if (getPiece(piece.x + i, piece.y) != null) {
            return false;
          }
        }
      }
      Piece destPiece = getPiece(piece.x + distance, piece.y);
      if(destPiece != null)
        if (destPiece.player == piece.player)
          return false;

      return true;
    } else if (piece.direction == "y") {
      if (piece.y + distance < 0 || (piece.y + distance) > size) {
        return false;
      } else if (piece.type.equals("m") && Math.abs(distance) % 2 == 0) {
        return false;
      } else {
        int inc = (int) Math.signum(distance);
        for (int i = inc; Math.abs(i - distance) > 1; i += inc) {
          if (getPiece(piece.x, piece.y + i) != null) {
            return false;
          }
        }
      }
      Piece destPiece = getPiece(piece.x + distance, piece.y);
      if (destPiece.player == piece.player)
        return false;

      return true;
    } else
      return false;
  }

  private void movePiece(Piece piece, int distance) {
    System.out.println("Moving (" + piece.x + "," + piece.y + ")");

    if (isPossibleMove(piece, distance)) {
      Piece destPiece;
      if(piece.direction.equals("x")) destPiece = getPiece(piece.x+distance, piece.y);
      else destPiece = getPiece(piece.x, piece.y+distance);

      if (destPiece != null) {
        removePiece(destPiece);
        if (isEnd())
          System.exit(1);
      }

      if (piece.direction == "x") piece.x += distance;
      else piece.y += distance;

      piece.rotate();

      if (isInCorner(piece)) {
        piece.promote();
        if (isEnd())
          System.exit(1);
      }
    } else {
      System.out.println("Failed move");
    }
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
