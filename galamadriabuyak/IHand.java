package galamadriabuyak;

/**
 * Models a player's hand. 
 * 
 * @pre 
 *      0 >= getSize() >= MAX_SIZE
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
    
}
