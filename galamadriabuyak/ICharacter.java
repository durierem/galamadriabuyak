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
 *      getHealth() == 0 ==> isDead()
 *      getHealth() > 0 ==> !isDead()
 *      getLevel() > 0
 *      getBasicAttack() != null
 *      getDeck() != null
 *      getHand() != null
 * @cons
 *      $DESC$ A new character with given arguments
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
     *      n >= 1 
     *      && n <= getDeck().getSize()
     *      && n <= IHand.MAX_SIZE - getHand().getSize()
     * @post
     *      getDeck().getSize() = old getDeck().getSize() - n
     *      getHand().getSize() = old getHand().getSize() + n
     */
    void draw(int n);
    
    /**
     * Sets the health of this character to q.
     * @pre
     *      q >= 0
     * @post
     *      getHealth() == q
     *      q == 0 => isDead()
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
     *      getHealth() == min(0, old getHealth() - q)
     *      getHealth() == 0 ==> isDead()
     */
    void setHealthDown(int q);
}
