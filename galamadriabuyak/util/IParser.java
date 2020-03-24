package galamadriabuyak.util;

/**
 * A simple parser able to handle 2 commands: "use" and "help".
 * @inv
 *      x := ((getLastCommand().equals(CMD_USE)
*       || getLastCommand().equals(CMD_HELP))
*       && getLastTargetID() >= 1) 
 *      x <==> isLastCommandLegal()
 * @cons
 *      $DESC$ An empty parser.
 *      $POST$
 *          !isLastCommandLegal()
 */

public interface IParser {
    
    // CONSTANTS
    /**
     * The commands to use a card and to get details about a card.
     */
    public static final String CMD_USE = "use";
    public static final String CMD_HELP = "help";
   
    // REQUESTS
    
    /**
     * The last player command.
     * @pre
     *      isLastInputLegal()
     */
    String getLastCommand();
  
    /**
     * The card ID associated with the last command.
     * @pre
     *      isLastInputLegal()
     */
    int getLastTargetID();
    
    /**
     * Checks if the last input given by the player is a correct command.
     */
    boolean isLastCommandLegal();
   
    // COMMANDS
    
    /**
     * Parse the given input.
     * @pre
     *      input != null && !input.trims().equals("")
     * @post
     *      !isLastCommandLegal() ==>
     *          getLastCommand().equals("")
     *          && getLastTargetID() == 0
     */
    void parseInput(String input);
}
