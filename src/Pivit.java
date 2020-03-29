import java.util.ArrayList;

/**
 * pivit
 */
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
		generateBoard(size);
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

		else if (size == 8) {
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
			if (piece.getX() == x && piece.getY() == y)
				return piece;
		}
		return null;
	}

	public void removePiece(Piece p) {
		captured.add(p);
		board.remove(p);
	}

	public boolean isInCorner(Piece piece) {
		if (piece.getX() == 0 && piece.getY() == 0)
			return true;
		if (piece.getX() == (size - 1) && piece.getY() == 0)
			return true;
		if (piece.getX() == 0 && piece.getY() == (size - 1))
			return true;
		if (piece.getX() == (size - 1) && piece.getY() == (size - 1))
			return true;
		return false;
	}

	public boolean isEnd() {
		for (Piece piece : board) {
			if (piece.getType().equals(Constants.MINION))
				return false;
		}
		return true;
	}

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
