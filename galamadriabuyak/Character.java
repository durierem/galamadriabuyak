package galamadriabuyak;

public abstract class Character implements ICharacter {

    // ATTRIBUTES

    private final String name;
    private int level;
    private int health;
    private ICard basicAttack;
    private IDeck deck;
    private IHand hand;
    private boolean isDead;

    // CONSTRUCTORS

    public Character(String name, int level, int health, ICard basicAttack,
            IDeck deck, IHand hand) {
        this.name = name.trim();
        this.level = level;
        this.health = health;
        this.basicAttack = basicAttack;
        this.deck = deck;
        this.hand = hand;
        this.isDead = false;
    }

    // REQUESTS

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

    public IHand getHand() {
        return hand;
    }

    public ICard getHand(int n) {
        return getHand().getCard(n);
    }

    public boolean isDead() {
        return isDead;
    }

    public ICard getBasicAttack() {
        return basicAttack;
    }

    // COMMANDS

    public void setHealthTo(int q) {
        if (q < 0) {
            throw new AssertionError();
        }

        health = q;
        if (health == 0) {
            isDead = true;
        }
    }

    public void setHealthUp(int q) {
        if (q < 0) {
            throw new AssertionError();
        }

        health += q;
    }

    public void setHealthDown(int q) {
        if (q < 0) {
            throw new AssertionError();
        }

        if (q >= health) {
            health = 0;
            isDead = true;
        } else {
            health -= q;
        }
    }

    public void draw(int n) {
        if (n < 1 || n > getDeck().getSize()
                || n > getHand().MAX_SIZE - getHand().getSize()) {
            throw new AssertionError();
        }

        for (int i = 0; i < n; i++) {
            getDeck().drawCard(getHand());
        }
    }

    public void fillHand() {
        final int deckSize = getDeck().getSize();
        final int drawNumber = IHand.MAX_SIZE - getHand().getSize();
        if (deckSize > 0) {
            if (drawNumber > deckSize) {
                draw(deckSize);
            } else if (drawNumber > 0) {
                draw(drawNumber);
            }
        }
    }
}
