package galamadriabuyak;

import galamadriabuyak.util.*;

public class Game {
    
    public Game() {
        Scanner scanner = new Scanner(System.in);
        ICombatParser combatParser = new CombatParser();
        IPlayer player = new Player();
    }
    
    /**
     * !!! NE GERE PAS ENCORE L'AFFICHAGE !!!
     */
    private void startFight(IPlayer player, ICharacter enemy) {
        player.getDeck().shuffleDeck();
        //enemy.getDeck().shuffleDeck();
        while (!player.isDead() && !enemy.isDead()) {
            player.draw(3 - player.getHand().getSize());
            while (combatParser.getLastCommand() != combatParser.CMD_ENDTURN
            && !player.isDead()) {
                waitForInput();
                while (!combatParser.isLastCommandLegal()) {
                    waitForInput();
                }
                if (combatParser.getLastCommand() == CMD_USE) {
                    player.GetHand().getCard().applyEffects(this);
                }
                if (combatParser.getLastCommand() == CMD_HELP) {
                    player.GetHand().getCard().getDescription();
                    player.GetHand().getCard().getTrivia();
                }
            }
            enemy.draw(3 - player.getHand().getSize());
            enemy.performTurn();
        }
    }
    
    private String waitForInput() {
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
