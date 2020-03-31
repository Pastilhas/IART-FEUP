import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private Constants.GameState state = Constants.GameState.MENU_STATE;

    public Menu() throws IOException {
        this.drawTitle();
        this.drawOptions();
    }

    // Writes 'Pivit' on the console
    private void drawTitle() {
        int width = 100;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 14));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("P I V I T", 10, 20);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "0");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }

    }

    private void drawOptions() {
        System.out.println("**************************************************************");

        System.out.println("1 - Player VS Player, size 6");
        System.out.println("2 - Player VS Bot, size 6");
        System.out.println("3 - Bot VS Bot, size 6");
        System.out.println("4 - Player VS Player, size 8");
        System.out.println("5 - Player VS Bot, size 8");
        System.out.println("6 - Bot VS Bot, size 8");
        System.out.println("0 - Quit");
    }

    public char getOption() {
        char selection;
        Scanner input = new Scanner(System.in);

        selection = input.next().charAt(0);

        // if (input != null)
        //     input.close();

        return selection;
    }

    void setState(Constants.GameState state) {
        this.state = state;
    }

    Constants.GameState getState() {
        return this.state;
    }
}
