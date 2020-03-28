import java.util.ArrayList;

/**
 * Move
 */
public class Move {
  int reward;
  int estimated_reward;
  Piece piece;
  int distance;
  ArrayList<Move> children;

  public Move(int rwd, int est_rwd, Piece p, int d) {
    reward = rwd;
    estimated_reward = est_rwd;
    piece = p;
    distance = d;
  }

  public void addChild(Move m) {
    children.add(m);
  }
}
