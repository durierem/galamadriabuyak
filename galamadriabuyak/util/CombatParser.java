package galamadriabuyak.util;

import java.util.StringTokenizer;
import java.util.NoSuchElementException;

/**
 * A parser to handle all the commands related to a fight.
 *
 * @inv
 *      x := (getLastCommand().equals(CMD_USE)
 *              || getLastCommand().equals(CMD_HELP)
 *              && getLastTargetID() > 0)
 *              || (getLastCommand().equals(CMD_ENDTURN)
 *              || getLastCommend().equals(CMD_EXIT))
 *           
 *      x ==> isLastCommandLegal()
 */
public class CombatParser implements IParser {

    // CONSTANTS

    public static final String CMD_USE = "use"; // Use a card
    public static final String CMD_HELP = "help"; // Get details about a card
    public static final String CMD_ENDTURN = "endturn"; // End the turn
    public static final String CMD_EXIT = "exit"; // Exit the game
    public static final String CMD_SKILL = "skill"; // Use the basic attack

    // ATTRIBUTES
    
    private String command;
    private int targetID;
    
    // CONSTRUCTOR
    
    public CombatParser() {
        command = "";
        targetID = 0;
    }
    
    // REQUESTS
    
    public String getLastCommand() {
        if (!isLastCommandLegal()) {
            throw new AssertionError();
        }
        return command;
    }
    
    public int getLastTargetID() {
        if (!isLastCommandLegal() || !isLastCommandTargeted()) {
            throw new AssertionError();
        }
        return targetID;
    }

    public boolean isLastCommandLegal() {
        return ((command.equals(CMD_USE)
            || command.equals(CMD_HELP))
            && targetID > 0)
            || (command.equals(CMD_SKILL)
            || command.equals(CMD_ENDTURN)
            || command.equals(CMD_EXIT));
    }

    public boolean isLastCommandTargeted() {
        if (!isLastCommandLegal()) {
            throw new AssertionError();
        }
        return command.equals(CMD_USE) || command.equals(CMD_HELP);
    }

    // COMMANDS
    
    public void parseInput(String input) {
        if (input == null || input.trim().equals("")) {
            throw new AssertionError();
        }
        StringTokenizer st = new StringTokenizer(input);
        try {
            String c = st.nextToken();
            if (isCommandLegal(c)) {
                command = c;
                if (isCommandTargeted(command)) {
                    try {
                        targetID = Integer.parseInt(st.nextToken());
                    } catch (NumberFormatException e) {
                        targetID = 0;
                    }
                }
            }            
        } catch (NoSuchElementException e) {
            command = "";
            targetID = 0;
        }
    }

    // TOOLS

    /**
     * Checks if the given input is a legal command.
     */
    private boolean isCommandLegal(String input) {
        return input.equals(CMD_USE)
            || input.equals(CMD_HELP)
            || input.equals(CMD_ENDTURN)
            || input.equals(CMD_EXIT)
            || input.equals(CMD_SKILL);
    }

    /**
     * Checks if the given command is a targeted command.
     * @pre
     *      isCommandLegal(command)
     */
    private boolean isCommandTargeted(String command) {
        assert isCommandLegal(command);
        return command.equals(CMD_USE) || command.equals(CMD_HELP);
    }
}
