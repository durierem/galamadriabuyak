package galamadriabuyak;

/**
 * Represents a character of the game.
 *
 * A character is composed of his name, his remaining health points, his
 * current level, his basic attack, his deck and his hand.
 * When a character's health points fall under zero, he dies.
 * @inv
 *      getName() != null
 *      getHealth() >= 0
 *      isDead() <==> (getHealth() == 0)
 *      getLevel() > 0
 *      getBasicAttack() != null
 *      getDeck() != null
 *      getHand() != null
 * @cons
 *      $DESC$
 *          A new character with given arguments.
 *      $ARGS$
 *          String name
 *          int level
 *          int health
 *          ICard basicAttack
 *          IDeck deck
 *          IHand hand
 *      $PRE$
 *          name != null
 *          level > 0
 *          health > 0
 *          basicAttack != null
 *          deck != null
 *          hand != null
 *      $POST$
 *          getName() == name.trim()
 *          getLevel() == level
 *          getHealth() == health
 *          getBasicAttack() == basicAttack
 *          getDeck() == deck
 *          getHand() == hand
 */
public interface ICharacter {

    // REQUESTS

    /**
     * The name of this character.
     */
    String getName();

    /**
     * The level of this character.
     */
    int getLevel();

    /**
     * The remaining health of this character.
     */
    int getHealth();

    /**
     * The basic attack of this character.
     */
    ICard getBasicAttack();

    /**
     * The hand of this character.
     */
    IHand getHand();

    /**
     * The n-th card in the hand of this character.
     * @pre
     *      1 <= n <= IHand.MAX_SIZE
     */
    ICard getHand(int n);

    /**
     * The deck of this character.
     */
    IDeck getDeck();

    /**
     * Checks if this character is dead.
     */
    boolean isDead();

    // COMMANDS

    /**
     * Draws n cards for this character.
     * @pre
     *      1 <= n <= max(getDeck().getSize(),
     *                      IHand.MAX_SIZE - getHand().getSize())
     * @post
     *      getDeck().getSize() == old getDeck().getSize() - n
     *      getHand().getSize() == old getHand().getSize() + n
     */
    void draw(int n);

    /**
     * Sets the health of this character to q.
     * @pre
     *      q >= 0
     * @post
     *      getHealth() == q
     */
    void setHealthTo(int q);

    /**
     * Increase the health of this character by q.
     * @pre
     *      q >= 0
     * @post
     *      getHealth() == old getHealth() + q
     */
    void setHealthUp(int q);

    /**
     * Lowers the health of this character by q.
     * @pre
     *      q >= 0
     * @post
     *      getHealth() == max(0, old getHealth() - q)
     */
    void setHealthDown(int q);

    /**
     * Fills the hand of this player with the top cards from his deck.
     * @post
     *      Let:    dsize ::= getDeck().getSize()
     *              hsize ::= getHand().getSize()
     *      hsize == max(IHand.MAX_SIZE, old dsize - old hsize)
     *      dsize == old dsize - max(old dsize, IHand.MAX_SIZE - old hsize)
     */
    void fillHand();
}
