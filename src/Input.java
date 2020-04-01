import java.io.Console;

public class Input {
    /**
     * Get user input for menu
     *
     * @return character written by user
     */
    public static char getMenuOption() {
        char selection;
        Console console = System.console();

        String input = console.readLine("Option: ");

        selection = input.charAt(0);
        return selection;

    }

    /**
     * Get coordinates from user input
     *
     * @return array of coordinates
     */
    public static int[] getCoordinates() {
        Console console = System.console();
        int[] coordinates = new int[2];

        while (true) {
            try {
                String input = console.readLine("Coordinates 'x y': ");

                coordinates[0] = input.charAt(0) - '0';
                coordinates[1] = input.charAt(2) - '0';

                if (coordinates[0] < 0 || coordinates[1] > 8)
                    throw new StringIndexOutOfBoundsException();

                System.out.println("Coordinates entered: " + coordinates[0] + " " + coordinates[1] + "\n");

                return coordinates;

            } catch (NumberFormatException e) {
                System.out.println("Input must be a number.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Input must be the 'x' and the 'y'.");
            }
        }
    }

    /**
     * Get distance from user input
     *
     * @return integer representing distance
     */
    public static int getDistance() {
        Console console = System.console();
        int distance;

        System.out.println("Distance");
        System.out.println("Positive numbers for right and down.");
        System.out.println("Negative numbers for left and up.");

        while (true) {
            try {
                String input = console.readLine("Number of cells to move: ");

                distance = Integer.parseInt(input);
                System.out.println("Distance entered: " + distance + "\n");

                return distance;

            } catch (NumberFormatException e) {
                System.out.println("Input must be a number.");
            }
        }
    }

}
