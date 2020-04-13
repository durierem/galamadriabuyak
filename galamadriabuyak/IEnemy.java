package galamadriabuyak;

/**
 * Represents an enemy.
 *
 * An enemy is essentially a character which can perform a turn.
 */
public interface IEnemy extends ICharacter {

    // COMMANDS

    /**
     * Plays a turn for this enemy.
     * @pre
     *      game != null
     */
    void performTurn(Game game);
}
