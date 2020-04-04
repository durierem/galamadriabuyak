package galamadriabuyak;

import galamadriabuyak.util.IParser;
import galamadriabuyak.util.CombatParser;
import galamadriabuyak.util.Target;
import galamadriabuyak.util.Type;
import galamadriabuyak.util.Tools;
import galamadriabuyak.util.JSONizer;

public class Game {

    // ATTRIBUTES

    public static final ICard[] CARD_DATABASE = JSONizer
            .cardsFromFile("./cards.json");
    public static final IParser COMBAT_PARSER = new CombatParser();

    private final IPlayer player;
    private IEnemy enemy;

    // CONSTRUCTORS

    public Game() {
        player = new Player("You", 1, 20,
                        new BasicAttack("Picky Pike", "enemy/direct hit/3",
                                "An ancient and beautiful decorated pike.",
                                Effect.createEffectsArray(new Effect(Type.HIT,
                                        Target.ENEMY, 3))),
                        new Deck(),
                        new Hand(),
                        0);

        enemy = new Enemy("Bob", 1, 30,
                        new BasicAttack("Useless Roar", "player/direct hit/5",
                                "A useless capacity",
                                Effect.createEffectsArray(new Effect(Type.HIT,
                                        Target.PLAYER, 5))),
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
            System.out.println("YOU LOSE!");
        } else  {
            System.out.println("YOU WIN!");
        }
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

        return "\n"
               + " =============================== YOUR TURN! ================="
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
               + "==================\n"
               + "\n"
               + " Commands available:\n"
               + " `use card [N]` / `help card [N]`\n"
               + " `use skill` / `help skill`\n"
               + " `end turn` / `quit`\n";
    }

    public static void main(String[] args) {
        new Game().play();
    }
}
