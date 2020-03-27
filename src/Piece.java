/**
 * Piece
 */
public class Piece {
  int x, y;
  String player, direction, type;

  public Piece(int x, int y, String player, String direction, String type) {
    this.x = x;
    this.y = y;
    this.player = player;
    this.direction = direction;
    this.type = type;
  }

  public void debug() {
    System.out.println("P ("+x+","+y+") "+"["+player+","+direction+","+type+"]");
  }

}
