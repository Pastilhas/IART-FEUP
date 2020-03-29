public class Piece {

  int x, y;
  String player, direction, type;

  /*
  *   "x" and "y", coordinates of the piece
  *   "player", player 1 or 2
  *   "direction", horizontal or vertical
  *   "type", minion or master 
  */
  public Piece(int x, int y, String player, String direction, String type) {
    this.x = x;
    this.y = y;
    this.player = player;
    this.direction = direction;
    this.type = type;
  }

  public void debug() {
    System.out.println("P (" + x + "," + y + ") " + "[" + player + "," + direction + "," + type + "]");
  }

  public String getChar() {
    // default values (for debugging)
    String color = ".";
    String character = "?";

    // piece color
    if (player.equals(Constants.PLAYER_1))
      color = Constants.GREEN;
    else
      color = Constants.BLUE;

    // piece direction and type
    // vertical
    if (direction == Constants.VERTICAL) {
      if (type == Constants.MINION)
        character = Constants.MINION_VERTICAL;
      else if (type == Constants.MASTER)
        character = Constants.MASTER_VERTICAL;
    }

    // horizontal
    else if (direction == Constants.HORIZONTAL) {
      if (type == Constants.MINION)
        character = Constants.MINION_HORIZONTAL;
      else if (type == Constants.MASTER)
        character = Constants.MASTER_HORIZONTAL;
    }

    return ("" + color + character + Constants.RESET);
  }

  // when any piece is moved, it rotates 90º
  public void rotate() {
    if (direction.equals(Constants.HORIZONTAL))
      direction = Constants.VERTICAL;
    else
      direction = Constants.HORIZONTAL;
  }

  // when a "Minion" piece reaches a corner, it becomes a "Master"
  public void promote() {
    type = Constants.MASTER;
  }
}
