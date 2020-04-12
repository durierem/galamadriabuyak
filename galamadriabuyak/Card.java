package galamadriabuyak;

public class Card implements ICard {
    
    // ATTRIBUTES
    
    String name;
    String description;
    String trivia;
    IEffect[] effects;
   
    // CONSTRUCTORS
    
    public Card(String name, String description, String trivia, 
            IEffect[] effects) {
        if (name == null || description == null || trivia == null
                || effects == null || doesArrayContainNull(effects)) {
            throw new AssertionError("Null parameter");
        }

        this.name = name;
        this.description = description;
        this.trivia = trivia;
        this.effects = effects;
    }
    
    // REQUESTS
    
    public String getName() {
        return name;
    }
   
    public String getDescription() {
        return description;
    }    
   
    public String getTrivia() {
        return trivia;
    }
    
    public IEffect[] getEffects() {
        return effects;
    }
    
    // COMMANDS
    
    public void applyEffects(Game game, Object caller) {
        if (game == null || caller == null) {
            throw new AssertionError("Null parameter");
        }

        for (int i = 0;  i < effects.length; ++i) {
            effects[i].applyEffect(game, caller);
        }
    }

    // TOOLS

    /**
     * Checks if the given array contains a null element.
     * @pre
     *      array != null
     */
    private boolean doesArrayContainNull(Object[] array) {
        assert array != null;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return true;
            }
        }

        return false;
    }
}
