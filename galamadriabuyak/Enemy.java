package galamadriabuyak;

import galamadriabuyak.util.Tools;

public class Enemy extends Character implements IEnemy {
    
    // CONSTRUCTORS
    
    public Enemy(String name, int level, int health, ICard basicAttack,
        IDeck deck, IHand hand) {
        super(name, level, health, basicAttack, deck, hand);
    }
    
    // COMMANDS
    
    public void performTurn(Game game){
        if (isDead() || game == null || game.getPlayer().isDead()) {
            throw new AssertionError();
        }
        
        fillHand();
        Tools.drawInterface(game.makeStringOfGame());

        if (alea(0, 100) <= 65) {
            this.getBasicAttack().applyEffects(game, this);
            
            if (game.getPlayer().isDead() || game.getEnemy().isDead()) {
                return;
            }
                
            if (alea(0, 100) <= 33) {
                chooseAndUseCards(game);
            }
        } else if (alea(0, 100) <= 50) {
            chooseAndUseCards(game);
        }

        Tools.drawInterface(game.makeStringOfGame());
    }
    
    // TOOLS
    
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
        for (int i = 1; i <= this.getHand().getSize(); i++) {
            getHand(i).applyEffects(game, this);
            getHand().deleteCard(i);
            if (game.getPlayer().isDead() || game.getEnemy().isDead()) {
                return;
            }
        }
    }
}
