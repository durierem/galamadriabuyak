package galamadriabuyak;

import galamadriabuyak.util.Parser;
import galamadriabuyak.util.CombatParser;
import galamadriabuyak.util.Tools;

public class Player extends Character implements IPlayer {

    // ATTRIBUTES

    private int money;

    // CONSTRUCTORS

    public Player(String name, int level, int health, ICard basicAttack,
                  IDeck deck, IHand hand, int money) {
        super(name, level, health, basicAttack, deck, hand);
        if (money < 0) {
            throw new AssertionError();
        }
        this.money = money;
    }

    // REQUESTS

    public int getMoney() {
        return money;
    }

    // COMMANDS

    public void setMoneyTo(int q) {
        if (q < 0) {
            throw new AssertionError();
        }
        money = q;
    }
    public void setMoneyUp(int q) {
        if (q < 0) {
            throw new AssertionError();
        }
        money += q;
    }

    public void setMoneyDown(int q) {
        if (q < 0) {
            throw new AssertionError();
        }
        money -= q;
    }

    public void performTurn(Game game) {
        if (game == null) {
            throw new AssertionError();
        }
        if (isDead() || game.getEnemy().isDead()) {
            throw new AssertionError();
        }

        Parser parser = Game.COMBAT_PARSER;

        /* Fills the player's hand. Woah, such comment :O */
        fillHand();

        /* Draws the interface at the beginning of the action */
        Game.STATUS_BAR.display();
        Tools.drawInterface(game.makeStringOfGame());

        /* Processes the command entered by the player */
        do {
            Tools.waitForInput(parser);
            String cmd = parser.getLastCommand();
            if (parser.isLastCommandTargeted()) {
                int targetID = parser.getLastTargetID();
                if (targetID > getHand().getSize()) {
                    Game.STATUS_BAR.setStatus("There is no card number "
                            + targetID + "!");
                } else {
                    final ICard card = getHand(targetID);
                    if (cmd.equals(CombatParser.CMD_CARD)) {
                        card.applyEffects(game, this);
                        getHand().deleteCard(targetID);
                        Game.STATUS_BAR.setStatus("You use: " + card.getName());
                    } else if (cmd.equals(CombatParser.CMD_HELP_CARD)) {
                        Game.STATUS_BAR.setStatus(card.getName(),
                                card.getDescription(), card.getTrivia());
                    }
                }
            } else if (cmd.equals(CombatParser.CMD_SKILL)) {
                getBasicAttack().applyEffects(game, this);
                Game.STATUS_BAR.setStatus("You use: " + game.getPlayer()
                        .getBasicAttack().getName());
            } else if (cmd.equals(CombatParser.CMD_HELP)) {
                Game.STATUS_BAR.setStatus("Available commands:",
                        parser.getAllCommands());
            }  else if (cmd.equals(CombatParser.CMD_HELP_SKILL)) {
                final ICard ba = getBasicAttack();
                Game.STATUS_BAR.setStatus(ba.getName(), ba.getDescription(),
                        ba.getTrivia());
            } else if (cmd.equals(CombatParser.CMD_QUIT)) {
                System.exit(0);
            }

            /* Checks if one of the character died after applying a command */
            if (isDead() || game.getEnemy().isDead()) {
                break;
            }

            /* Draws the interface at the end of the action */
            Game.STATUS_BAR.display();
            Tools.drawInterface(game.makeStringOfGame());

        } while (!parser.getLastCommand().equals(CombatParser.CMD_END_TURN));
    }
}
