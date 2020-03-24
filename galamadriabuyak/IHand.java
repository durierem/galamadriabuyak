package galamadriabuak;


public interface IHand
{
    
    //Requests
    
    /**
     * Returns the size of the hand.
     */
    int getSize();
    
    /**
     * Returns the nth card of the hand.
     */
    ICard getCard(int n);
    
    
}
