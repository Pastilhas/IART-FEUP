/**
 * AggroAI
 */
public class AggroAI {
  Move currentState = new Move(0,0,null,0);

  static public int minimax(int depth, Move node, int alpha, int beta, boolean isMax) {
    if(depth == 0 || node.children.isEmpty()) {
      return node.reward;
    }

    if(isMax) {
      int alpha_ = Integer.MIN_VALUE;
      for(Move child : node.children) {
        int e = minimax(depth-1, child, alpha, beta, !isMax);
        alpha_ = Integer.max(alpha_,e);
        alpha = Integer.max(alpha,alpha_);
        if(beta <= alpha) break;
      }

      return alpha_;
    } else {
      int beta_ = Integer.MAX_VALUE;
      for(Move child : node.children) {
        int e = minimax(depth-1, child, alpha, beta, !isMax);
        beta_ = Integer.min(beta_,e);
        beta = Integer.min(beta,beta_);
        if(beta <= alpha) break;
      }

      return beta_;
    }
  }

  public AggroAI() {

  }

  public void generateMoves(Pivit game, String player) {
    for(Piece p : game.board) {
      if(p.player != player) continue;
      else {
        int distance = -game.size;
        while(distance < game.size) {
          if(game.isPossibleMove(p, distance)) {
            currentState.addChild(new Move(getReward(game,p,distance), 0, p, distance));
          }
        }
      }
    }
  }

  public int getReward(Pivit game, Piece p, int distance) {
    int reward = 0;

    /*
      TABLE OF OPERATIONS AND REWARDS
    */

    return reward;
  }

}
