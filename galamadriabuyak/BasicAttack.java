package galamadriabuyak;

import galamadriabuyak.util.Type;
import galamadriabuyak.util.Target;

public class BasicAttack extends Card implements IBasicAttack {
    
    // CONSTRUCTOR
    
    public BasicAttack() {
        super("Picky Pike",
            "[TARGET] enemy ; [TYPE] direct hit ; [POWER] 5",
            "An ancient and beautiful decorated pike",
            createEffectsArray(new Effect(Type.HIT, Target.ENEMY, 5)));
    }
    
    /**
     * Returns an array of IEffect created from the effects in argument.
     * @pre
     *      effects != null
     */
    private static IEffect[] createEffectsArray(IEffect... effects) {
        if (effects == null) {
            throw new AssertionError();
        }
        IEffect[] array = new IEffect[effects.length];
        for (int i = 0; i < effects.length; i++) {
            array[i] = effects[i];
        }
        return array;
    }
}
