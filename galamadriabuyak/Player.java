package galamadriabuyak;

import galamadriabuyak.util.IParser;
import galamadriabuyak.util.CombatParser;
import galamadriabuyak.util.Tools;

public class Player extends Character implements IPlayer {
    // ATTRIBUTES
    
    private int money;
    
    // CONSTRUCTORS
    
    public Player(String name, int level, int health, ICard basicAttack,
                  IDeck deck, IHand hand, int money) {
        super(name, level, health, basicAttack, deck, hand);
        if (money < 0) {
            throw new AssertionError();
        }
        this.money = money;
    }
    
    // REQUESTS
    
    public int getMoney() {
        return money;
    }
    
    // COMMANDS
    
    public void setMoneyTo(int q) {
        if (q < 0) {
          throw new AssertionError();
        }
        money = q;
    }

    public void setMoneyUp(int q) {
        if (q < 0 ) {
            throw new AssertionError();
        }
        money += q;
    }

    public void setMoneyDown(int q) {
        if (q < 0) {
          throw new AssertionError();
        }
        money -= q;
    }
    
    public void performTurn(Game game) {
        if (game == null) {
            throw new AssertionError();
        }
        if (isDead() || game.getEnemy().isDead()) {
            throw new AssertionError();
        }

        IParser parser = Game.combatParser;  
        
        do {
            Tools.waitForInput(parser);

            /* Process the command entered by the player */
            String cmd = parser.getLastCommand();
            if (parser.isLastCommandTargeted()) {
                int targetID = parser.getLastTargetID();
                if (targetID > getHand().getSize()) {
                    System.out.println(" -> There is no card number " 
                                        + targetID + "!"); 
                    continue;
                }
                if (cmd.equals(CombatParser.CMD_CARD)) {
                    getHand(targetID).applyEffects(game);
                } else if (cmd.equals(CombatParser.CMD_HELP_CARD)) {
                    System.out.println(getHand(targetID)
                                       .getDescription());
                    System.out.println(getHand(targetID)
                                       .getTrivia());
                }
            } else if (cmd.equals(CombatParser.CMD_SKILL)) {
                getBasicAttack().applyEffects(game);
            } else if (cmd.equals(CombatParser.CMD_HELP_SKILL)) {
                // TODO
                System.out.println(" [ERROR] This functionality is not"
                                   + " implemented yet!");
                continue;
            } else if (cmd.equals(CombatParser.CMD_QUIT)) {
                System.exit(0);
            } 
            
            /* Checks if one of the character died after applying a command */
            if (isDead() || game.getEnemy().isDead()) {
                break;
            }
            
        } while (!parser.getLastCommand().equals(CombatParser.CMD_END_TURN));
    }
}
