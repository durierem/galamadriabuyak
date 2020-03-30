package galamadriabuyak;

import galamadriabuyak.util.*;

public class Game {
    
    private IParser combatParser;
    private IPlayer player;
    private IEnemy enemy;
    
    public Game() {
        combatParser = new CombatParser();
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
        enemy = new Enemy("Bob",
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
    
    // Useful for applyEffect
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
            
            //If deck is not empty, Player draw cards to complete his hand. 
            //If not enought cards in the deck, draws less cards.
            int deckSize = player.getDeck().getSize();
            if (deckSize > 0) {
                int draw_number = IHand.MAX_SIZE - player.getHand().getSize();
                if (draw_number > deckSize) {
                    player.draw(deckSize);
                } else {
                    player.draw(draw_number);
                }
            }

            do {
                Tools.drawInterface(makeStringOfGame());
                Tools.waitForInput(combatParser);
                
                String cmd = combatParser.getLastCommand();
                if (combatParser.isLastCommandTargeted()) {
                    int targetId = combatParser.getLastTargetID();
                    if (cmd.equals(CombatParser.CMD_USE)) {
                        player.getHand().getCard(targetId).applyEffects(this);
                    } else if (cmd.equals(CombatParser.CMD_HELP)) {
                        System.out.println(player.getHand().getCard(targetId)
                            .getDescription());
                        System.out.println(player.getHand().getCard(targetId)
                            .getTrivia());
                    }
                } else if (cmd.equals(CombatParser.CMD_SKILL)) {
                    player.getBasicAttack().applyEffects(this);
                }
                
            } while (combatParser.getLastCommand() != CombatParser.CMD_ENDTURN
                    && !player.isDead());     
            enemy.draw(IHand.MAX_SIZE - player.getHand().getSize());
            enemy.performTurn(this);
        }
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
               + "+------------------------------------------------------------------------------+\n"
               + "> ";
    }
    
    public static void main(String[] args) {
        new Game().play();
    } 
}
