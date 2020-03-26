package galamadriabuyak;
import java.util.Stack;

public class Deck implements IDeck {
    
    // Attributes
    
    private int size;
    private Stack deck;
    
    //Constructor
    
    public Deck() {
        size = 0;
        deck = new Stack();
    }
    
    // Requestes
    
    public int getSize() {
        return size;
    }
    
    // Commands
    
    public void addCard(ICard c) {
        if (c == null) {
            throw new AssertionError();
        }
        
        deck.push(c);
        size += 1;
    }
    
    public void rmTopCard() {
        if (getSize() <= 0) {
            throw new AssertionError();
        }
        
        deck.pop();
        size -= 1;
    }
    
    public void drawCard(IHand hand) {
        if (hand == null || getSize() <= 0
            || hand.getSize() >= IHand.MAX_SIZE) {
                throw new AssertionError();
            }
            
        hand.addCard((ICard) deck.pop());
        size -= 1;
    }
    
    /**
     * Un nombre aléatoire compris entre a et b (au sens large).
     * @pre <pre>
     *     0 < a <= b </pre>
     * @post <pre>
     *     a <= result <= b </pre>
     */
    private static int alea(int a, int b) {
        assert (a > 0) && (b >= a);
        return a + (int) (Math.random() * (b - a + 1));
    }
    
    public void shuffleDeck() {
        if (size <= 0) {
            throw new AssertionError();
        }
        int random_factor = 7;
        Stack[] bufStack = new Stack[random_factor];
        
        //  Splitting the deck in random_factor stacks
        //  Card by card we randomly choose one of the stack and 
        //  pushing in the card.
        while (!deck.empty()) {
            bufStack[alea(0, random_factor - 1)].push(deck.pop());
        }
        
        //  Assembling all the stacks in one deck.
        for (int i = 0; i < random_factor; ++i) {
            while (!bufStack[i].empty()) {
                deck.push(bufStack[i].pop());
            }
        }
    }
}
