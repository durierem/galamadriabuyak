package galamadriabuyak;

/**
 * Represents a player's hand.
 *
 * @inv
 *      0 <= getSize() <= MAX_SIZE
 * @cons
 *      $DESC$ A new empty hand
 *      $POST$
 *          getSize() == 0
 */
public interface IHand {

    // CONSTANTS

    /**
     * The maximum size of a hand.
     */
    int MAX_SIZE = 3;

    // REQUESTS

    /**
     * The current size of this hand.
     */
    int getSize();

    /**
     * The nth card of this hand.
     * @pre
     *      0 < n <= getSize()
     * @post
     *      getSize() = old getSize()
     */
    ICard getCard(int n);

    // COMMANDS

    /**
     * Sets the current size of this hand to n.
     * @pre
     *      0 <= n <= MAX_SIZE
     */
    void setSize(int n);

    /**
     *  Deletes the nth card of this hand.
     *  @pre
     *       1 <= n <= getSize()
     *  @post
     *       getSize() = old getSize() - 1
     *       forall i:[1...MAX_SIZE]:
     *          if i >= n:
     *              getCard(i) = old getCard(i + 1)
     *          else:
     *              getCard(i) = old getCard(i)
     */
    void deleteCard(int n);
    
    /**
     *  Adds card to this hand.
     *  @pre 
     *      card != null
     *      getSize() < MAX_SIZE
     *  @post
     *      getSize() = old getSize() + 1
     */
    void addCard(ICard card);
}
