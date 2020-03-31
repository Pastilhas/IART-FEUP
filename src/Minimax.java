public class Minimax {
	Move startMove;
	String player;
	int maxDepth;
	Pivit currentGameState;
	Pivit currentGameState_;
	static int move_id = 0;

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
		startMove = new Move(-1, 0, game, null, 0);
		maxDepth = 4;
		currentGameState = game;
	}

	public Move getBestMove() {
		Move bestMove = null;
		int max_x = Integer.MIN_VALUE;
		for (Move m : startMove.getChildren()) {
			int x = minimax(maxDepth - 1, m, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
			if (x > max_x) {
				bestMove = m;
				max_x = x;
			}
		}
		return bestMove;
	}

	public void generateChildMoves(Move move, int depth) {
		if (depth == 0) {
			return;
		}

		for (int i = 0; i < move.getGame().getBoard().size(); i++) {

			for (int d = -move.getGame().getSize() + 1; d < move.getGame().getSize(); d++) {
				Pivit newG = new Pivit(move.getGame());
				Piece p = newG.getBoard().get(i);
				if (newG.isPossibleMove(p, d)) {

					Piece before = new Piece(p);
					newG.movePiece(p, d);
					int reward = getValue(newG);
					Move newM = new Move(Minimax.move_id, reward, newG, before, d);
					Minimax.move_id = Minimax.move_id + 1;

					move.addChild(newM);

					generateChildMoves(newM, depth - 1);

				}
			}
		}
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

		if (game.firstPromotePlayer != null)
			if (game.firstPromotePlayer.equals(player))
				value += 4;
		for (Piece p : game.getBoard()) {
			if (p.getPlayer().equals(player) && p.getType().equals(Constants.MASTER))
				value += 2;
		}

		return value;
	}

	public void printMoves(Move move, int depth) {
		System.out.println(new String(new char[maxDepth - depth]).replace("\0", "--") + move);
		for (Move m : move.getChildren()) {
			printMoves(m, depth - 1);
		}
	}
}
