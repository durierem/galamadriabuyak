package galamadriabuyak;

import galamadriabuyak.util.Target;
import galamadriabuyak.util.Type;

public class Effect implements IEffect {
    
    // ATTRIBUTES
    
    private final Type type;
    private final Target target;
    private final int power;
    
    // CONSTRUCTORS
    
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
    
    public void applyEffect(Game game, Object caller) {
        if (game == null || caller == null) {
            throw new AssertionError();
        }
        
        ICharacter user = null;
        ICharacter targ = null;
        if (caller instanceof Player) {
            user = game.getPlayer();
            targ = game.getEnemy();
        } else if (caller instanceof Enemy) {
            user = game.getEnemy();
            targ = game.getPlayer();
        } else {
            throw new AssertionError();
        }
        
        switch (type) {
            case HEAL:
                switch(target) {
                    case PLAYER:
                        user.setHealthUp(power);
                        break;
                    case ENEMY:
                        targ.setHealthUp(power);
                        break;
                }
                break;
            case HIT:
                switch(target) {
                    case PLAYER:
                        user.setHealthDown(power);
                        break;
                    case ENEMY:
                        targ.setHealthDown(power);
                        break;
                }
                break;
           }    
    }  
     
    /**
     * Returns an array of IEffect created from the effects in argument.
     * @pre
     *      effects != null
     */
    public static IEffect[] createEffectsArray(IEffect... effects) {
        if (effects == null) {
            throw new AssertionError();
        }
        return effects;
    }
}
