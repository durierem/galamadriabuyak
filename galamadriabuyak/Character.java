package galamadriabuyak;

public abstract class Character implements ICharacter {
    
    // ATTRIBUTS

    private final String name;
    private int level;
    private int health;
    private IDeck deck;
    private IHand hand;
    private ICard basicAttack;
    

    // CONSTRUCTEUR

    Character() {
        name = "";
        health = 0;
        deck = new Deck();
    }

    // REQUÊTES
    
    public String getName() {
        return name;
    }
    
    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public IDeck getDeck() {
        return deck;
    }

    public IHand getHand(){
        return hand;    
    }
    
    public boolean isDead() {
        return getHealth() <= 0;
    }
    
    public ICard getBasicAttack(){
        return basicAttack;
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
    
    public void draw(int n){
        if (n < 1 || n > getDeck().getSize() 
            || n > getHand().MAX_SIZE - getHand().getSize()){
            throw new AssertionError();
        }
        getDeck().drawCard(getHand()); 
    }
}
