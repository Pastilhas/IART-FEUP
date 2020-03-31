import java.util.ArrayList;

public class Move {
	private int reward;
	private Pivit game;
	private Piece piece;
	private int distance;
	private ArrayList<Move> children = new ArrayList<>();

	public Move(int rwd, Pivit game, Piece p, int d) {
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

	public void addChild(Move m) {
		children.add(m);
	}

	@Override
	public String toString() {
		return "Move("+reward+","+piece+","+distance+")";
	}
}
