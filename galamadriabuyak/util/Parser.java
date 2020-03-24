package galamadriabuak.util;

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
        if (!isLastCommandLegal()) {
            throw new AssertionError();
        }
        return command;
    }
    
    public int getLastTargetID() {
        if (!isLastCommandLegal()) {
            throw new AssertionError();
        }
        return targetID;
    }
    
    public boolean isLastCommandLegal() {
        return (command.equals(CMD_USE) || command.equals(CMD_HELP))
            && targetID > 0;
    }
    
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
                targetID = 0;
            }
        } catch (NoSuchElementException e) {
            command = "";
            targetID = 0;
        }
    }
}
