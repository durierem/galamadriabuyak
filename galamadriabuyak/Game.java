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
        player = new Player(0); // NON SENS
        enemy = new Enemy(); // NON SENS
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
    
    private String makeStringOfGame() {
        return "================================ YOUR TURN! ====================================\n"
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
               + "   1 - Dead's souls                                                             \n"
               + "   2 - Fussy Hammer                                                             \n"
               + "   3 - Angelic, the angelic angel                                               \n"
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
