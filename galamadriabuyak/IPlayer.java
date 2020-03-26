package galamadriabuyak;

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
}
