public class Minimax {

  static public int minimax(int depth, Move node, int alpha, int beta, boolean isMax) {
    if (depth == 0 || node.children.isEmpty()) {
      return node.reward;
    }

    if (isMax) {
      int alpha_ = Integer.MIN_VALUE;
      for (Move child : node.children) {
        int e = minimax(depth - 1, child, alpha, beta, !isMax);
        alpha_ = Integer.max(alpha_, e);
        alpha = Integer.max(alpha, alpha_);
        if (beta <= alpha)
          break;
      }

      return alpha_;
    } else {
      int beta_ = Integer.MAX_VALUE;
      for (Move child : node.children) {
        int e = minimax(depth - 1, child, alpha, beta, !isMax);
        beta_ = Integer.min(beta_, e);
        beta = Integer.min(beta, beta_);
        if (beta <= alpha)
          break;
      }

      return beta_;
    }
  }

  Move startMove;
  String player;

  public Minimax(String pl, Pivit game) {
    player = pl;
    startMove = new Move(0, 0, new Pivit(game), null, 0);
  }

  public void generateChildMoves(Move move, int depth) {
    if (depth == 0)
      return;
    Pivit g = move.game;
    int est_reward = 0;
    for (Piece p : g.board) {
      for (int d = -g.size + 1; d < g.size; d++) {
        if (g.isPossibleMove(p, d)) {
          Pivit newG = new Pivit(g);
          newG.movePiece(p, d);
          int reward = getValue(newG);
          est_reward += reward;
          Move newM = new Move(reward, 0, newG, p, d);
          move.addChild(newM);
          generateChildMoves(newM, depth - 1);
        }
      }
    }
    move.setEstimated(est_reward);
  }

  public int getValue(Pivit game) {
    int value = 0;
    for (Piece p : game.captured) {
      if (p.player.equals(player)) {
        if (p.type.equals("m"))
          value -= 1; // lose 1 pt for each friendly minion captured
        else
          value -= 2; // lose 2 pt for each friendly master captured
      } else {
        if (p.type.equals("m"))
          value += 1; // gain 1 pt for each rival minion captured
        else
          value += 2; // gain 2 pt for each rival master captured
      }
    }
    return value;
  }

  public void printMoves(Move move, int depth) {
    System.out.println("yes");
    for(Move m : startMove.children) {
      printMoves(m, depth+1);
    }
  }
}
