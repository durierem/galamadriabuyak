package galamadriabuyak;

/**
 * Models a deck of size getSizeDeck(). 
 * You can shuffle it or draw the top card.
 * 
 * @inv <pre>
 *      getSizeDeck() >= 0;
 *      0 <= getSizeDeck() <= MAX_CARD
 */
public interface IDeck {
    // Constants
    
    int MAX_CARD = 100;
    
    // Requests
    
    /**
     * Gives the size of the deck.
     */
    int getSize();
   
    // Commands
    
    /**
     * Add the given card to the deck.
     * 
     * @pre <pre>
     *      c != null
     *      
     * @post <pre>
     *      getSizeDeck() = old getSizeDeck + 1
     */
    void addCard(ICard c);
    
    /**
     * Remove from the deck an occurence of the card named by m.
     * 
     * @pre <pre>
     *      getSize() > 0
     *      
     * @post <pre>
     *      getSize() = old getSizeDeck() - 1
     */
    void rmTopCard();
    
    /**
     * Put the top card in the given hand.
     * 
     * @pre <pre>
     *      hand != null
     *      getSize() > 0
     *      hand.getSize() < IHand.MAX_SIZE
     * @post <pre>
     *      getSizeDeck() = old getSizeDeck - 1
     *      hand.getSize() = old hand.getSize() + 1
     */
    void drawCard(IHand hand);
    
    /**
     * Shuffle the deck.
     * 
     * @pre <pre>
     *      getSize() != 0
     *      
     * @post <pre>
     *      getSize() = old getSizeDeck()
     */
    void shuffleDeck();
}
