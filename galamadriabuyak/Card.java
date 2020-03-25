package galamadriabuyak;

public class Card implements ICard {
    
    // Attributs
    
    String name;
    String description;
    String trivia;
    IEffect[] effects;
   
    // Constructor
    
    public Card(String n, String d, String t, IEffect[] arrayEffect) {
        if (n == null || d == null || t == null || arrayEffect == null) {
            throw new AssertionError();
        }
        name = n;
        description = d;
        trivia = t;
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
