/**
 * Piece
 */
public class Piece {
  public static final String GREEN = "\033[92m";
  public static final String BLUE = "\033[94m";
  public static final String RESET = "\033[0m";

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
    String color, character;

    if(player == "A") color = GREEN;
    else color = BLUE;

    if(direction.equals("y"))
      if(type.equals("minion")) character = "|";
      else character = "?";
    else
    if(type.equals("minion")) character = "-";
    else character = "=";

    return (color + character + RESET);
  }
}
