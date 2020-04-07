package galamadriabuyak.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A parser to handle all the commands related to a fight.
 */
public class CombatParser implements Parser {

    // CONSTANTS

    public static final String CMD_CARD = "use card";
    public static final String CMD_SKILL = "use skill";
    public static final String CMD_HELP = "help";
    public static final String CMD_HELP_CARD = "help card";
    public static final String CMD_HELP_SKILL = "help skill";
    public static final String CMD_END_TURN = "end turn";
    public static final String CMD_QUIT = "quit";

    private static final String[] AVAILABLE_COMMANDS = {
        CMD_CARD,
        CMD_SKILL,
        CMD_HELP,
        CMD_HELP_CARD,
        CMD_HELP_SKILL,
        CMD_END_TURN,
        CMD_QUIT
    };

    private static final Map<String, Command> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put(CMD_CARD, new Command(CMD_CARD, true));
        COMMANDS.put(CMD_SKILL, new Command(CMD_SKILL));
        COMMANDS.put(CMD_HELP, new Command(CMD_HELP));
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
            throw new AssertionError("The last command is not legal.");
        }

        return lastCommand.getName();
    }

    public int getLastTargetID() {
        if (!isLastCommandLegal()) {
            throw new AssertionError("The last command is not legal.");
        }
        if (!isLastCommandTargeted()) {
            throw new AssertionError("The last command is not targeted.");
        }

        return lastCommand.getTargetID();
    }

    public boolean isLastCommandLegal() {
        return COMMANDS.get(lastCommand.getName()) != null;
    }

    public boolean isLastCommandTargeted() {
        if (!isLastCommandLegal()) {
            throw new AssertionError("The last command is not legal.");
        }

        return lastCommand.isTargeted();
    }

    public String getAllCommands() {
        final StringBuffer sc = new StringBuffer();
        for (int i = 0; i < AVAILABLE_COMMANDS.length; i++) {
            Command c = COMMANDS.get(AVAILABLE_COMMANDS[i]);
            sc.append("[" + c.getName() + "] ");
        }
        return sc.toString();
    }

    // COMMANDS

    public void parseInput(String input) {
        if (input == null) {
            throw new AssertionError("There is no input to parse.");
        }


        final Scanner sc = new Scanner(input);
        final StringBuffer buffer = new StringBuffer();

        /*
         * Separates the command from the potential target ID.
         */
        while (sc.hasNext() && !sc.hasNextInt()) {
            buffer.append(sc.next() + " ");
        }
        buffer.deleteCharAt(buffer.length() - 1); // Removes the trailing space

        /*
         * If the command is legal, attempts to retrieve the target ID.
         * If the command is illegal or if there is no valid target ID, ensures
         * that the last command is illegal (==> !isLastCommandLegal()).
         */
        Command c = COMMANDS.get(buffer.toString());
        if (c == null) {
            lastCommand = new Command("");
            sc.close();
            return;
        }
        if (c.isTargeted() && sc.hasNextInt()) {
            final int targetID = sc.nextInt();
            if (targetID <= 0) {
                lastCommand = new Command("");
                sc.close();
                return;
            }
            c.setTargetID(targetID);
        }
        lastCommand = c;
        sc.close();
    }
}
