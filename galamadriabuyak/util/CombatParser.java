package galamadriabuyak.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

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

    public static final String CMD_CARD = "use card"; // Use a card
    public static final String CMD_SKILL = "use skill"; // Use the basic attack
    public static final String CMD_HELP = "help card"; // Get details about a card
    public static final String CMD_ENDTURN = "end turn"; // End the turn
    public static final String CMD_EXIT = "exit"; // Exit the game

    private static final Map COMMANDS = new HashMap();
    static {
        COMMANDS.put(CMD_CARD, new Command(CMD_CARD, true, 0));
        COMMANDS.put(CMD_SKILL, new Command(CMD_SKILL, true, 0));
        COMMANDS.put(CMD_HELP, new Command(CMD_HELP));
        COMMANDS.put(CMD_ENDTURN, new Command(CMD_ENDTURN));
        COMMANDS.put(CMD_EXIT, new Command(CMD_EXIT));
    }

    // ATTRIBUTES
    
    private Command lastCommand;
    
    // CONSTRUCTOR
    
    public CombatParser() {
        lastCommand = new Command("");
    }
    
    // REQUESTS
    
    public String getLastCommand() {
        if (!isLastCommandLegal()) {
            throw new AssertionError();
        }
        return lastCommand.getName();
    }
    
    public int getLastTargetID() {
        if (!isLastCommandLegal() || !isLastCommandTargeted()) {
            throw new AssertionError();
        }
        return lastCommand.getTargetID();
    }

    public boolean isLastCommandLegal() {
        return COMMANDS.get(lastCommand.getName()) != null;
    }

    public boolean isLastCommandTargeted() {
        if (!isLastCommandLegal()) {
            throw new AssertionError();
        }
        return lastCommand.isTargeted();
    }

    // COMMANDS
    
    public void parseInput(String input) {
        if (input == null || input.trim().equals("")) {
            throw new AssertionError();
        }
        Scanner sc = new Scanner(input);
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNext()) {
            buffer.append(sc.next());
        }
        if (isCommandLegal(buffer.toString())) {
            Command c = (Command) COMMANDS.get(buffer.toString());
            if (c.isTargeted()) {
                if (sc.hasNextInt()) {
                    c.setTargetID(sc.nextInt());
                } else {
                    lastCommand = new Command("");
                }
            }   
            lastCommand = c;
        } else {
            lastCommand = new Command("");
        }
    }

    // TOOLS

    public static boolean isCommandLegal(String input) {
        return COMMANDS.get(input) != null;
    }
}
