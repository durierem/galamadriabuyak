package galamadriabuyak;

import galamadriabuyak.util.Tools;

public class Enemy extends Character implements IEnemy {
    
    // CONSTANTS
    
    // The maximal number of actions that 
    // the ennemy could perform in a turn. 
    private final int MAX_NUMBER_ACTION = 4;
    
    // ATTRIBUTES
    
    // Action Array
    // It will contain cards used during the ennemy's turn.
    // (including the basicAttack)
    private String[] actArr;

    // ActionNumber
    // Number of cards used this turn.
    private int actNb;
    
    // CONSTRUCTORS

    public Enemy(String name, int level, int health, ICard basicAttack,
        IDeck deck, IHand hand) {
        super(name, level, health, basicAttack, deck, hand);
        actArr = new String[MAX_NUMBER_ACTION];
        actNb = 0;
    }

    // COMMANDS

    public void performTurn(Game game) {
        if (isDead() || game == null || game.getPlayer().isDead()) {
            throw new AssertionError();
        }

        fillHand();

        if (Tools.alea(0, 100) <= 65) {
            this.getBasicAttack().applyEffects(game, this);
            
            actArr[actNb] = getBasicAttack().getName();
            ++actNb;
            
            if (game.getPlayer().isDead() || game.getEnemy().isDead()) {
                return;
            }

            if (Tools.alea(0, 100) <= 33) {
                chooseAndUseCards(game);
            }
        } else if (Tools.alea(0, 100) <= 50) {
            chooseAndUseCards(game);
        }
        
        
        StringBuffer sb = new StringBuffer();
        sb.append(getName() + " used : ");
        for (int i = 0; i < actNb; ++i) {
            sb.append( " " + actArr[i] + "\n" );  
        }  
        Game.STATUS_BAR.setStatus(sb.toString());
        actNb = 0;
    }

    // TOOLS

    /**
     * Make the enemy randomly choose and use cards.
     */
    private void chooseAndUseCards(Game game) {
        for (int i = 1; i <= this.getHand().getSize(); i++) {
            ICard usingCard = getHand(i);
            usingCard.applyEffects(game, this);
            getHand().deleteCard(i);
            
            actArr[actNb] = usingCard.getName();
            ++actNb;
            
            if (game.getPlayer().isDead() || game.getEnemy().isDead()) {
                return;
            }
        }
    }
}
