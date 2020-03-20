package galamadriabuyak;
   
/**
 * Modélise un personnage du jeu.
 * Les personnages peuvent être des ennemis ou le joueur.
 */
public interface ICharacter {
    
    // REQUÊTES

    /**
     * Le nom du personnage.
     */
    String getName();
    
    /**
     * Le nombre de points de vie restant du personnage
     */
    int getHealth();

    /**
     * Le deck du personnage.
     */
    IDeck getDeck();

    /**
     * L'attaque de base du personnage.
     */
    IBasicAttack getBasicAttack();

    /**
     * Indique si le personnage est vivant.
     */
    boolean isDead();

    // COMMANDES
    
    /**
     * Définit les points du vie du personnage à q.
     * @pre
     *      q >= 0
     * @post
     *      getHealth() == q
     */
    void setHealthTo(int q);

    /**
     * Augmente de q les points de vie du personnage.
     * @pre
     *      q >= 0
     * @post
     *      getHealth() == old getHealth() + q
     */
    void setHealthUp(int q);

    /**
     * Diminue de q les points de vie du personnage.
     * @pre
     *      q >= 0
     * @post
     *      getHealth() == old getHealth() - q
     */
    void setHealthDown(int q);
}
