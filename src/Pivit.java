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
    if (game.movePiece(game.getPiece(1, 0), 1)) {
      game.debugBoard();
      game.printBoard();
    }

    System.out.println("");
    if (game.movePiece(game.getPiece(0, 1), 3)) {
      game.debugBoard();
      game.printBoard();
    }

    System.out.println("");
    if (game.movePiece(game.getPiece(2, 0), 3)) {
      game.debugBoard();
      game.printBoard();
    }

    System.out.println("");
    if (game.movePiece(game.getPiece(2, 5), -3)) {
      game.debugBoard();
      game.printBoard();
    }
    System.out.println("");
    if (game.movePiece(game.getPiece(0, 1), 1)) {
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

  private void rotatePiece(Piece piece) {
    if (piece.direction == 'x')
      piece.direction = 'y';
    else if (piece.direction == 'y')
      piece.direction = 'x';
  }

  private boolean isInCorner(Piece piece) {
    int x = piece.x;
    int y = piece.y;

    if (x == 0 && y == 0)
      return true;
    if (x == (size - 1) && y == 0)
      return true;
    if (x == 0 && y == (size - 1))
      return true;
    if (x == (size - 1) && y == (size - 1))
      return true;
    return false;
  }

  private void promotePiece(Piece piece) {
    if (piece.type == 'm')
      piece.type = 'M';
  }

  private boolean isEnd() {
    for (Piece piece : board) {
      if (piece.type == 'm')
        return false;
    }
    return true;
  }

<<<<<<< HEAD
  public boolean isPossibleMove(Piece piece, int distance) {
    if (piece.direction == "x") {
      if (piece.x + distance < 0 || (piece.x + distance) > size) {
        return false;
      } else if (piece.type == "minion" && Math.abs(distance) % 2 == 0) {
        return false;
      } else {
        int inc = (int) Math.signum(distance);
        for(int i = inc; Math.abs(i - distance) > 1; i += inc) {
          if (getPiece(piece.x + i, piece.y) != null) {
            return false;
          }
        }
      }
      Piece destPiece = getPiece(piece.x + distance, piece.y);
      if (destPiece.player == piece.player) return false;

      return true;
    }

    else if (piece.direction == "y") {
      if (piece.y + distance < 0 || (piece.y + distance) > size) {
        return false;
      } else if (piece.type == "minion" && Math.abs(distance) % 2 == 0) {
        return false;
      } else {
        int inc = (int) Math.signum(distance);
        for(int i = inc; Math.abs(i - distance) > 1; i += inc) {
          if (getPiece(piece.x, piece.y + i) != null) {
            return false;
          }
        }
      }
      Piece destPiece = getPiece(piece.x + distance, piece.y);
      if (destPiece.player == piece.player) return false;

      return true;
=======
  private boolean moveHorizontal(Piece piece, int distance) {
    System.out.println("Moving piece from player " + piece.player);
    System.out.println("From position [" + piece.x + "," + piece.y + "]");
    System.out.println("To position [" + (piece.x + distance) + "," + piece.y + "]");

    if ((piece.x + distance) < 0 || (piece.x + distance) > (size - 1)) {
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
      while (Math.abs(i - (piece.x + distance)) > 0) {
        if (getPiece(i, piece.y) != null) {
          System.out.print("This piece is in the way: ");
          getPiece(i, piece.y).debug();
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
        if(isEnd()) System.exit(1);
        debugBoard();
      }
    }
    piece.x += distance;
    return true;
  }

  private boolean moveVertical(Piece piece, int distance) {
    System.out.println("Moving piece from player " + piece.player);
    System.out.println("From position [" + piece.x + "," + piece.y + "]");
    System.out.println("To position [" + piece.x + "," + (piece.y + distance) + "]");

    if ((piece.y + distance) < 0 || (piece.y + distance) > (size - 1)) {
      System.out.println("Invalid movement");
      return false;
    }

    else if (piece.type == 'm' && Math.abs(distance) % 2 == 0) {
      System.out.println("Minions can only move in odd distances");
      return false;
    }

    else {
      int inc = (int) Math.signum(distance);
      int i = piece.y + inc;
      while (Math.abs(i - (piece.y + distance)) > 0) {
        if (getPiece(piece.x, i) != null) {
          System.out.print("This piece is in the way: ");
          getPiece(piece.x, i).debug();
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
        if(isEnd()) System.exit(1);
        debugBoard();
      }
    }
    piece.y += distance;
    return true;
  }

  private boolean movePiece(Piece piece, int distance) {

    if (piece == null)
      return false;

    if (piece.direction == 'x') {
      if (!moveHorizontal(piece, distance))
        return false;
>>>>>>> 0936541a16c361934e28631b2b458ddd0f8a6c2a
    }

    else if (piece.direction == 'y') {
      if (!moveVertical(piece, distance))
        return false;
    }

<<<<<<< HEAD
  private void movePiece(Piece piece, int distance) {
    System.out.println("Moving piece from player " + piece.player);
    System.out.println("From position [" + piece.x + "," + piece.y + "]");
    System.out.println("To position []");

    if(isPossibleMove(piece, distance)) {
      Piece destPiece = getPiece(piece.x + distance, piece.y);
      if(destPiece != null) removePiece(destPiece);

      if(piece.direction == "x") piece.x += distance;
      else piece.y += distance;



    } else {
      System.out.println("Failed move");
    }
  }

  private void removePiece(Piece p) {
    board.remove(p);
=======
    if (isInCorner(piece)) {
      promotePiece(piece);
      if(isEnd()) System.exit(1);
    }

    rotatePiece(piece);
    return true;
>>>>>>> 0936541a16c361934e28631b2b458ddd0f8a6c2a
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
