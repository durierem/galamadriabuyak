package galamadriabuyak.util;

/**
 * A simple parser able to handle 2 commands: "use" and "help".
 * @cons
 *      $DESC$ An empty parser.
 *      $POST$
 *          getLastCommand().equals("")
 *          getLastTargetID() == 0
 */

public interface IParser {
    
    // CONSTANTS
    
    public static final String CMD_USE = "use";
    public static final String CMD_HELP = "help";
   
    // REQUESTS
    
    /**
     * The last player command.
     */
    String getLastCommand();
  
    /**
     * The card ID associated with the last command.
     */
    int getLastTargetID();
   
    // COMMANDS
    
    /**
     * Parse the given input.
     * @pre
     *      input != null && !input.trims().equals("")
     * @post
     *      getLastCommand().equals("use") || getLastCommand().equals("help")
     *      getLastTargetID() >= 1
     */
    void parseInput(String input);
}
