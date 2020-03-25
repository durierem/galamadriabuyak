. 

import galamadriabuyak.util.*;

public class Game {
    
    public Game() {
        ICombatParser Combatparser = new CombatParser();
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
            while(combatParser.getLastCommand() != combatParser.CMD_ENDTURN && !player.isDead()) {
                waitForInput();
                while(!combatParser.isLastCommandLegal()){
                    waitForInput();
                }
                if(combatParser.getLastCommand() == CMD_USE){
                    player.GetHand().getCard().applyEffects(this);
                }
                if(combatParser.getLastCommand() == CMD_HELP){
                    player.GetHand().getCard().getDescription();
                    player.GetHand().getCard().getTrivia();
                }
            }
            enemy.draw(3 - player.getHand().getSize());
            enemy.performMonsterTurn();
        }
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
    private String waitForInput(){
        
    }
}
