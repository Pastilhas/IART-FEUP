/**
 * Piece
 */
public class Piece {

  int x, y;
  char player, direction, type;

  public Piece(int x, int y, char player, char direction, char type) {
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
    char character = '?';

    // piece color
    if (player == 'A')
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

}
