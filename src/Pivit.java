import java.util.ArrayList;

public class Pivit {
  private int size;
  private Constants.GameMode gameMode;
  private Constants.GameState gameState;
  private String player_turn;
  public String firstPromotePlayer = null;
  private ArrayList<Piece> board = new ArrayList<>();
  private ArrayList<Piece> captured = new ArrayList<>();

  public Pivit(int size, Constants.GameMode gameMode) {
    this.size = size;
    this.gameMode = gameMode;
    this.gameState = Constants.GameState.MAINGAME_STATE;
    player_turn = "GREEN";

    if (size == 6)
      generateBoard_6();
    else {
      generateBoard_8_Players_2();
    }
  }

  public Pivit(Pivit game) {
    this.board = new ArrayList<>();
    for (Piece p : game.board) {
      this.board.add(new Piece(p));
    }
    this.captured = new ArrayList<>();
    for (Piece p : game.captured) {
      this.captured.add(new Piece(p));
    }
    this.size = game.size;
    this.gameMode = game.gameMode;
    this.gameState = Constants.GameState.MAINGAME_STATE;
    this.firstPromotePlayer = game.firstPromotePlayer;
    this.player_turn = game.player_turn;
  }

  public int getSize() {
    return this.size;
  }

  public ArrayList<Piece> getBoard() {
    return this.board;
  }

  public ArrayList<Piece> getCaptured() {
    return this.captured;
  }

  // this board generator is only valid for 2 players
  // "for learning or fast games"
  public void generateBoard_6() {
    // 5 = 6 (size) - 1

    for (int coor = 1; coor < 5; coor++) {
      if ((coor - 1) % 2 == 0)
        board.add(new Piece(coor, 0, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      else
        board.add(new Piece(coor, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
    }

    for (int coor = 1; coor < 5; coor++) {
      if ((coor - 1) % 2 == 0)
        board.add(new Piece(coor, 5, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      else
        board.add(new Piece(coor, 5, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
    }

    for (int coor = 1; coor < 5; coor++) {
      if ((coor - 1) % 2 == 0)
        board.add(new Piece(0, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      else
        board.add(new Piece(0, coor, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
    }

    for (int coor = 1; coor < 5; coor++) {
      if ((coor - 1) % 2 == 0)
        board.add(new Piece(5, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      else
        board.add(new Piece(5, coor, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
    }

    return;
  }

  // this board generator is only valid for 2 players
  public void generateBoard_8_Players_2() {
    // 7 = 8 (size) - 1

    for (int coor = 1; coor < 7; coor++) {
      if ((coor - 1) % 3 == 0)
        board.add(new Piece(coor, 0, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      else if ((coor - 1) % 3 == 1)
        board.add(new Piece(coor, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      else if ((coor - 1) % 3 == 2)
        board.add(new Piece(coor, 0, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
    }

    for (int coor = 1; coor < 7; coor++) {
      if ((coor - 1) % 3 == 0)
        board.add(new Piece(coor, 7, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
      else if ((coor - 1) % 3 == 1)
        board.add(new Piece(coor, 7, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
      else if ((coor - 1) % 3 == 2)
        board.add(new Piece(coor, 7, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
    }

    for (int coor = 1; coor < 7; coor++) {
      if ((coor - 1) % 3 == 0)
        board.add(new Piece(0, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      else if ((coor - 1) % 3 == 1)
        board.add(new Piece(0, coor, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      else if ((coor - 1) % 3 == 2)
        board.add(new Piece(0, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
    }

    for (int coor = 1; coor < 7; coor++) {
      if ((coor - 1) % 3 == 0)
        board.add(new Piece(7, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
      else if ((coor - 1) % 3 == 1)
        board.add(new Piece(7, coor, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
      else if ((coor - 1) % 3 == 2)
        board.add(new Piece(7, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
    }

    return;
  }

  // checks if the piece exists in the board
  public Piece getPiece(int x, int y) {
    for (Piece piece : this.board) {
      if (piece.getX() == x && piece.getY() == y)
        return piece;
    }
    return null;
  }

  // removes a piece from the board
  // and adds it to the captured list
  public void removePiece(Piece p) {
    this.captured.add(p);
    this.board.remove(p);
  }

  // checks if the piece is in the corner, by verifying it's coordinates
  public boolean isInCorner(Piece piece) {
    if (piece.getX() == 0 && piece.getY() == 0)
      return true;
    else if (piece.getX() == (this.size - 1) && piece.getY() == 0)
      return true;
    else if (piece.getX() == 0 && piece.getY() == (this.size - 1))
      return true;
    else if (piece.getX() == (this.size - 1) && piece.getY() == (this.size - 1))
      return true;
    return false;
  }

  // // checks if any player still has any pieces
  // public boolean hasPiecesLeft(String player){
  // return false;
  // }

  // checks if the end conditions are met
  public boolean isGameOver() {
    for (Piece piece : board) {
      if (piece.getType().equals(Constants.MINION))
        return false;
    }
    return true;
  }

  // changes player's turn
  public void switchTurn() {
    if (player_turn.equals(Constants.PLAYER_1))
      this.player_turn = Constants.PLAYER_2;
    else
      this.player_turn = Constants.PLAYER_1;
  }

  // A 'Minion' can only move in odd number of cells,
  // the same is not true for a 'Master'.
  // Nor 'Minion' nor 'Master' can jump over other pieces
  public boolean isPossibleMove(Piece piece, int distance) {
    if (piece.getPlayer().equals(this.player_turn)) {
      if (piece.getDirection().equals(Constants.HORIZONTAL)) {
        if ((piece.getX() + distance) < 0 || (piece.getX() + distance) >= size) {
          return false;
        } else if (piece.getType().equals(Constants.MINION) && Math.abs(distance) % 2 == 0) {
          return false;
        } else {
          int inc = (int) Math.signum(distance);
          for (int i = inc; Math.abs(i - distance) > 0; i += inc) {
            if (getPiece(piece.getX() + i, piece.getY()) != null) {
              return false;
            }
          }
        }
        Piece destPiece = getPiece(piece.getX() + distance, piece.getY());
        if (destPiece != null)
          if (destPiece.getPlayer().equals(piece.getPlayer())) {
            return false;
          }
        return true;
      } else if (piece.getDirection().equals(Constants.VERTICAL)) {
        if ((piece.getY() + distance) < 0 || (piece.getY() + distance) >= size) {
          return false;
        } else if (piece.getType().equals(Constants.MINION) && Math.abs(distance) % 2 == 0) {
          return false;
        } else {
          int inc = (int) Math.signum(distance);
          for (int i = inc; Math.abs(i - distance) > 0; i += inc) {
            if (getPiece(piece.getX(), piece.getY() + i) != null) {
              return false;
            }
          }
        }
        Piece destPiece = getPiece(piece.getX(), piece.getY() + distance);
        if (destPiece != null)
          if (destPiece.getPlayer().equals(piece.getPlayer())) {
            return false;
          }

        return true;
      }
    }
    return false;
  }

  public void movePiece(Piece piece, int distance) {
    if (this.isPossibleMove(piece, distance)) {
      Piece destPiece;
      if (piece.getDirection().equals(Constants.HORIZONTAL))
        destPiece = getPiece(piece.getX() + distance, piece.getY());
      else
        destPiece = getPiece(piece.getX(), piece.getY() + distance);

      if (destPiece != null) {
        this.removePiece(destPiece);
      }

      if (piece.getDirection() == Constants.HORIZONTAL)
        piece.setX(piece.getX() + distance);
      else
        piece.setY(piece.getY() + distance);

      piece.rotate();

      if (this.isInCorner(piece)) {
        piece.promote();
        if (this.firstPromotePlayer == null)
          this.firstPromotePlayer = piece.getPlayer();
      }

    }
  }

  // prints board
  public void printBoard() {
    String separatorLine = "+---+---+---+---+---+---+";
    String separatorLeft = "| ";
    String separatorRight = " ";
    if (this.size == 8) {
      separatorLine += "---+---+";
    }

    for (int y = 0; y < this.size; y++) {
      System.out.println(separatorLine);
      String line = "";
      for (int x = 0; x < this.size; x++) {
        line += separatorLeft;
        Piece p = getPiece(x, y);

        // checks if it's odd or even
        if (((x + y) & 1) == 0)
          line += Constants.RED_BACKGROUND;
        else
          line += Constants.BLACK_BACKGROUND;

        if (p == null) {
          line += " ";
        } else {
          line += p.getChar();
        }
        line += Constants.RESET;

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

  // loop where player vs player runs
  private Constants.GameState PlayPlayer() {
    int[] coordinates = new int[2];
    Piece playPiece;
    int distance;
    String winner;

    this.printBoard();

    do {
      System.out.println(this.player_turn + " Player's turn");
      coordinates = Input.getCoordinates();
      playPiece = this.getPiece(coordinates[0], coordinates[1]);
      while (playPiece == null || playPiece.getPlayer() != this.player_turn) {
        System.out.println("There isn't a " + this.player_turn + " piece there.");
        coordinates = Input.getCoordinates();
        playPiece = this.getPiece(coordinates[0], coordinates[1]);
      }
      distance = Input.getDistance();
      while (this.isPossibleMove(playPiece, distance) == false) {
        System.out.println("That piece can't move there.");
        distance = Input.getDistance();
      }
      this.movePiece(playPiece, distance);
      winner = this.player_turn;
      this.switchTurn();
      this.printBoard();

    } while (!this.isGameOver());

    System.out.println(winner + " PLAYER WINS!");
    this.gameState = Constants.GameState.MENU_STATE;

    return this.gameState;
  }

  // public Constants.GameState run() {

  //   while (!isGameOver()) {
  //     this.printBoard();
  //     if (gameMode == Constants.GameMode.BVB) {
  //       Minimax A = new Minimax(Constants.PLAYER_1, new Pivit(this));
  //       A.generateChildMoves(A.startMove, A.maxDepth);
  //       Move bestMove = A.getBestMove();
  //       Piece pp = getPiece(bestMove.getPiece().getX(), bestMove.getPiece().getY());
  //       movePiece(pp, bestMove.getDistance());
  //       System.out.println(bestMove);
  //     } else if (gameMode == Constants.GameMode.PVB || gameMode == Constants.GameMode.PVP) {
  //       // player faz move
  //     }
  //     if (gameMode == Constants.GameMode.BVB || gameMode == Constants.GameMode.PVB) {
  //       Minimax B = new Minimax(Constants.PLAYER_2, new Pivit(this));
  //       B.generateChildMoves(B.startMove, B.maxDepth);
  //       Move bestMove = B.getBestMove();
  //       Piece pp = getPiece(bestMove.getPiece().getX(), bestMove.getPiece().getY());
  //       movePiece(pp, bestMove.getDistance());
  //       System.out.println(bestMove);

  //     } else if (gameMode == Constants.GameMode.PVP) {
  //       // player faz move
  //     }

  //   }
  //   return Constants.GameState.MENU_STATE;
  // }

  // loop where player vs bot runs
  // first player is human
  // second player is bot
  private Constants.GameState PlayBot() {
    int[] coordinates = new int[2];
    Piece playPiece;
    int distance;
    String winner;

    Move bestMove;

    this.printBoard();

    do {
      System.out.println(this.player_turn + " Player's turn");
      if (this.player_turn == Constants.PLAYER_1) {
        coordinates = Input.getCoordinates();
        playPiece = this.getPiece(coordinates[0], coordinates[1]);
        while (playPiece == null || playPiece.getPlayer() != this.player_turn) {
          System.out.println("There isn't a " + this.player_turn + " piece there.");
          coordinates = Input.getCoordinates();
          playPiece = this.getPiece(coordinates[0], coordinates[1]);
        }
        distance = Input.getDistance();
        while (this.isPossibleMove(playPiece, distance) == false) {
          System.out.println("That piece can't move there.");
          distance = Input.getDistance();
        }
        this.movePiece(playPiece, distance);
      }
      winner = this.player_turn;
      this.switchTurn();
      this.printBoard();

      Minimax B = new Minimax(Constants.PLAYER_2, new Pivit(this));
  		B.generateChildMoves(B.startMove, B.maxDepth);
  		 bestMove = B.getBestMove();
  		Piece pp = getPiece(bestMove.getPiece().getX(), bestMove.getPiece().getY());
  		movePiece(pp, bestMove.getDistance());
  		System.out.println(bestMove);

    } while (!this.isGameOver());

    System.out.println(winner + " PLAYER WINS!");
    this.gameState = Constants.GameState.MENU_STATE;

    return this.gameState;

  }

  // loop where bot vs bot runs
  private Constants.GameState PlayBotBot() {
    String winner;

    do {
	    this.printBoard();
			Minimax A = new Minimax(Constants.PLAYER_1, new Pivit(this));
			A.generateChildMoves(A.startMove, A.maxDepth);
      Move bestMove = A.getBestMove();
  		Piece pp = getPiece(bestMove.getPiece().getX(), bestMove.getPiece().getY());
  		movePiece(pp, bestMove.getDistance());
  	  System.out.println(bestMove);

			winner = this.player_turn;
      this.printBoard();
			if(isGameOver()) break;
			this.switchTurn();

      Minimax B = new Minimax(Constants.PLAYER_2, new Pivit(this));
      B.generateChildMoves(B.startMove, B.maxDepth);
      bestMove = B.getBestMove();
      pp = getPiece(bestMove.getPiece().getX(), bestMove.getPiece().getY());
      movePiece(pp, bestMove.getDistance());
			System.out.println(bestMove);

      winner = this.player_turn;
      this.switchTurn();
      this.printBoard();

    } while (!this.isGameOver());

    System.out.println(winner + " PLAYER WINS!");
    this.gameState = Constants.GameState.MENU_STATE;

    return this.gameState;
  }

  // 'main' function of Pivit.java
  public Constants.GameState run() {
    if (this.gameMode == Constants.GameMode.PVB) {
      System.out.println("Player VS Bot Mode");
      this.PlayBot();

    } else if (this.gameMode == Constants.GameMode.BVB) {
      System.out.println("Bot VS Bot Mode");
      this.PlayBotBot();

    } else if (this.gameMode == Constants.GameMode.PVP) {
      System.out.println("Player VS Player Mode");
      this.PlayPlayer();
    }

    return this.gameState;
  }
}
