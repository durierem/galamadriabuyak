package galamadriabuyak.util;

/**
 * A parser to handle all the commands related to a fight.
 *
 * @inv
 *      x := (getLastCommand().equals(CMD_USE)
 *              || getLastCommand().equals(CMD_HELP)
 *              && getLastTargetID() >= 0)
 *              || (getLastCommand().equals(CMD_ENDTURN)
 *              || getLastCommend().equals(CMD_EXIT))
 *           
 *      x ==> isLastCommandLegal()
 */
public interface ICombatParser extends IParser {
    
    // CONSTANTS

    /**
     * The commands to use a card and to get details about a card.
     */
    public static final String CMD_USE = "use"; // Use a card
    public static final String CMD_HELP = "help"; // Get details about a card
    public static final String CMD_ENDTURN = "endturn"; // End the turn
    public static final String CMD_EXIT = "exit"; // Exit the game
}
