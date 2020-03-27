package galamadriabuyak;

import galamadriabuyak.util.Type;
import galamadriabuyak.util.Target;

public class BasicAttack extends Card implements ICard {
    
    // CONSTRUCTOR
    
    public BasicAttack(String name, String description, String trivia, 
            IEffect[] arrayEffect) {
        super(name, description, trivia, arrayEffect);
    }
}
