package galamadriabuyak;

import galamadriabuyak.util.IParser;
import galamadriabuyak.util.CombatParser;
import galamadriabuyak.util.Target;
import galamadriabuyak.util.Type;
import galamadriabuyak.util.Tools;
import galamadriabuyak.util.JSONizer;

public class Game {

    // ATTRIBUTES
    
    public static final ICard[] cardDataBase = JSONizer
            .cardsFromFile("./cards.json");
    public static final IParser combatParser = new CombatParser();
    
    private final IPlayer player;
    private IEnemy enemy;
    
    // CONSTRUCTORS

    public Game() {
        player = new Player("You", 1, 10,
                        new BasicAttack("Picky Pike", "enemy/direct hit/3",
                                "An ancient and beautiful decorated pike.",
                                Effect.createEffectsArray(new Effect(Type.HIT,
                                        Target.ENEMY, 3))),
                        new Deck(),
                        new Hand(),
                        0);
        
        for (int i = 0; i < cardDataBase.length; ++i) {
            player.getDeck().addCard(cardDataBase[i]);
        }
                        
        enemy = new Enemy("Bob", 1, 5,
                        new BasicAttack("Useless Roar", "player/direct hit/5",
                                "A useless capacity",
                                Effect.createEffectsArray(new Effect(Type.HIT, 
                                        Target.PLAYER, 5))),
                        new Deck(),
                        new Hand());
    }

    // REQUESTS

    public IPlayer getPlayer() {
        return player;
    }
    
    public IEnemy getEnemy() {
        return enemy;
    }

    // COMMANDS

    public void play() {
        startFight(player, enemy);
    }
    
   
    private void startFight(IPlayer player, IEnemy enemy) {
        player.getDeck().shuffleDeck();
        enemy.getDeck().shuffleDeck();
        player.completeHand();
        Tools.drawInterface(makeStringOfGame());

        while (true) {
            player.performTurn(this);
            Tools.drawInterface(makeStringOfGame());
            if (player.isDead() || enemy.isDead()) {
                break; // TODO: display a end game screen / informations
            }
            enemy.performTurn(this);
            Tools.drawInterface(makeStringOfGame());
            if (player.isDead() || enemy.isDead()) {
                break; // TODO: display a end game screen / informations
            }
        }

        Tools.drawInterface(makeStringOfGame());
    }

    // TOOLS
    
    public String makeStringOfGame() {
        /* 
         * TODO: Automate the default cards names: it should depends on
         * IHand.MAX_SIZE.
         */
        final String defaultCardName = "__________";
        String card1Name = defaultCardName;
        String card2Name = defaultCardName;
        String card3Name = defaultCardName;
        if (player.getHand().getSize() > 0) {
            card1Name = player.getHand().getCard(1).getName();
        }
        if (player.getHand().getSize() > 1) {
            card2Name = player.getHand().getCard(2).getName();
        }
        if (player.getHand().getSize() > 2) {
            card3Name = player.getHand().getCard(3).getName();
        }

        return   "                                                                                \n"
               + " =============================== YOUR TURN! ====================================\n"
               + "                                                                                \n"
               + " [" + enemy.getName() + "]                                                      \n"
               + " HP: " + enemy.getHealth() + "                                                  \n"
               + " Deck (" + enemy.getDeck().getSize() + ") / Hand (" + enemy.getHand().getSize() + ")\n"
               + "                                                                                \n"
               + " -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n"
               + "                                                                                \n"
               + " [" + player.getName() + "]                                                     \n"
               + " HP: " + player.getHealth() + "                                                 \n"
               + " Deck (" + player.getDeck().getSize() + ") / Hand (" + player.getHand().getSize() + ")\n"
               + "   1 - " + card1Name + "                                                        \n"
               + "   2 - " + card2Name + "                                                        \n"
               + "   3 - " + card3Name + "                                                        \n"
               + "                                                                                \n"
               + " ===============================================================================\n"
               + "                                                                                \n"
               + " Commands available:                                                            \n"
               + " `use card [N]` / `help card [N]`                                               \n"
               + " `use skill` / `help skill`                                                     \n"
               + " `end turn` / `quit`                                                            \n";
    }
    
    // MAIN
    
    public static void main(String[] args) {
        new Game().play();
    } 
}
