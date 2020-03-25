package galamadriabuyak.util;

/**
 * A simple parser able to handle a basic command syntax:
 * A correct command is made of a keyword and a target ID when necessary.
 * @inv
 *      x := ((getLastCommand().equals(CMD_USE)
*       || getLastCommand().equals(CMD_HELP)
*       || getLastCommand().equals(CMD_ENDTURN))
*       && getLastTargetID() >= 0) 
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
    public static final String CMD_ENDTURN = "endturn";
   
    // REQUESTS
    
    /**
     * The last player command.
     * @pre
     *      isLastCommandLegal()
     */
    String getLastCommand();
  
    /**
     * The card ID associated with the last command.
     * @pre
     *      isLastCommandLegal()
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
     *          && getLastTargetID() == -1
     */
    void parseInput(String input);
}
