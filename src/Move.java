import java.util.ArrayList;

/**
 * Move
 */
public class Move {
  int reward;
  int estimated_reward;
  ArrayList<Move> children;

  public Move(int rwd, int est_rwd) {
    reward = rwd;
    estimated_reward = est_rwd;
  }
}
