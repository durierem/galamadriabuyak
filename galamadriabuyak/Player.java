package galamadriabuyak;

import galamadriabuyak.util.IParser;
import galamadriabuyak.util.CombatParser;
import galamadriabuyak.util.Tools;

public class Player extends Character implements IPlayer {
    // Attributes
    
    private int money;
    
    // Constructor
    
    public Player(String name, int level, int health, ICard basicAttack,
        IDeck deck, IHand hand, int money) {
        super(name, level, health, basicAttack, deck, hand);
        if (money < 0) {
            throw new AssertionError();
        }
        this.money = money;
    }
    
    // Requests
    
    public int getMoney() {
        return money;
    }
    
    // Commands
    
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
        if (isDead() || game == null || game.getEnemy().isDead() || Game.combatParser == null) {
            throw new AssertionError();
        }   
        
        IParser parser = Game.combatParser;
        
        completeHand();
        
        do {
                Tools.waitForInput(parser);
                // Processes the command entered by the player
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
                    System.out.println(" -> This functionality is not"
                        + " implemented yet!");
                    continue;
                } else if (cmd.equals(CombatParser.CMD_EXIT)) {
                    System.exit(0);
                } 
                
                // Checking if one of the character died after applying a command.
                if (isDead() || game.getEnemy().isDead()) {
                    break; // TODO: display a end game screen / informations
                }
                
                Tools.drawInterface(game.makeStringOfGame());
            } while (!parser.getLastCommand().equals(CombatParser.CMD_END_TURN));
    }
}
