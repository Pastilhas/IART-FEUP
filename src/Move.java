import java.util.ArrayList;

public class Move {
	private int reward;
	private int estimated_reward;
	private Pivit game;
	private Piece piece;
	private int distance;
	private ArrayList<Move> children = new ArrayList<>();

	public Move(int rwd, int est_rwd, Pivit gm, Piece p, int d) {
		this.reward = rwd;
		this.estimated_reward = est_rwd;
		this.game = gm;
		this.piece = p;
		this.distance = d;
	}


	public int getReward() {
		return this.reward;
	}

	public Pivit getGame() {
		return this.game;
	}

	public ArrayList<Move> getChildren() {
		return this.children;
	}

	public void addChild(Move m) {
		children.add(m);
	}

	public void setEstimated(int est_reward) {
		this.estimated_reward = est_reward;
	}
}
