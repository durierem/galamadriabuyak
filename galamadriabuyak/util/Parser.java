package galamadriabuyak.util;

/**
 * A simple parser able to handle a basic command syntax.
 *
 * The parser only handles the correctness of the command, and does not
 * perform actions by itself.
 * One can only retrieve a command if it is a legal command
 * (isLastCommandLegal() == true)
 *
 * @inv
 *      Let: lc := the string representation of a legal command
 *
 *      getLastCommand().equals(lc) <==> isLastCommandLegal()
 *      getLastTargetID() > 0
 *
 * @cons
 *      $DESC$ An empty parser.
 *      $POST$
 *          !isLastCommandLegal()
 */
public interface Parser {

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
     *      isLastCommandTargeted()
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

    /**
     * The list of all the available commands for this parser.
     */
    String getAllCommands();

    // COMMANDS

    /**
     * Parse the given input.
     * @pre
     *      input != null
     */
    void parseInput(String input);
}
