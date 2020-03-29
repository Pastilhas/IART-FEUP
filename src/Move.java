import java.util.ArrayList;
public class Move {
  int reward;
  int estimated_reward;
  Pivit game;
  Piece piece;
  int distance;
  ArrayList<Move> children = new ArrayList<>();

  public Move(int rwd, int est_rwd, Pivit gm, Piece p, int d) {
    reward = rwd;
    estimated_reward = est_rwd;
    game = gm;
    piece = p;
    distance = d;
  }

  public void addChild(Move m) {
    children.add(m);
  }

  public void setEstimated(int est_reward) {
    estimated_reward = est_reward;
  }
}
