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
    int getSizeDeck();
   
    // Commands
    
    /**
     * Shuffle the deck.
     * 
     * @pre <pre>
     *      getSizeDeck() != 0
     *      
     * @post <pre>
     *      getSizeDeck() = old getSizeDeck()
     */
    void shuffleDeck();
    
    /**
     * Put the top card in the given hand.
     * 
     * @pre <pre>
     *      getSizeDeck() > 0
     *      
     * @post <pre>
     *      getSizeDeck() = old getSizeDeck - 1
     */
    void drawCard(IHand hand);
    
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
     *      getSizeDeck() > 0
     *      
     * @post <pre>
     *      getSizeDeck() = old getSizeDeck() - 1
     */
    void rmTopCard();
}
