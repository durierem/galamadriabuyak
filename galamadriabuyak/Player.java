package galamadriabuyak;

public class Player extends Character implements IPlayer {
    // Attributes
    
    private int money;
    
    // Constructor
    
    public Player(String name, int level, int health, ICard basicAttack,
        IDeck deck, IHand hand, int money) {
        super(name, level, health, basicAttack, deck, hand);
        if (money < 0) {
            throw new AssertionError();
        }
        this.money = money;
    }
    
    // Requests
    
    public int getMoney() {
        return money;
    }
    
    // Commands
    
    public void setMoneyTo(int q) {
        if (q < 0) {
          throw new AssertionError();
        }
        money = q;
    }

    public void setMoneyUp(int q) {
        if (q < 0 ) {
            throw new AssertionError();
        }
        money += q;
    }

    public void setMoneyDown(int q) {
        if (q < 0) {
          throw new AssertionError();
        }
        money -= q;
    }
}
