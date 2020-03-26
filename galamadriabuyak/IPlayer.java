package galamadriabuyak;

/**
 * Models the player.
 * @inv 
 *      getName() != null
 *      getLevel() >= 0
 *      getMoney() >= 0
 * @pre
 *      name != null
 *      level >= 0
 *      money >= 0
 */
public interface IPlayer extends ICharacter {
    
    // REQUESTS
    
    /**
     * The name of this player.
     */
    String getName();
    
    /**
     * The level of this player.
     */
    int getLevel();
    
    /**
     * The money of this player.
     */
    int getMoney();

}
