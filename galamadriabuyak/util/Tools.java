package galamadriabuyak.util;

import java.util.Scanner;
import java.io.IOException;

public class Tools {
    
    private Tools() {
        
    }
    
    public static void waitForInput(IParser wparser){
        Scanner scanner = new Scanner(System.in);
        wparser.parseInput(scanner.nextLine());
        while (!wparser.isLastCommandLegal()) {
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