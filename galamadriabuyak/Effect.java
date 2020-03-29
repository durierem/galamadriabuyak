package galamadriabuyak;

import galamadriabuyak.util.Target;
import galamadriabuyak.util.Type;

public class Effect implements IEffect {
    
    // ATTRIBUTES
    
    private final Type type;
    private final Target target;
    private final int power;
    
    // CONSTRUCTOR
    
    public Effect(Type type, Target target, int power) {
        if (type == null || target == null || power < 0) {
            throw new AssertionError();
        }
        this.type = type;
        this.target = target;
        this.power = power;
    }
    
    // REQUESTS
    
    public Type getType() {
        return type;
    }
    
    public Target getTarget() {
        return target;
    }
    
    public int getPower() {
        return power;
    }
    
    // COMMANDS
    
    public void applyEffect(Game game) {
        
    }
}
