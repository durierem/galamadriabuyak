package galamadriabuyak;

public class Hand implements IHand {

    // ATTRIBUTES

    private ICard[] data;
    private int size;

    // CONSTRUCTORS

    public Hand() {
        data = new ICard[MAX_SIZE];
        size = 0;
    }

    // REQUESTS

    public int getSize() {
        return size;
    }

    public ICard getCard(int n) {
        if (n <= 0 || n > getSize()) {
            throw new AssertionError();
        }
        return data[n - 1];
    }
    
    // COMMANDS

    public void setSize(int n) {
        if (n < 0 || n > MAX_SIZE) {
            throw new AssertionError();
        }
        size = n;
    }
    
    public void deleteCard(int n) {
        if (n < 1 || n > getSize()) {
            throw new AssertionError();
        }
        for (int i = n; i < MAX_SIZE; i++) {
            data[i] = data[i + 1];
        }
        data[getSize() - 1] = null;
        size = getSize();
    }
    
    /**
     *  Adds card to this hand.
     *  @pre 
     *      card != null
     *      getSize() < MAX_SIZE
     *  @post
     *      getSize() = old getSize() + 1
     */
    public void addCard(ICard card) {
        if (getSize() >= MAX_SIZE || card == null) {
            throw new AssertionError();
        }
        data[getSize()] = card;
        size += 1;
    }
}
