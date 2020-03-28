package galamadriabuyak;

import galamadriabuyak.util.*;
import java.util.Scanner;

public class Game {
    
    private Scanner scanner;
    private ICombatParser combatParser;
    private IPlayer player;
    private IEnemy enemy;
    
    public Game() {
        scanner = new Scanner(System.in);
        combatParser = new CombatParser();
        player = new Player("Alice",
            1,
            10,
            new BasicAttack("Picky Pike",
                "[TARGET] enemy ; [TYPE] direct hit ; [POWER] 5",
                "An ancient and beautiful decorated pike",
                createEffectsArray(new Effect(Type.HIT, Target.ENEMY, 5))),
            new Deck(),
            new Hand(),
            0);
        enemy = new Enemy("Bob",
            1,
            5,
            new BasicAttack("Useless Roar",
                "[TARGET] player ; [TYPE] direct hit ; [POWER] 1",
                "A useless capacity",
                createEffectsArray(new Effect(Type.HIT, Target.PLAYER, 1))),
            new Deck(),
            new Hand());
        startFight(player, enemy);
    }
    
    /*
     * !!! NE GERE PAS ENCORE L'AFFICHAGE !!!
     */
    private void startFight(IPlayer player, IEnemy enemy) {
        player.getDeck().shuffleDeck();
        enemy.getDeck().shuffleDeck();
        while (!player.isDead() && !enemy.isDead()) {
            player.draw(IHand.MAX_SIZE - player.getHand().getSize());
            while (combatParser.getLastCommand() != ICombatParser.CMD_ENDTURN
            && !player.isDead()) {
                draw();
                waitForInput();
                while (!combatParser.isLastCommandLegal()) {
                    waitForInput();
                }
                if (combatParser.getLastCommand() == ICombatParser.CMD_USE) {
                    player.getHand().getCard(combatParser.getLastTargetID())
                        .applyEffects(this);
                    draw();
                }
                if (combatParser.getLastCommand() == ICombatParser.CMD_HELP) {
                    player.getHand().getCard(combatParser.getLastTargetID())
                        .getDescription();
                    player.getHand().getCard(combatParser.getLastTargetID())
                        .getTrivia();
                }
            }
            enemy.draw(IHand.MAX_SIZE - player.getHand().getSize());
            enemy.performTurn(this);
        }
    }
    
   
    private String waitForInput(){
        StringBuffer result = new StringBuffer();
        while (scanner.hasNext()) {
            result.append(scanner.next());
        }
        return result.toString();
    }
    
    private void draw() {
        clear();
        System.out.println(makeStringOfGame());
    }
    
    /**
     * Returns an array of IEffect created from the effects in argument.
     * @pre
     *      effects != null
     */
    private static IEffect[] createEffectsArray(IEffect... effects) {
        if (effects == null) {
            throw new AssertionError();
        }
        return effects;
    }
    
    private String makeStringOfGame() {
        return "================================ YOUR TURN! ====================================  \n"
               + "                                                                                \n"
               + " Type 'help [card number]' for detailed informations about a card.              \n"
               + " Type 'use [card number]' to use a card.                                        \n"
               + "                                                                                \n" 
               + " +----------------------------------------------------------------------------+ \n"
               + "                                                                                \n"
               + " [" + enemy.getName() + "]                                                      \n"
               + "                                                                                \n"
               + " HP: " + enemy.getHealth() + "                                                  \n"
               + " Deck (" + enemy.getDeck().getSize() +") / Hand (" + enemy.getHand().getSize() + ")\n"
               + "                                                                                \n"
               + " -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n"
               + "                                                                                \n"
               + " [" + player.getName() + "]                                                     \n"
               + "                                                                                \n"
               + " HP: " + player.getHealth() + "                                                 \n"
               + " Deck (" + player.getDeck().getSize() +") / Hand (" + player.getHand().getSize() + ")\n"
               + "   1 - " + player.getHand().getCard(0).getName() + "                            \n"
               + "   2 - " + player.getHand().getCard(1).getName() + "                            \n"
               + "   3 - " + player.getHand().getCard(2).getName() + "                            \n"
               + "                                                                                \n"
               + " + ---------------------------------------------------------------------------- \n";
    }
    
    private void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    
    
    
    public static void main(String[] args) {
        new Game();
    } 
}
