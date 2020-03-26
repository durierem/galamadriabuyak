package galamadriabuyak;

public class TextInterface implements ITextInterface {
    
    // ATTRIBUTES
    
    private Game game;
    
    // CONSTRUCTOR
    
    public TextInterface(Game game) {
        this.game = game;
    }
    
    // COMMANDS
    
    public void draw() {
        clear();
        System.out.println(makeStringOfGame());
    }
    
    // TOOLS
    
    private static String makeStringOfGame() {
        return game.toString(); // temporary test
    }
    
    private static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
}
