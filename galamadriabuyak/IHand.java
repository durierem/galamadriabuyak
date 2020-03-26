package galamadriabuyak;

/**
 * Models a player's hand. 
 * 
 * @pre 
 *      0 >= getSize() >= MAX_SIZE
 *      
 * @cons
 *      $DESC$ A new empty  hand
 *      $ARGS$
 *      $PRE$
 *      $POST$
 *          getSize() == 0
 */
public interface IHand
{
    // Constants
    
    final static int MAX_SIZE = 3;
    
    // Requests
    
    /**
     * Returns the size of the hand.
     */
    int getSize();
    
    /**
     * Returns the nth card of the hand.
     * 
     * @pre 
     *      0 <= n <= getSize() - 1
     * @post
     *      getSize() = old getSize()
     */
    ICard getCard(int n);
    
    // COMMANDES
    
    /**
     * Set the size of hand
     * 
     * @pre 
     *      0 <= n <= MAX_SIZE
     */
    public int setSize(int n);
    
    /**
    *  Delete the Nth card of the deck
    *  
    *  @pre
    *       0 <= n <= getSize() - 1
    *  @post
    *       getSize() = old getSize() - 1
    *       the Nth card was deleted
    *       the Cards at positon Nth + x in handTab was reposition on Nth + x - 1
    */
    void deleteCard(int n);
    
    /**
     *  Add card to the hand. At the first available position.
     *  
     *  @pre 
     *      card != null
     *      getSize() <= 2
     *      
     *  @post
     *      getSize() = old getSize() + 1
     */
    public void addCard(ICard card);
}
