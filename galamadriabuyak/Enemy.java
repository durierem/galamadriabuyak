package galamadriabuyak;

public class Enemy extends Character implements IEnemy {
    
    // Constructor
    
    public Enemy(String name, int level, int health, ICard basicAttack,
        IDeck deck, IHand hand) {
        super(name, level, health, basicAttack, deck, hand);
    }
    
    // COMMANDS
    
    public void performTurn(Game game){
        if (isDead() || game == null || game.getPlayer().isDead()) {
            throw new AssertionError();
        }
        
        completeHand();
        
        if (alea(0, 100) <= 65) {
            this.getBasicAttack().applyEffects(game);
            
            if (game.getPlayer().isDead() || game.getEnemy().isDead()) {
                return;
            }
                
            if (alea(0, 100) <= 33) {
                chooseAndUseCards(game);
            }
        } else if (alea(0, 100) <= 50) {
            chooseAndUseCards(game);
        }
    }
    
    // Outils
    
    /**
     * Return a random number in range of [min, max] 
     */
    private int alea(int min,int max) {
        return min + (int) (Math.random() * (max - min + 1));
    } 
    
    /**
     * Make the enemy randomly choose and use cards.
     */
    private void chooseAndUseCards(Game game) {
        for (int i = alea(0,2); i < this.getHand().getSize(); i++) {
            this.getHand().getCard(i).applyEffects(game);
            if (game.getPlayer().isDead() || game.getEnemy().isDead()) {
                return;
            }
        }
    }
}
