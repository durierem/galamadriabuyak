package galamadriabuyak;

public class Game {
    
    public Game() {
        Parser parser = new Parser();
        IPlayer player = new Player();
    }
    
    private void startFight(IPlayer player, ICharacter enemy) {
        while (!player.isDead() && !enemy.isDead()) {
            player.waitForInput();
            //...
        }
    }
    
    public static void main(String[] args) {
        new Game();
    }
}
