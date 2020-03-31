public class Piece {

    private int x, y;
    private String player, direction, type;

    /*
     * "x" and "y", coordinates of the piece "player", player 1 or 2 "direction",
     * horizontal or vertical "type", minion or master
     */
    public Piece(int x, int y, String player, String direction, String type) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.direction = direction;
        this.type = type;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void debug() {
        System.out.println("P (" + x + "," + y + ") " + "[" + player + "," + this.direction + "," + this.type + "]");
    }

    public String getChar() {
        // default values (for debugging)
        String color = ".";
        String character = "?";

        // piece color
        if (player.equals(Constants.PLAYER_1))
            color = Constants.GREEN;
        else if (player.equals(Constants.PLAYER_2))
            color = Constants.YELLOW;
        else if (player.equals(Constants.PLAYER_3))
            color = Constants.CYAN;
        else if (player.equals(Constants.PLAYER_4))
            color = Constants.PURPLE;

        // piece direction and type
        // vertical
        if (this.direction == Constants.VERTICAL) {
            if (this.type == Constants.MINION)
                character = Constants.MINION_VERTICAL;
            else if (this.type == Constants.MASTER)
                character = Constants.MASTER_VERTICAL;
        }

        // horizontal
        else if (this.direction == Constants.HORIZONTAL) {
            if (this.type == Constants.MINION)
                character = Constants.MINION_HORIZONTAL;
            else if (this.type == Constants.MASTER)
                character = Constants.MASTER_HORIZONTAL;
        }

        return (color + character + Constants.RESET);
    }

    // when any piece is moved, it rotates 90º
    public void rotate() {
        if (this.direction.equals(Constants.HORIZONTAL))
            this.direction = Constants.VERTICAL;
        else
            this.direction = Constants.HORIZONTAL;
    }

    // when a "Minion" piece reaches a corner, it becomes a "Master"
    public void promote() {
        this.type = Constants.MASTER;
    }

    @Override
    public String toString() {
        return "Piece("+x+","+y+","+direction+","+player+","+type+")";
    }
}
