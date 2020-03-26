package galamadriabuyak;

public class Player extends Character implements IPlayer {
    // Attributes
    
    private final String name;
    private int level;
    private int money;
    
    // Constructor
    
    public Player(String name, int level, int money) {
        super();
        
        if (name == null || level < 0 ||money < 0) {
            throw new AssertionError();
        }
        this.name = name;
        this.level = level;
        this.money = money;
    }
    
    public String getName() {
        return name;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getMoney() {
        return money;
    }
}
