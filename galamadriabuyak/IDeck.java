package galamadriabuyak;

/**
 * Models a deck of size getSize(). 
 * You can shuffle it or draw a maximum of MAX_DRAW number of cards. 
 * Notice that getNbCard is the current number of cards.
 * 
 * @inv <pre>
 *      getSize() >= 0;
 *      0 <= getNbCard() <= getSize()
 */
public interface IDeck {
    
    // Constants
    
    // Requests
    
    /**
     * Gives the size of the deck.
     */
    int getSizeDeck();
    
    /**
     * Gives the current card number in the deck.
     */
    int getNbCard();
    
    
    // Commands
    
    /**
     * Shuffle the deck.
     * 
     * @pre <pre>
     *      getNbCard() != 0
     *      
     * @post <pre>
     *      getNbCard() = old getNbCard()
     */
    void shuffleDeck();
    
    /**
     * Draw one card of the deck.
     * 
     * @pre <pre>
     *      getNbCard() > 0
     *      
     * @post <pre>
     *      getNbCard() = old getNbCard - 1
     */
    ICard drawCard();
    
    /**
     * Add the given card to the deck.
     * 
     * @pre <pre>
     *      c != null
     *      
     * @post <pre>
     *      getNbCard() = old getNbCard + 1
     */
    void addCard(ICard c);
    
    /**
     * Remove from the deck an occurence of the card named by m.
     * 
     * @pre <pre>
     *      m != null
     *      
     * @post <pre>
     *      getNbCard() = old getNbCard - 1
     */
    void rmCard(String m);
}
