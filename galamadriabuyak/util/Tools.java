package galamadriabuyak.util;

import java.io.IOException;

public abstract class Tools {

    /**
     * Clears the terminal.
     */
    public static void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            System.exit(1);
        }
    }

    /**
     * Returns a random number between min and max inclusive.
     * @pre
     *     0 <= min <= max
     * @post
     *     min <= result <= max
     */
    public static int alea(int min, int max) {
        if (min < 0 || max < min) {
            throw new AssertionError();
        }

        return min + (int) (Math.random() * (max - min + 1));
    }
}