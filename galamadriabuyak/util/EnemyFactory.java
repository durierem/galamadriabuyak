package galamadriabuyak.util;

import galamadriabuyak.IPlayer;
import galamadriabuyak.IEnemy;
import galamadriabuyak.Enemy;
import galamadriabuyak.IDeck;
import galamadriabuyak.Deck;
import galamadriabuyak.IHand;
import galamadriabuyak.Hand;
import galamadriabuyak.BasicAttack;

/**
 * Non instantiable class made to make new Enemies 
 * with the same strength than the player.
 */
public abstract class EnemyFactory {
    
    public static IEnemy createEnemy(IPlayer p) {
        String name = "Jesus"; //Still to do
        
        int level = (int) (p.getLevel() * alea(60, 90) / 100); //Still to adjust
        int health = p.getHealth() * alea(60, 90) / 100; //Still to adjust
        //Should we base health on the level, wich will be based on the player level.
        //Because like this the level have no interest.
        
        BasicAttack basicAttack = null; //Still to do
        //Choose a random basicAttack.
        
        IDeck deck = new Deck();
        //We will have to fill the deck with random (or not) cards.
        
        IHand hand = new Hand();
        
        return new Enemy(name, level, health, basicAttack, deck, hand);
    }
    
    /**
     * Return a random number in range of [min, max] 
     */
    private static int alea(int min,int max) {
        return min + (int) (Math.random() * (max - min + 1));
    } 
}