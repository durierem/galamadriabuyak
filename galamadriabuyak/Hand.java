package galamadriabuyak;


public class Hand implements IHand {

    // ATTRIBUTS    
    
    ICard[] handTab = new ICard[MAX_SIZE];
    
    // REQUETES
    
    
    public int getSize(){
        return handTab.length;
    }
    
    /**
     * Returns the nth card of the hand.
     * 
     * @pre 
     *      0 <= n <= getSize() - 1
     * @post
     *      getSize() = old getSize()
     */
    public ICard getCard(int n){
        if (0 > n || getSize() - 1 > n){
            throw new AssertionError();
        }
        return handTab[n];
    }
}
