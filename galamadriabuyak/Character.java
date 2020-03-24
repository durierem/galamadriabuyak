package galamadriabuak;

 

  

public abstract class Character implements ICharacter {
    
    // ATTRIBUTS

    private final String name;
    private int health;
    private IDeck deck;
    private IBasicAttack basicAccack;

    // CONSTRUCTEUR

    Character() {
        name = "";
        health = 0;
        deck = null;
        basicAttack = null;
    }

    // REQUÊTES
    
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public IDeck getDeck() {
        return deck;
    }

    public IBasicAttack getBasicAttack() {
        return basicAttack;
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }

    // COMMANDES
    
    public void setHealthTo(int q) {
        if (q < 0) {
          throw new AssertionError();
        }
        health = q;
    }

    public void setHealthUp(int q) {
        if (q < 0 ) {
            throw new AssertionError();
        }
        health += q;
    }

    public void setHealthDown(int q) {
        if (q < 0) {
          throw new AssertionError();
        }
        health -= q;
    }

}
