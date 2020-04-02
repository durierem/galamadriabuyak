package galamadriabuyak;

import galamadriabuyak.util.IParser;

/**
 * Models the player.
 * @inv 
 *      getMoney() >= 0
 * @cons
 *      $DESC$ Creates a new Player.
 *      $ARGS$ money
 *      $PRE$ money >= 0
 */
public interface IPlayer extends ICharacter {
  
    // REQUESTS
    
    /**
     * The money of this player.
     */
    int getMoney();
    
    // COMMANDS
    
    /**
     * Sets the money of this player to q.
     * @pre
     *      q >= 0
     * @post
     *      getMoney() == q
     */
    void setMoneyTo(int q);

    /**
     * Increase the money of this player by q.
     * @pre
     *      q >= 0
     * @post
     *      getMoney() == old getMoney() + q
     */
    void setMoneyUp(int q);

    /**
     * Lowers the money of this player by q.
     * @pre
     *      q >= 0
     * @post
     *      getMoney() == old getMoney() - q
     */
    void setMoneyDown(int q);
    
    /**
     * Performs the player's turn.
     * 
     * @pre 
     *      !isDead();
     */
    void performTurn(Game game, IParser combatParser);
}
