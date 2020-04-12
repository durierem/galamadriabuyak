package galamadriabuyak;

/**
 * Represents a card.
 * Cards have a name, a description of their effects, and a bit of trivia. Their
 * effects can be applied to the game by a specified caller (be it the player
 * or an enemy).
 * @inv
 *      name != null
 *      description != null
 *      trivia != null
 *      effects != null
 *      forall i in [1..effects.length]: effects[i] != null
 * @cons
 *      $DESC$
 *          A new card, with the given arguments.
 *      $ARGS$
 *          String name
 *          String description
 *          String trivia
 *          IEffect[] effects
 *      $PRE$
 *          name != null
 *          description != null
 *          trivia != null
 *          effects != null
 *          forall i in [1..effects.length]: effects[i] != null
 *      $POST$
 *          getName() == name
 *          getDescription() == description
 *          getTrivia() == trivia
 *          getEffects() == effects
 */
public interface ICard {

    // REQUESTS

    /**
     * Returns the name of the card.
     */
    String getName();

    /**
     * Returns the description of the card.
     */
    String getDescription();

    /**
     * Returns trivias's card.
     */
    String getTrivia();

    /**
     * Returns the array of effects.
     */
    IEffect[] getEffects();

    // COMMANDS

    /**
     * Apply the different effects of the cards on the different targets.
     * 
     * @pre
     *      game != null
     *      caller != null
     * @post
     *      the game inner state has changed according to this.getEffects()
     */
    void applyEffects(Game game, Object caller);
}
