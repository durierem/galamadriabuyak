package galamadriabuyak.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A parser to handle all the commands related to a fight.
 */
public class CombatParser implements IParser {

    // CONSTANTS

    public static final String CMD_CARD = "use card";
    public static final String CMD_SKILL = "use skill";
    public static final String CMD_HELP_CARD = "help card";
    public static final String CMD_HELP_SKILL = "help skill";
    public static final String CMD_END_TURN = "end turn";
    public static final String CMD_QUIT = "quit";

    private static final Map<String, Command> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put(CMD_CARD, new Command(CMD_CARD, true));
        COMMANDS.put(CMD_SKILL, new Command(CMD_SKILL));
        COMMANDS.put(CMD_HELP_CARD, new Command(CMD_HELP_CARD, true));
        COMMANDS.put(CMD_HELP_SKILL, new Command(CMD_HELP_SKILL));
        COMMANDS.put(CMD_END_TURN, new Command(CMD_END_TURN));
        COMMANDS.put(CMD_QUIT, new Command(CMD_QUIT));
    }

    // ATTRIBUTES

    private Command lastCommand;

    // CONSTRUCTORS

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
        if (input == null) {
            throw new AssertionError();
        }

        /*
         * Separates the command from the potential target ID.
         */
        final Scanner sc = new Scanner(input);
        final StringBuffer buffer = new StringBuffer();
        while (sc.hasNext() && !sc.hasNextInt()) {
            buffer.append(sc.next() + " ");
        }
        buffer.deleteCharAt(buffer.length() - 1); // Removes the trailing space

        /*
         * If the command is legal, attempts to retrieve the target ID.
         * If the command is illegal or if there is no valid target ID, ensures
         * that the last command is illegal (==> !isLastCommandLegal()).
         */
        if (isCommandLegal(buffer.toString())) {
            final Command c = COMMANDS.get(buffer.toString());
            if (c.isTargeted()) {
                if (sc.hasNextInt()) {
                    final int targetID = sc.nextInt();
                    if (targetID <= 0) {
                        lastCommand = new Command(""); 
                    } else {
                        c.setTargetID(targetID);    
                    }
                } else {
                    lastCommand = new Command("");
                }
            }
            lastCommand = c;
        } else {
            lastCommand = new Command("");
        }
        sc.close();
    }

    // TOOLS

    /**
     * Checks if input is a legal command.
     * @pre
     *      input != null
     */
    public static boolean isCommandLegal(String input) {
        assert input != null;
        return COMMANDS.get(input) != null;
    }
}
