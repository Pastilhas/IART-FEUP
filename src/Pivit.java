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
    Minimax A = new Minimax(Constants.PLAYER_1, game);
    A.generateChildMoves(A.startMove, 4);
    A.printMoves(A.startMove, 0);
  }

  ArrayList<Piece> board = new ArrayList<>();
  ArrayList<Piece> captured = new ArrayList<>();
  int size;
  String firstPromotePlayer;

  public Pivit(int size) {
    if (size != 6 && size != 8) {
      System.out.println("The board can only be size 6 or 8.");
      System.exit(1);
    }

    this.size = size;
    generateBoard(size);
  }

  public Pivit(Pivit game) {
    board = new ArrayList<>(game.board);
    captured = new ArrayList<>(game.captured);
    size = game.size;
    firstPromotePlayer = game.firstPromotePlayer;
  }

  public void generateBoard(int size) {
    if (size == 6) {
      board.add(new Piece(0, 1, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(0, 2, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(0, 3, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(0, 4, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));

      board.add(new Piece(1, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(2, 0, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(3, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(4, 0, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));

      board.add(new Piece(5, 1, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(5, 2, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(5, 3, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(5, 4, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));

      board.add(new Piece(1, 5, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(2, 5, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(3, 5, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(4, 5, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));

      return;
    }

    if (size == 8) {
      board.add(new Piece(0, 1, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(0, 2, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(0, 3, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(0, 4, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(0, 5, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(0, 6, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));

      board.add(new Piece(1, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(2, 0, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(3, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(4, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(5, 0, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(6, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));

      board.add(new Piece(7, 1, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(7, 2, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(7, 3, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(7, 4, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(7, 5, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      board.add(new Piece(7, 6, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));

      board.add(new Piece(1, 7, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(2, 7, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(3, 7, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(4, 7, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(5, 7, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      board.add(new Piece(6, 7, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));

      return;
    }
  }

  public Piece getPiece(int x, int y) {
    for (Piece piece : board) {
      if (piece.x == x && piece.y == y)
        return piece;
    }
    return null;
  }

  public void removePiece(Piece p) {
    captured.add(p);
    board.remove(p);
  }

  public boolean isInCorner(Piece piece) {
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

  public boolean isEnd() {
    for (Piece piece : board) {
      if (piece.type.equals(Constants.MINION))
        return false;
    }
    return true;
  }

  public boolean isPossibleMove(Piece piece, int distance) {
    if (piece.direction.equals(Constants.HORIZONTAL)) {
      if (piece.x + distance < 0 || (piece.x + distance) > size) {
        return false;
      } else if (piece.type.equals(Constants.MINION) && Math.abs(distance) % 2 == 0) {
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
      if (destPiece != null)
        if (destPiece.player == piece.player)
          return false;

      return true;
    } else if (piece.direction == Constants.VERTICAL) {
      if (piece.y + distance < 0 || (piece.y + distance) > size) {
        return false;
      } else if (piece.type.equals(Constants.MINION) && Math.abs(distance) % 2 == 0) {
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
      if (destPiece != null)
        if (destPiece.player == piece.player)
          return false;

      return true;
    } else
      return false;
  }

  public void movePiece(Piece piece, int distance) {
    if (isPossibleMove(piece, distance)) {
      Piece destPiece;
      if (piece.direction.equals(Constants.HORIZONTAL))
        destPiece = getPiece(piece.x + distance, piece.y);
      else
        destPiece = getPiece(piece.x, piece.y + distance);

      if (destPiece != null) {
        removePiece(destPiece);
        if (isEnd())
          System.exit(1);
      }

      if (piece.direction == Constants.HORIZONTAL)
        piece.x += distance;
      else
        piece.y += distance;

      piece.rotate();

      if (isInCorner(piece)) {
        piece.promote();
        if (isEnd())
          System.exit(1);
      }
    }
  }

  public void printBoard() {
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

  public void debugBoard() {
    for (Piece p : this.board) {
      p.debug();
    }
  }

}
