package galamadriabuyak;

public class Game {
    
    
    private void startFight(ICharacter c1, ICharacter c2) {
        
    }
    
    public static void main(String[] args) {
        IPlayer p = new Player();
        IMonster m = new Monster();
        startFight(p, m);
    }
}
