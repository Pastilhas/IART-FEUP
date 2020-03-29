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

  public void rotate() {
    if (direction.equals(Constants.HORIZONTAL))
      direction = Constants.VERTICAL;
    else
      direction = Constants.HORIZONTAL;
  }

  public void promote() {
    type = Constants.MASTER;
  }
}
