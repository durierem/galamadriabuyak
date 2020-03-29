package galamadriabuyak.util;

import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class CombatParser extends Parser implements ICombatParser {
    
    // REQUESTS

    public boolean isLastCommandLegal() {
        return (command.equals(CMD_USE)
            || command.equals(CMD_HELP)
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
            command = st.nextToken(); 
            try {
                targetID = Integer.parseInt(st.nextToken());
            } catch (NumberFormatException e) {
                command = "";
                targetID = -1;
            }
        } catch (NoSuchElementException e) {
            command = "";
            targetID = -1;
        }
    } 
}
