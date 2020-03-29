package galamadriabuyak.util;

import galamadriabuyak.ICard;
import galamadriabuyak.Card;
import galamadriabuyak.IEffect;
import galamadriabuyak.Effect;

public class CardMaker {
    
    public CardMaker() {
        
    }
    
    /**
     * You can use this method to generate the 1 effect card of your choice.
     *   ( I let my team mate improve it or anything)
     */
    public ICard makeCard(String name, String description, String trivia, 
                            Type ty, Target ta, int power) {
        IEffect[] effArray = new IEffect[1];
        effArray[0] = new Effect(ty, ta, power);
        
        return new Card(name, description, trivia, effArray);
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
    
    /**
     * Returns a Card. Very practical for tests and debugs.
     */
    public static ICard genOneCard() {
        IEffect[] effArray = new IEffect[1];
        effArray[0] = new Effect(Type.HIT, Target.ENEMY, 2);
        
        return new Card("Dead's souls", 
                            "Inflict 2 damages to the ennemy", 
                            "No one lived enougth to know their stories", 
                            effArray);
    }
    
}