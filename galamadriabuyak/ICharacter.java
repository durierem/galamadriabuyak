package galamadriabuyak;
   
/**
 * Represents a character of the game.
 */
public interface ICharacter {
    
    // REQUÊTES

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
     * The hand of this character.
     */
    IHand getHand();
    
    /**
     * The current deck of this character.
     */
    IDeck getDeck();

    /**
     * Tells if this character is dead.
     */
    boolean isDead();

    // COMMANDES
    
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
     *      getHealth() == old getHealth() - q
     */
    void setHealthDown(int q);
}
