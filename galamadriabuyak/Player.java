package galamadriabuyak;

public class Player extends Character implements IPlayer {
    // Attributes
    
    private int money;
    
    // Constructor
    
    public Player(int money) {
        super();
        
        if (money < 0) {
            throw new AssertionError();
        }
        this.money = money;
    }
    
    public int getMoney() {
        return money;
    }
}
