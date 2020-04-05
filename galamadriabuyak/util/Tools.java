package galamadriabuyak.util;

import java.util.Scanner;
import java.io.IOException;

public abstract class Tools {

    public static void waitForInput(Parser wparser){
        Scanner scanner = new Scanner(System.in);
        System.out.print(" > ");
        wparser.parseInput(scanner.nextLine());
        while (!wparser.isLastCommandLegal()) {
            System.out.print(" > ");
            wparser.parseInput(scanner.nextLine());
        }
    }
    
    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO().start().waitFor();
            } else {
               Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            // ...
        }
    }
    
    public static void drawInterface(String s) {
        clear();
        System.out.println(s);
    }
    
}