package galamadriabuyak.util;

import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class CombatParser extends Parser implements ICombatParser {
    
    // REQUESTS

    public boolean isLastCommandLegal() {
        return ((command.equals(CMD_USE)
            || command.equals(CMD_HELP))
            && targetID >= 0)
            || (command.equals(CMD_ENDTURN)
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
                        targetID = -1;
                    }
                }
            }            
        } catch (NoSuchElementException e) {
            command = "";
            targetID = -1;
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
            || input.equals(CMD_EXIT);
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
