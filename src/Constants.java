
public final class Constants {

    public enum GameState {
        MENU_STATE,
        MAINGAME_STATE,
        EXIT
    }

    public enum GameMode {
        PVP,
        PVB,
        BVB
    }

    // ---- PIECE ----
    // background color
    public static final String RED_BACKGROUND = "";
    public static final String BLACK_BACKGROUND = "";

    // ---- PIECE ----
    // colors
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RESET = "\u001B[0m";

    // ASCII
    public static final String MINION_HORIZONTAL = "H";
    public static final String MINION_VERTICAL = "V";

    public static final String MASTER_HORIZONTAL = "=";
    public static final String MASTER_VERTICAL = "!";

    // Direction
    public static final String HORIZONTAL = "x";
    public static final String VERTICAL = "y";

    // Type (minion or Master)
    public static final String MINION = "m";
    public static final String MASTER = "M";

    // Player
    public static final String PLAYER_1 = "GREEN";
    public static final String PLAYER_2 = "YELLOW";
    public static final String PLAYER_3 = "CYAN";
    public static final String PLAYER_4 = "PURPLE";
}
