package galamadriabuak;

public interface ICard {
    
    // Requests
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
    
    // Commands 
    /**
     * Apply the different effects of the cards on the different targets.
     * 
     * @pre
     *      getEffects() != null 
     *      game != null
     * @post
     */
    void applyEffects(Game game);
}
