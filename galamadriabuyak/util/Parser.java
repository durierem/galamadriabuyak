package galamadriabuyak.util;

import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class Parser implements IParser {
    
    // ATTRIBUTES
    
    private String command;
    private int targetID;
    
    // CONSTRUCTOR
    
    public Parser() {
        command = "";
        targetID = 0;
    }
    
    // COMMANDS
    
    public String getLastCommand() {
        return command;
    }
    
    public int getLastTargetID() {
        return targetID;
    }
    
    public void parseInput(String input) {
        if (input == null || input.trim().equals("")) {
            throw new AssertionError("Empty input");
        }
        StringTokenizer st = new StringTokenizer(input);
        try {
            String cmd = st.nextToken();
            if (!isCmdLegal(cmd)) {
                throw new AssertionError("Unknown command");
            } else {
               command = cmd; 
            }
            try {
                int id = Integer.parseInt(st.nextToken());
                if (id <= 0) {
                    throw new AssertionError("Invalid target");
                } else {
                    targetID = id;
                }
            } catch (NumberFormatException e) {
                throw new AssertionError("Not a number");
            }
        } catch (NoSuchElementException e) {
            throw new AssertionError("Not enough arguments");
        }
    }
    
    // TOOLS
    
    private boolean isCmdLegal(String cmd) {
        return cmd.equals(CMD_USE) || cmd.equals(CMD_HELP);
    }
}
