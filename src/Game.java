import java.io.IOException;

public class Game {
	public static void main(String[] args) throws InterruptedException, IOException {

		int size = 0;
		Constants.GameMode gameMode = Constants.GameMode.PVP;
		Constants.GameState current_state = Constants.GameState.MENU_STATE;

		while (true) {
			switch (current_state) {
				case MENU_STATE:
					Menu menu = new Menu();

					switch (menu.getOption()) {
						case '1':
							size = 6;
							gameMode = Constants.GameMode.PVP;
							current_state = Constants.GameState.MAINGAME_STATE;
							break;
						case '2':
							size = 6;
							gameMode = Constants.GameMode.PVB;
							current_state = Constants.GameState.MAINGAME_STATE;
							break;
						case '3':
							size = 6;
							gameMode = Constants.GameMode.BVB;
							current_state = Constants.GameState.MAINGAME_STATE;
							break;
						case '4':
							size = 8;
							gameMode = Constants.GameMode.PVP;
							current_state = Constants.GameState.MAINGAME_STATE;
							break;
						case '5':
							size = 8;
							gameMode = Constants.GameMode.PVB;
							current_state = Constants.GameState.MAINGAME_STATE;
							break;
						case '6':
							size = 8;
							gameMode = Constants.GameMode.BVB;
							current_state = Constants.GameState.MAINGAME_STATE;
							break;
						case '0':
							current_state = Constants.GameState.EXIT;
							break;
						default:
							System.out.println("Invalid input, try again!");
							break;
					}
					break;
				case MAINGAME_STATE:
					if (size == 0) {
						System.out.println("Something went wrong, exiting...");
						System.exit(1);
					}

					Pivit game = new Pivit(size, gameMode);

					current_state = game.run();

					break;
				case EXIT:
					System.out.println("Exiting...");
					Thread.sleep(500);
					System.exit(0);
					break;
			}
		}

	}
}