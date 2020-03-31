public class Minimax {
	Move startMove;
	String player;
	int maxDepth;
	Pivit currentGameState;

	static public int minimax(int depth, Move node, int alpha, int beta, boolean isMax) {
		if (depth == 0 || node.getChildren().isEmpty()) {
			return node.getReward();
		}

		if (isMax) {
			int alpha_ = Integer.MIN_VALUE;
			for (Move child : node.getChildren()) {
				int e = minimax(depth - 1, child, alpha, beta, !isMax);
				alpha_ = Integer.max(alpha_, e);
				alpha = Integer.max(alpha, alpha_);
				if (beta <= alpha)
					break;
			}

			return alpha_;
		} else {
			int beta_ = Integer.MAX_VALUE;
			for (Move child : node.getChildren()) {
				int e = minimax(depth - 1, child, alpha, beta, !isMax);
				beta_ = Integer.min(beta_, e);
				beta = Integer.min(beta, beta_);
				if (beta <= alpha)
					break;
			}

			return beta_;
		}
	}

	public Minimax(String pl, Pivit game) {
		player = pl;
		startMove = new Move(0, 0, null, 0);
		maxDepth = 1;
		currentGameState = game;
	}

	public void generateChildMoves(Move move, int depth) {
		if (depth == 0)
			return;
		int est_reward = 0;

		for (Piece p : currentGameState.getBoard()) {
			for (int d = -currentGameState.getSize() + 1; d < currentGameState.getSize(); d++) {
				if (currentGameState.isPossibleMove(p, d)) {
					Pivit newG = new Pivit(currentGameState);
					newG.movePiece(p, d);
					int reward = getValue(newG);
					est_reward += reward;
					Move newM = new Move(reward, 0, p, d);
					move.addChild(newM);
					generateChildMoves(newM, depth - 1);
				}
			}
		}
		move.setEstimated(est_reward);
	}

	public int getValue(Pivit game) {
		int value = 0;
		for (Piece p : game.getCaptured()) {
			if (p.getPlayer().equals(player)) {
				if (p.getType().equals(Constants.MINION))
					value -= 1; // lose 1 pt for each friendly minion captured
				else
					value -= 2; // lose 2 pt for each friendly master captured
			} else {
				if (p.getType().equals(Constants.MINION))
					value += 1; // gain 1 pt for each rival minion captured
				else
					value += 2; // gain 2 pt for each rival master captured
			}
		}
		return value;
	}

	public void printMoves(Move move, int depth) {
		System.out.println(move);
		for (Move m : move.getChildren()) {
			printMoves(m, depth - 1);
		}
	}
}
