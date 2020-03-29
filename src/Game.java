public class Game {
    public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		Pivit game = new Pivit(size);
		run(game);
	}

	public static void run(Pivit game) {
		game.printBoard();
		Minimax A = new Minimax(Constants.PLAYER_1, game);
		A.generateChildMoves(A.startMove, 4);
		A.printMoves(A.startMove, 0);
	}
}