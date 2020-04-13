package galamadriabuyak;

/**
 * Represents a character's hand.
 *
 * A hand contains a maximum of MAX_SIZE cards and can also be empty. Cards are
 * added at the end of a hand. When a card is deleted from a hand, cards are
 * rearranged in a way that they all stack up at the beginning of the hand.
 * @inv
 *      0 <= getSize() <= MAX_SIZE
 * @cons
 *      $DESC$
 *          A new empty hand.
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
     *      1 <= n <= getSize()
     * @post
     *      getSize() == old getSize()
     */
    ICard getCard(int n);

    // COMMANDS

    /**
     *  Deletes the nth card of this hand.
     *  @pre
     *      1 <= n <= getSize()
     *  @post
     *      getSize() == old getSize() - 1
     *      forall i in [1...n - 1]: getCard(i) == old getCard(i)
     *      forall i in [n...MAX_SIZE]: getCard(i) == old getCard(i + 1)
     */
    void deleteCard(int n);

    /**
     *  Adds card to this hand.
     *  @pre
     *      card != null
     *      getSize() < MAX_SIZE
     *  @post
     *      getSize() == old getSize() + 1
     */
    void addCard(ICard card);
}
