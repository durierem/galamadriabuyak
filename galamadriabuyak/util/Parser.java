package galamadriabuyak.util;

public abstract class Parser implements IParser {
    // ATTRIBUTES
    
    private String command;
    private int targetID;
    
    // CONSTRUCTOR
    
    public CombatParser() {
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

    public boolean isLastCommandLegal();

    public boolean isLastCommandTargeted();

    // COMMANDS

    public void parseInput(String input);
}
