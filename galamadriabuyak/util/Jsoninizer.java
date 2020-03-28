package galamadriabuyak.util;

import galamadriabuyak.ICard;
import galamadriabuyak.IEffect;

import java.io.FileWriter;
import java.io.IOException;

import galamadriabuyak.util.json_simple.JSONArray;
import galamadriabuyak.util.json_simple.JSONObject;

public class Jsoninizer {
    
    public JSONObject CardToJson(ICard card) {
        JSONObject jcard = new JSONObject();
        jcard.put("name", card.getName());
        jcard.put("description", card.getDescription());
        jcard.put("trivia", card.getTrivia());
        
        JSONArray effectArray = new JSONArray();
        IEffect[] effects = card.getEffects();
        for (int i = 0; i < effects.length; ++i) {
            JSONObject e = new JSONObject();
            e.put("type", effects[i].getType().toString());
            e.put("target", effects[i].getTarget().toString());
            e.put("power", effects[i].getPower());
            effectArray.add(e);
        }
        jcard.put("effect", effectArray);
        return jcard;
        //Adding the card to the list.
    }
    
    public void CardsToFile(JSONObject jcard, String fname) {
        //Creating list Card.
        JSONArray cardList = new JSONArray();
        cardList.add(jcard);
        
        //Write JSON file.
        try(FileWriter file = new FileWriter("cards.json")) {
            file.write(cardList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * IM WORKING ON IT DON'T TOUCH PLEASE
     */
}
    
