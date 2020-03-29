package galamadriabuyak.util;

import galamadriabuyak.ICard;
import galamadriabuyak.Card;
import galamadriabuyak.IEffect;
import galamadriabuyak.Effect;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import galamadriabuyak.util.json_simple.JSONArray;
import galamadriabuyak.util.json_simple.JSONObject;
import galamadriabuyak.util.json_simple.parser.JSONParser;
import galamadriabuyak.util.json_simple.parser.ParseException;

public class JSONizer implements IJSONizer {
    
    public ICard[] cardsFromFile(String f) {
        if (f == null) {
            throw new AssertionError();
        }
        
        return cardsFromJArray(fileToJArray(f));
    }
    
    /** 
     * Reads the f named file and returns the first Json Array found.
     * 
     * @pre 
     *      f != null
     *      f exists
     *      f not empty of JSONObject
     */
    private JSONArray fileToJArray(String f) {
        assert (f != null);
        
        JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(f)) {
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;
            return cards;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /** 
     * Converts the given JSONArray to an ICard array.
     * 
     * @pre
     *      array != null
     */
    private ICard[] cardsFromJArray(JSONArray array) {
        assert (array != null);
        
        ICard[] cards = new Card[array.size()];
        for (int i = 0; i < array.size(); ++i) {
            cards[i] = cardFromJSON((JSONObject) array.get(i));
        }
        return cards;
    }
    
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
    private ICard cardFromJSON(JSONObject jcard) {
        assert (jcard != null);
        
        String name = (String) jcard.get("name");
        String description = (String) jcard.get("description");
        String trivia = (String) jcard.get("trivia");
        
        JSONArray effectsArray = (JSONArray) jcard.get("effects");
        int len = effectsArray.size();
        IEffect[] effects = new IEffect[len];
        for (int i = 0; i < len; ++i) {
            JSONObject e = (JSONObject) effectsArray.get(i);
            String etype = (String) e.get("type");
            String etarget = (String) e.get("target");
            int epower = (int) (long) e.get("power");
            effects[i]= new Effect(Type.myValueOf(etype), 
                                     Target.myValueOf(etarget), epower);
        }
       
        return new Card(name, description, trivia, effects);
    }
    
}
