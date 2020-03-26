package galamadriabuyak;


/**
 * Décrivez votre classe Enemy ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Enemy extends Character implements IEnemy
{
    
    // Constructor
    
    public Enemy() {
        super();
    }
    
    // COMMANDS
    
    void performTurn(Game game){
        for (int i = alea(0,2) ; i < this.getHand().getSize() ; i++){
            this.getHand().getCard().applyEffects(game);
        }    
    }
    
    // Outils
    
    /**
     * Return a random number in range of [min, max] 
     */
    private int alea(int min,int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }
    
}
