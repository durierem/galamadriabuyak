package galamadriabuyak.util;

public abstract class Parser implements IParser {
    
    // ATTRIBUTES
    
    protected String command;
    protected int targetID;
    
    // CONSTRUCTOR
    
    public Parser() {
        command = "";
        targetID = -1;
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

    public abstract boolean isLastCommandLegal();

    public abstract boolean isLastCommandTargeted();

    // COMMANDS

    public abstract void parseInput(String input);
}
