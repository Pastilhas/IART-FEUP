import java.util.ArrayList;

public class Pivit {
	private int size;
	private String firstPromotePlayer;
	private ArrayList<Piece> board = new ArrayList<>();
	private ArrayList<Piece> captured = new ArrayList<>();

	public Pivit(int size) {
		if (size != 6 && size != 8) {
			System.out.println("The board can only be size 6 or 8.");
			System.exit(1);
		}

		this.size = size;
		int players = 2;

		if (size == 6)
			generateBoard_6();
		else {
			switch (players) {
				case 2:
					generateBoard_8_Players_2();
					break;
				case 3:
					generateBoard_8_Players_3();
					break;
				case 4:
					generateBoard_8_Players_4();
					break;
				default:
					break;
			}
		}
	}

	public Pivit(Pivit game) {
		this.board = new ArrayList<>(game.board);
		this.captured = new ArrayList<>(game.captured);
		this.size = game.size;
		this.firstPromotePlayer = game.firstPromotePlayer;
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

	// this board generator is only valid for 3 players
	public void generateBoard_8_Players_3() {
		// 7 = 8 (size) - 1

		for (int coor = 1; coor < 7; coor++) {
			if ((coor - 1) % 3 == 0)
				board.add(new Piece(coor, 0, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 3 == 1)
				board.add(new Piece(coor, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 3 == 2)
				board.add(new Piece(coor, 0, Constants.PLAYER_3, Constants.VERTICAL, Constants.MINION));
		}

		for (int coor = 1; coor < 7; coor++) {
			if ((coor - 1) % 3 == 0)
				board.add(new Piece(coor, 7, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 3 == 1)
				board.add(new Piece(coor, 7, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 3 == 2)
				board.add(new Piece(coor, 7, Constants.PLAYER_3, Constants.VERTICAL, Constants.MINION));
		}

		for (int coor = 1; coor < 7; coor++) {
			if ((coor - 1) % 3 == 0)
				board.add(new Piece(0, coor, Constants.PLAYER_3, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 3 == 1)
				board.add(new Piece(0, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 3 == 2)
				board.add(new Piece(0, coor, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
		}

		for (int coor = 1; coor < 7; coor++) {
			if ((coor - 1) % 3 == 0)
				board.add(new Piece(7, coor, Constants.PLAYER_3, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 3 == 1)
				board.add(new Piece(7, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 3 == 2)
				board.add(new Piece(7, coor, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
		}

		return;
	}

	// this board generator is only valid for 4 players
	public void generateBoard_8_Players_4() {
		// 7 = 8 (size) - 1

		for (int coor = 1; coor < 7; coor++) {
			if ((coor - 1) % 4 == 0)
				board.add(new Piece(coor, 0, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 4 == 1)
				board.add(new Piece(coor, 0, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 4 == 2)
				board.add(new Piece(coor, 0, Constants.PLAYER_3, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 4 == 3)
				board.add(new Piece(coor, 0, Constants.PLAYER_4, Constants.VERTICAL, Constants.MINION));
		}

		for (int coor = 1; coor < 7; coor++) {
			if ((coor - 1) % 4 == 0)
				board.add(new Piece(coor, 7, Constants.PLAYER_1, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 4 == 1)
				board.add(new Piece(coor, 7, Constants.PLAYER_2, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 4 == 2)
				board.add(new Piece(coor, 7, Constants.PLAYER_3, Constants.VERTICAL, Constants.MINION));
			else if ((coor - 1) % 4 == 3)
				board.add(new Piece(coor, 7, Constants.PLAYER_4, Constants.VERTICAL, Constants.MINION));
		}

		for (int coor = 1; coor < 7; coor++) {
			if ((coor - 1) % 4 == 0)
				board.add(new Piece(0, coor, Constants.PLAYER_3, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 4 == 1)
				board.add(new Piece(0, coor, Constants.PLAYER_4, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 4 == 2)
				board.add(new Piece(0, coor, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 4 == 3)
				board.add(new Piece(0, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
		}

		for (int coor = 1; coor < 7; coor++) {
			if ((coor - 1) % 4 == 0)
				board.add(new Piece(7, coor, Constants.PLAYER_3, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 4 == 1)
				board.add(new Piece(7, coor, Constants.PLAYER_4, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 4 == 2)
				board.add(new Piece(7, coor, Constants.PLAYER_1, Constants.HORIZONTAL, Constants.MINION));
			else if ((coor - 1) % 4 == 3)
				board.add(new Piece(7, coor, Constants.PLAYER_2, Constants.HORIZONTAL, Constants.MINION));
		}

		return;
	}

	public Piece getPiece(int x, int y) {
		for (Piece piece : this.board) {
			if (piece.getX() == x && piece.getY() == y)
				return piece;
		}
		return null;
	}

	public void removePiece(Piece p) {
		this.captured.add(p);
		this.board.remove(p);
	}

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

	public boolean isEnd() {
		for (Piece piece : board) {
			if (piece.getType().equals(Constants.MINION))
				return false;
			else {
				System.out.println(piece.getPlayer() + " Player " + " wins!");
				return true;
			}
		}
		return false;
	}

	// A 'Minion' can only move in odd number of cells, the same is not true for a
	// 'Master'
	// Nor 'Minion' nor 'Master' can jump over other pieces
	public boolean isPossibleMove(Piece piece, int distance) {
		if (piece.getDirection().equals(Constants.HORIZONTAL)) {
			if (piece.getX() + distance < 0 || (piece.getX() + distance) > size) {
				return false;
			} else if (piece.getType().equals(Constants.MINION) && Math.abs(distance) % 2 == 0) {
				return false;
			} else {
				int inc = (int) Math.signum(distance);
				for (int i = inc; Math.abs(i - distance) > 1; i += inc) {
					if (getPiece(piece.getX() + i, piece.getY()) != null) {
						return false;
					}
				}
			}
			// ? wut
			Piece destPiece = getPiece(piece.getX() + distance, piece.getY());
			if (destPiece != null)
				if (destPiece.getPlayer() == piece.getPlayer())
					return false;

			return true;
		} else if (piece.getDirection() == Constants.VERTICAL) {
			if (piece.getY() + distance < 0 || (piece.getY() + distance) > size) {
				return false;
			} else if (piece.getType().equals(Constants.MINION) && Math.abs(distance) % 2 == 0) {
				return false;
			} else {
				int inc = (int) Math.signum(distance);
				for (int i = inc; Math.abs(i - distance) > 1; i += inc) {
					if (getPiece(piece.getX(), piece.getY() + i) != null) {
						return false;
					}
				}
			}
			Piece destPiece = getPiece(piece.getX() + distance, piece.getY());
			if (destPiece != null)
				if (destPiece.getPlayer() == piece.getPlayer())
					return false;

			return true;
		} else
			return false;
	}

	public void movePiece(Piece piece, int distance) {
		if (isPossibleMove(piece, distance)) {
			Piece destPiece;
			if (piece.getDirection().equals(Constants.HORIZONTAL))
				destPiece = getPiece(piece.getX() + distance, piece.getY());
			else
				destPiece = getPiece(piece.getX(), piece.getY() + distance);

			if (destPiece != null) {
				removePiece(destPiece);
				if (isEnd())
					System.exit(1);
			}

			if (piece.getDirection() == Constants.HORIZONTAL)
				piece.setX(piece.getX() + distance);
			else
				piece.setY(piece.getY() + distance);

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
