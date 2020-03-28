package galamadriabuyak.util;

import galamadriabuyak.ICard;
import galamadriabuyak.Card;
import galamadriabuyak.IEffect;
import galamadriabuyak.Effect;

public class CardMaker {
    
    public CardMaker() {
        
    }
    
    public ICard makeCard(String name, String description, String trivia, 
                            Type ty, Target ta, int power) {
        IEffect[] effArray = new IEffect[1];
        effArray[0] = new Effect(ty, ta, power);
        
        return new Card(name, description, trivia, effArray);
    }
    
    /**
     * Returns a Card. Very practocal for tests and debugs.
     */
    public static ICard main(String[] args) {
        IEffect[] effArray = new IEffect[1];
        effArray[0] = new Effect(Type.HIT, Target.ENEMY, 2);
        
        return new Card("Dead's souls", 
                            "Inflict 2 damages to the ennemy", 
                            "No one lived enougth to know their stories", 
                            effArray);
    }
}