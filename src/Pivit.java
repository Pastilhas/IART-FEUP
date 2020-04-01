import java.util.ArrayList;

public class Pivit {
  private int size;
  private Constants.GameMode gameMode;
  private Constants.GameState gameState;
  private String player_turn;
  public String firstPromotePlayer = null;
  private ArrayList<Piece> board = new ArrayList<>();
  private ArrayList<Piece> captured = new ArrayList<>();

  /**
   * Pivit class constructor
   *
   * @param size     size of board (6 or 8)
   * @param gameMode game mode can be PvP, PvB or BvB
   */
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

  /**
   * Pivit copy constructor
   *
   * @param game game state to copy
   */
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

  /**
   * Generate 6x6 board
   */
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

  /**
   * Generate 8x8 board
   */
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

  /**
   * Get piece in position x and y
   *
   * @param x x position of piece to find
   * @param y y position of piece to find
   * @return piece if exists, otherwise null
   */
  public Piece getPiece(int x, int y) {
    for (Piece piece : this.board) {
      if (piece.getX() == x && piece.getY() == y)
        return piece;
    }
    return null;
  }

  /**
   * Remove piece from board and place it in captured list
   *
   * @param p piece to move from board to captured
   */
  public void removePiece(Piece p) {
    this.captured.add(p);
    this.board.remove(p);
  }

  /**
   * Check if piece is in one of the corners
   *
   * @param piece piece to check
   * @return true if piece is in a corner, false otherwise
   */
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

  /**
   * Check if game has reach final state
   *
   * @return true if game is in final state, false otherwise
   */
  public boolean isGameOver() {
    for (Piece piece : board) {
      if (piece.getType().equals(Constants.MINION))
        return false;
    }
    return true;
  }

  /**
   * Get the player who has won the match
   * @return name of the player who won
   */
	public String checkWinner() {
		int player_1_Masters = 0;
		int player_2_Masters = 0;

		for (Piece piece : board) {
			if (piece.getType().equals(Constants.MASTER) && piece.getPlayer() == Constants.PLAYER_1)
				player_1_Masters++;
			if (piece.getType().equals(Constants.MASTER) && piece.getPlayer() == Constants.PLAYER_2)
				player_2_Masters++;
		}

		if (player_1_Masters > player_2_Masters)
			return Constants.PLAYER_1;
		else if (player_2_Masters > player_1_Masters)
			return Constants.PLAYER_2;
		else
			return this.firstPromotePlayer;
	}

  /**
   * Switch player turn
   */
  public void switchTurn() {
    if (player_turn.equals(Constants.PLAYER_1))
      this.player_turn = Constants.PLAYER_2;
    else
      this.player_turn = Constants.PLAYER_1;
  }

  /**
   * Check if move with given piece and distance is possible
   *
   * @param piece    piece to move
   * @param distance distance to move
   * @return true if piece can move distance, false otherwise
   */
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

  /**
   * Move piece a given distance
   *
   * @param piece    piece to move
   * @param distance distance to move
   */
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

  /**
   * Print the board on the terminal
   */
  public void printBoard() {
		String upperLine = "   | 0 | 1 | 2 | 3 | 4 | 5 |";
		String separatorLine = "---+---+---+---+---+---+---+";
    String separatorLeft = "| ";
    String separatorRight = " ";
    if (this.size == 8) {
      separatorLine += "---+---+";
			upperLine += " 6 | 7 |";
    }
		System.out.println();
		System.out.println(upperLine);

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
			System.out.println(" " + y + " " + line);
    }
    System.out.println(separatorLine);
		System.out.println();
  }

  /**
   * Print all pieces in the board
   */
  public void debugBoard() {
    for (Piece p : this.board) {
      p.debug();
    }
  }

  /**
   * Game loop for PvP game mode
   *
   * @return
   */
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

  /**
   * Game loop for PvB game mode
   *
   * @return
   */
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

  /**
   * Game loop for BvB game mode
   *
   * @return
   */
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
      if (isGameOver())
        break;
      this.switchTurn();

      Minimax B = new Minimax(Constants.PLAYER_2, new Pivit(this));
      B.generateChildMoves(B.startMove, B.maxDepth);
      bestMove = B.getBestMove();
      pp = getPiece(bestMove.getPiece().getX(), bestMove.getPiece().getY());
      movePiece(pp, bestMove.getDistance());
      System.out.println(bestMove);
			this.switchTurn();

		} while (!this.isGameOver());

		System.out.println(checkWinner() + " PLAYER WINS!");
		this.gameState = Constants.GameState.MENU_STATE;

		return this.gameState;
	}

  /**
   * Chooses what game loop to run based on game mode
   *
   * @return
   */
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
