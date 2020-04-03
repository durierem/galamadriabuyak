package galamadriabuyak.util;

/**
 * A simple parser able to handle a basic command syntax.
 *
 * A correct command is made of command and a target ID when necessary.
 * The parser only handles the correctness of the command, and does not
 * perform actions by itself.
 * One can only retrieve a command if it is a legal command
 * (isLastCommandLegal() == true)
 *
 * @inv
 *      lc := the string representation of a legal command
 *      getLastCommand().equals(lc) <==> isLastCommandLegal()
 *
 * @cons
 *      $DESC$ An empty parser.
 *      $POST$
 *          !isLastCommandLegal()
 */
public interface IParser {
   
    // REQUESTS
    
    /**
     * The last player command.
     * @pre
     *      isLastCommandLegal()
     */
    String getLastCommand();
  
    /**
     * The target ID associated with the last command.
     * @pre
     *      isLastCommandLegal()
     *      && isLastCommandTargeted()
     */
    int getLastTargetID();
    
    /**
     * Checks if the last input given by the player is a correct command.
     */
    boolean isLastCommandLegal();

    /**
     * Checks if the last command requires a target ID.
     * @pre
     *       isLastCommandLegal()
     */
    boolean isLastCommandTargeted();
   
    // COMMANDS
    
    /**
     * Parse the given input.
     * @pre
     *      input != null
     */
    void parseInput(String input);
}
