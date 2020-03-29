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
                Effect.createEffectsArray(new Effect(Type.HIT, Target.ENEMY, 5))),
            new Deck(),
            new Hand(),
            0);
        enemy = new Enemy("Bob",
            1,
            5,
            new BasicAttack("Useless Roar",
                "[TARGET] player ; [TYPE] direct hit ; [POWER] 1",
                "A useless capacity",
                Effect.createEffectsArray(new Effect(Type.HIT, Target.PLAYER, 1))),
            new Deck(),
            new Hand());
        startFight(player, enemy);
    }
    
    // Usefull for applyEffect
    IPlayer getPlayer() {
        return player;
    }
    
    IEnemy getEnemy() {
        return enemy;
    }
    
    /*
     * !!! NE GERE PAS ENCORE L'AFFICHAGE !!!
     */
    private void startFight(IPlayer player, IEnemy enemy) {
        player.getDeck().shuffleDeck();
        enemy.getDeck().shuffleDeck();
        while (!player.isDead() && !enemy.isDead()) {
            
            //If deck is not empty, Player draw cards to complete his hand. 
            //If not enought cards in the deck, draws less cards.
            int deckSize = player.getDeck().getSize();
            if (deckSize > 0) {
                int draw_number = IHand.MAX_SIZE - player.getHand().getSize();
                if (draw_number > deckSize) {
                    player.draw(deckSize);
                } else {
                    player.draw(IHand.MAX_SIZE - player.getHand().getSize());
                }
            }
            
            do {
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
            } while (combatParser.getLastCommand() != ICombatParser.CMD_ENDTURN
            && !player.isDead());
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
               + "   1 - " + card0Name + "                                                        \n"
               + "   2 - " + card1Name + "                                                        \n"
               + "   3 - " + card2Name + "                                                        \n"
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
