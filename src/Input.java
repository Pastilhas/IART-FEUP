import java.io.Console;

public class Input {

    public int[] getCoordinates() {
        Console console = System.console();
        int[] coordinates = new int[2];

        while (true) {
            try {
                String input = console.readLine("Coordinates 'x y': ");

                coordinates[0] = input.charAt(0) - '0';
                coordinates[1] = input.charAt(2) - '0';

                if (coordinates[0] < 0 || coordinates[1] > 8)
                    throw new StringIndexOutOfBoundsException();

                System.out.println("numbers entered : " + coordinates[0] + " " + coordinates[1]);

                return coordinates;

            } catch (NumberFormatException e) {
                System.out.println("Input must be a number.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Input must be the 'x' and the 'y'.");
            }
        }
    }
}