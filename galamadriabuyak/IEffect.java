package galamadriabuyak;

import galamadriabuyak.util.Target;
import galamadriabuyak.util.Type;

/**
 * Models an effect.
 *
 * An effect is the combination of a Type, a Target and a power value.
 * @inv
 *      getType() != null
 *      getTarget() != null
 *      getPower() >= 0
 * @cons
 *      $DESC$
 *          Creates an effect from the attributes given in arguments.
 *      $ARGS$
 *          Type type
 *          Target target
 *          int power
 *      $PRE$
 *          type != null
 *          target != null
 *          power >= 0
 *      $POST$
 *          getType() == type
 *          geTarget() == target
 *          getPower() == power
 */
public interface IEffect {

    // REQUESTS

    /**
     * Returns the type of the effect.
     */
    Type getType();

    /**
     * Returns the target of the effect.
     */
    Target getTarget();

    /**
     * Returns the power of the effect.
     */
    int getPower();

    // COMMANDS

    /**
     * Apply the effect on the game.
     *
     * @pre
     *      game != null
     *      caller != null
     */
    void applyEffect(Game game, Object caller);
}
