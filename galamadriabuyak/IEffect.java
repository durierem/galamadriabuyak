package galamadriabuyak;
import galamadriabuyak.util.Target;
import galamadriabuyak.util.Type;
/**
 * Models an effect.
 */

public interface IEffect {
    // Constants
    
    // Requests
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
    
    // Commands
    /**
     * Apply the given effect on the game.
     * 
     * @pre
     *      effect != null
     *      game != null
     */
    void applyEffect(IEffect effect, Game game);
}