package galamadriabuyak;

public abstract class Character implements ICharacter {
    
    // ATTRIBUTS

    private final String name;
    private int level;
    private int health;
    private ICard basicAttack;
    private IDeck deck;
    private IHand hand;    

    // CONSTRUCTEUR

    public Character(String name, int level, int health, ICard basicAttack,
        IDeck deck, IHand hand) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.basicAttack = basicAttack;
        this.deck = deck;
        this.hand = hand;
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
