package galamadriabuyak;

/**
 * Models a player's hand. 
 * 
 * @pre 
 *      0 >= getSize() >= MAX_HAND
 */
public interface IHand
{
    // Constants
    
    int MAX_HAND = 3;
    
    // Requests
    
    /**
     * Returns the size of the hand.
     * Without counting the basic attack.
     */
    int getSize();
    
    /**
     * Returns the nth card of the hand.
     * n = 0 is the basic attack.
     * 
     * @pre 
     *      0 >= n >= getSize()
     * @post
     *      getSize() = old getSize()
     */
    ICard getCard(int n);
    
    
}
