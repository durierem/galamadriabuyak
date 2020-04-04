package galamadriabuyak;

/**
 * Models a deck of size getSizeDeck(). 
 * You can shuffle it or draw the top card.
 * 
 * @inv
 *      getSize() >= 0;
 *      0 <= getSize() <= MAX_SIZE
 */
public interface IDeck {
    
    // CONSTANTS
    
    final int MAX_SIZE = 100;
    
    // REQUESTS
    
    /**
     * Gives the size of the deck.
     */
    int getSize();
   
    // COMMANDS
    
    /**
     * Add the given card to the deck.
     * 
     * @pre
     *      c != null
     *      
     * @post
     *      getSizeDeck() = old getSizeDeck + 1
     */
    void addCard(ICard c);
    
    /**
     * Remove from the deck an occurence of the card named by m.
     * 
     * @pre
     *      getSize() > 0
     *      
     * @post
     *      getSize() = old getSizeDeck() - 1
     */
    void rmTopCard();
    
    /**
     * Put the top card in the given hand.
     * 
     * @pre
     *      hand != null
     *      getSize() > 0
     *      hand.getSize() < IHand.MAX_SIZE
     * @post
     *      getSizeDeck() = old getSizeDeck - 1
     *      hand.getSize() = old hand.getSize() + 1
     */
    void drawCard(IHand hand);
    
    /**
     * Shuffle the deck.
     * 
     * @pre
     *      getSize() >= 0
     *      
     * @post
     *      getSize() = old getSizeDeck()
     */
    void shuffleDeck();

    /**
     * Add n randomly picked cards from possibleCards in this deck.
     * @pre
     *      n > 0 && n <= MAX_SIZE - getSize()
     *      possibleCards != null && possibleCards.length > 0
     * @post
     *      getSize() = old getSize() + n
     */
    void randomFill(int n, final ICard[] possibleCards);
}
