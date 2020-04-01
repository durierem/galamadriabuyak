package galamadriabuyak;

import galamadriabuyak.util.IParser;
import galamadriabuyak.util.CombatParser;
import galamadriabuyak.util.Target;
import galamadriabuyak.util.Type;
import galamadriabuyak.util.Tools;
import galamadriabuyak.util.JSONizer;

public class Game {
    
    private final IParser combatParser;
    private final ICard[] cardDataBase;
    private final IPlayer player;
    private final IEnemy enemy;
    
    public Game() {
        combatParser = new CombatParser();
        cardDataBase = JSONizer.cardsFromFile("./cards.json");

        player = new Player("Alice",
            1,
            10,
            new BasicAttack("Picky Pike",
                "[TARGET] enemy ; [TYPE] direct hit ; [POWER] 3",
                "An ancient and beautiful decorated pike",
                Effect.createEffectsArray(new Effect(Type.HIT, Target.ENEMY, 3))),
            new Deck(),
            new Hand(),
            0);

        enemy = new Enemy(
            "Bob",
            1,
            5,
            new BasicAttack("Useless Roar",
                "[TARGET] player ; [TYPE] direct hit ; [POWER] 1",
                "A useless capacity",
                Effect.createEffectsArray(new Effect(Type.HIT, Target.PLAYER, 1))),
            new Deck(),
            new Hand());
    }

    public void play() {
        startFight(player, enemy);
    }
    
    public IPlayer getPlayer() {
        return player;
    }
    
    public IEnemy getEnemy() {
        return enemy;
    }
    
   
    private void startFight(IPlayer player, IEnemy enemy) {
        player.getDeck().shuffleDeck();
        enemy.getDeck().shuffleDeck();
        while (!player.isDead() && !enemy.isDead()) {
            player.completeHand();
            Tools.drawInterface(makeStringOfGame());

            do {
                Tools.waitForInput(combatParser);
                // Processes the command entered by the player
                String cmd = combatParser.getLastCommand();
                if (combatParser.isLastCommandTargeted()) {
                    int targetID = combatParser.getLastTargetID();
                    if (targetID > player.getHand().getSize()) {
                        System.out.println(" -> There is no card number " 
                                               + targetID + "!"); 
                        continue;
                    }
                    if (cmd.equals(CombatParser.CMD_CARD)) {
                        player.getHand(targetID).applyEffects(this);
                    } else if (cmd.equals(CombatParser.CMD_HELP_CARD)) {
                        System.out.println(player.getHand(targetID)
                            .getDescription());
                        System.out.println(player.getHand(targetID)
                            .getTrivia());
                    }
                } else if (cmd.equals(CombatParser.CMD_SKILL)) {
                    player.getBasicAttack().applyEffects(this);
                } else if (cmd.equals(CombatParser.CMD_HELP_SKILL)) {
                    // TODO
                    System.out.println(" -> This functionality is not"
                        + " implemented yet!");
                    continue;
                } else if (cmd.equals(CombatParser.CMD_EXIT)) {
                    System.exit(0);
                } 
                
                // Checking if one of the character died after applying a command.
                if (player.isDead() || enemy.isDead()) {
                    break; // TODO: display a end game screen / informations
                }
                
                Tools.drawInterface(makeStringOfGame());
            } while (!combatParser.getLastCommand().equals(CombatParser.CMD_END_TURN));     
            
            if (player.isDead() || enemy.isDead()) {
                break;
            }
            
            enemy.completeHand(); // This has to be in Enemy.performTurn()
            enemy.performTurn(this);
            
            if (player.isDead() || enemy.isDead()) {
                break; // TODO: display a end game screen / informations
            }
        }
        Tools.drawInterface(makeStringOfGame());
    }
    
    private String makeStringOfGame() {
        String card0Name = "______________";
        if (player.getHand().getSize() > 0) {
            card0Name = player.getHand().getCard(0).getName();
        }
        
        String card1Name = "______________";
        if (player.getHand().getSize() > 1) {
            card1Name = player.getHand().getCard(1).getName();
        }
        
        String card2Name = "______________";
        if (player.getHand().getSize() > 2) {
            card2Name = player.getHand().getCard(2).getName();
        }

        return "================================ YOUR TURN! ====================================\n"
               + "                                                                                \n"
               + " Type 'help [card number]' for detailed informations about a card.              \n"
               + " Type 'use [card number]' to use a card.                                        \n"
               + "                                                                                \n" 
               + " +-----------------------------------------------------------------------------+\n"
               + "                                                                                \n"
               + " [" + enemy.getName() + "]                                                      \n"
               + "                                                                                \n"
               + " HP: " + enemy.getHealth() + "                                                  \n"
               + " Deck (" + enemy.getDeck().getSize() + ") / Hand (" + enemy.getHand().getSize() + ")\n"
               + "                                                                                \n"
               + " -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n"
               + "                                                                                \n"
               + " [" + player.getName() + "]                                                     \n"
               + "                                                                                \n"
               + " HP: " + player.getHealth() + "                                                 \n"
               + " Deck (" + player.getDeck().getSize() + ") / Hand (" + player.getHand().getSize() + ")\n"
               + "   1 - " + card0Name + "                                                        \n"
               + "   2 - " + card1Name + "                                                        \n"
               + "   3 - " + card2Name + "                                                        \n"
               + "                                                                                \n"
               + "+------------------------------------------------------------------------------+\n";       
    }
    
    public static void main(String[] args) {
        new Game().play();
    } 
}
