import java.util.ArrayList;

public class Move {
	public int id;
	private int reward;
	private Pivit game;
	private Piece piece;
	private int distance;
	private ArrayList<Move> children = new ArrayList<>();

	/**
	 * Constructor of Move class. Stores piece and distance to move, plus the value
	 * of the board after the move.
	 *
	 * @param id   identification number of the move
	 * @param rwd  value of board after the move
	 * @param game current state of the game after the move
	 * @param p    piece to move (state of the piece before the move)
	 * @param d    distance to move
	 */
	public Move(int id, int rwd, Pivit game, Piece p, int d) {
		this.id = id;
		this.reward = rwd;
		this.game = game;
		this.piece = p;
		this.distance = d;
	}

	public int getReward() {
		return this.reward;
	}

	public Pivit getGame() {
		return game;
	}

	public ArrayList<Move> getChildren() {
		return this.children;
	}

	public int getDistance() {
		return distance;
	}

	public Piece getPiece() {
		return piece;
	}

	public void addChild(Move m) {
		children.add(m);
	}

	@Override
	public String toString() {
		return "Move(" + id + "," + reward + "," + piece + "," + distance + ")";
	}
}
