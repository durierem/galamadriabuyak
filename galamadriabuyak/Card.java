package galamadriabuyak;

public class Card implements ICard {
    
    // Attributs
    
    String name;
    String description;
    String trivia;
    IEffect[] effects;
   
    // Constructor
    
    public Card(String name, String description, String trivia, 
                    IEffect[] arrayEffect) {
        if (name == null || description == null 
            || trivia == null || arrayEffect == null) {
            throw new AssertionError();
        }
        this.name = name;
        this.description = description;
        this.trivia = trivia;
        effects = arrayEffect;
    }
    
    // Requests
    
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
    
    // Commands
    
    public void applyEffects(Game game) {
        if( game == null || getEffects() == null) {
            throw new AssertionError();
        }
    }
}
