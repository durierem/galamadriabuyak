package galamadriabuyak.util;

import galamadriabuyak.ICard;
import galamadriabuyak.Card;
import galamadriabuyak.IEffect;
import galamadriabuyak.Effect;

import galamadriabuyak.util.json_simple.JSONArray;
import galamadriabuyak.util.json_simple.JSONObject;

/**
 * Class used to convert cards to Json and Json to cards.
 * Including methods to save/backup json to/from file.
 * 
 * @inv void
 * @pre void
 */
public interface IJsonizer {
    
    /**
     * Convert the given card into a Json object.
     * 
     * @pre 
     *      card != null
     */
    JSONObject cardToJson(ICard card);
    
    /**
     * Convert the given card's Json Object.
     * to the original ICard object.
     * 
     * @pre 
     *      jcard != null
     *      jcard.contains("name")
     *      jcard.contains("description")
     *      jcard.contains("trivia")
     *      jcard.contains("effect")
     */
    ICard jsonToCard(JSONObject jcard);
    
    /**
     * Saves the given Json Object into a new file named f.
     * 
     * @pre
     *      jcard != null
     *      f != null
     */
    void cardToFile(JSONObject jcard, String f);
    
    /**
     * Reads the f named file and returns the first Json Object found.
     * 
     * @pre 
     *      f != null
     *      f exists
     *      f not empty of JSONObject
     */
    JSONObject fileToCard(String f);
}