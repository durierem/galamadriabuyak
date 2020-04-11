package galamadriabuyak;

import galamadriabuyak.util.Target;
import galamadriabuyak.util.Type;
import galamadriabuyak.util.Tools;
import galamadriabuyak.util.JSONizer;
import galamadriabuyak.util.StatusBar;

public class Game {

    // ATTRIBUTES

    public static final ICard[] CARD_DATABASE = JSONizer
            .cardsFromFile("./cards.json");
    public static final StatusBar STATUS_BAR = new StatusBar();

    private final IPlayer player;
    private IEnemy enemy;

    // CONSTRUCTORS

    public Game() {
        player = new Player("You", 1, 30,
                        new BasicAttack("Picky Pike",
                                "Inflicts 3 damages to the enemy.",
                                "An ancient and beautiful decorated pike.",
                                Effect.createEffectsArray(new Effect(Type.HIT,
                                        Target.ENEMY, 3))),
                        new Deck(),
                        new Hand(),
                        0);

        enemy = new Enemy("Bob", 1, 50,
                        new BasicAttack("Useless Roar",
                                "Inflict 5 damages to the enemy.",
                                "A useless capacity",
                                Effect.createEffectsArray(new Effect(Type.HIT,
                                        Target.ENEMY, 5))),
                        new Deck() ,
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
        player.getDeck().randomFill(7, CARD_DATABASE);
        enemy.getDeck().randomFill(7, CARD_DATABASE);
        startFight();
    }

    private void startFight() {
        player.getDeck().shuffleDeck();
        enemy.getDeck().shuffleDeck();
        while (true) {
            player.performTurn(this);
            if (player.isDead() || enemy.isDead()) {
                break; // TODO: display a end game screen / informations
            }
            enemy.performTurn(this);
            if (player.isDead() || enemy.isDead()) {
                break; // TODO: display a end game screen / informations
            }
        }
        if (player.isDead()) {
            STATUS_BAR.setStatus("YOU LOSE!");
        } else  {
            STATUS_BAR.setStatus("YOU WIN!");
        }
        drawInterface();
    }

    public void drawInterface() {
        System.out.print(" ============================ GALAMADRIABUYAK ======="
                + "==========================\n");
        Tools.clearTerminal();
        STATUS_BAR.display();
        System.out.print(makeStringOfGame());
    }

    // TOOLS

    private String makeStringOfGame() {
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

        return "\n"
               + " ============================================================"
               + "==================\n"
               + "\n"
               + " [" + enemy.getName() + "]\n"
               + " HP: " + enemy.getHealth() + "\n"
               + " Deck (" + enemy.getDeck().getSize() + ") / Hand (" + enemy
                        .getHand().getSize() + ")\n"
               + "\n"
               + " -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+"
               + "-+-+-+-+-+-+-+-+-+\n"
               + "\n"
               + " [" + player.getName() + "]\n"
               + " HP: " + player.getHealth() + "\n"
               + " Deck (" + player.getDeck().getSize() + ") / Hand (" + player
                    .getHand().getSize() + ")\n"
               + "   1 - " + card1Name + "\n"
               + "   2 - " + card2Name + "\n"
               + "   3 - " + card3Name + "\n"
               + "\n"
               + " ============================================================"
               + "==================\n\n";
    }

    // MAIN

    public static void main(String[] args) {
        new Game().play();
    }
}
