package galamadriabuyak;

import galamadriabuyak.util.*;
import java.util.Scanner;

public class Game {
    
    private Scanner scanner;
    private ICombatParser combatParser;
    private IPlayer player;
    
    public Game() {
        scanner = new Scanner(System.in);
        combatParser = new CombatParser();
        player = new Player();
    }
    
    /**
     * !!! NE GERE PAS ENCORE L'AFFICHAGE !!!
     */
    private void startFight(IPlayer player, ICharacter enemy) {
        player.getDeck().shuffleDeck();
        //enemy.getDeck().shuffleDeck();
        while (!player.isDead() && !enemy.isDead()) {
            player.draw(3 - player.getHand().getSize());
            while (combatParser.getLastCommand() != ICombatParser.CMD_ENDTURN
            && !player.isDead()) {
                waitForInput();
                while (!combatParser.isLastCommandLegal()) {
                    waitForInput();
                }
                if (combatParser.getLastCommand() == ICombatParser.CMD_USE) {
                    player.getHand().getCard().applyEffects(this);
                }
                if (combatParser.getLastCommand() == ICombatParser.CMD_HELP) {
                    player.getHand().getCard().getDescription();
                    player.getHand().getCard().getTrivia();
                }
            }
            enemy.draw(3 - player.getHand().getSize());
            enemy.performTurn();
        }
    }
    
   
    private String waitForInput(){
        StringBuffer result = new StringBuffer();
        while (scanner.hasNext()) {
            result.append(scanner.next());
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
}
