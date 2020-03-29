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
    
    public JSONObject cardToJSON(ICard card) {
        if (card == null) {
            throw new AssertionError();
        }
        
        JSONObject jcard = new JSONObject();
        jcard.put("name", card.getName());
        jcard.put("description", card.getDescription());
        jcard.put("trivia", card.getTrivia());
        
        IEffect[] effects = card.getEffects();
        JSONArray effectsArray = new JSONArray();
        for (int i = 0; i < effects.length; ++i) {
            JSONObject e = new JSONObject();
            e.put("type", effects[i].getType().toString());
            e.put("target", effects[i].getTarget().toString());
            e.put("power", effects[i].getPower());
            effectsArray.add(e);
        }
        jcard.put("effect", effectsArray);
        return jcard;
        //Adding the card to the list.
    }
    
    public ICard cardFromJSON(JSONObject jcard) {
        if (jcard == null) {
            throw new AssertionError();
        }
        
        String name = (String) jcard.get("name");
        String description = (String) jcard.get("description");
        String trivia = (String) jcard.get("trivia");
        
        JSONArray effectsArray = (JSONArray) jcard.get("effect");
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
    
    public void cardToFile(JSONObject jcard, String f) {
        if (jcard == null || f == null) {
            throw new AssertionError();
        }
        
        //Write JSON file.
        try(FileWriter file = new FileWriter(f)) {
            file.write(jcard.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public JSONObject fileToCard(String f) {
        if (f == null) {
            throw new AssertionError();
        }
        
        JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(f)) {
            Object obj = jsonParser.parse(reader);
            JSONObject card = (JSONObject) obj;
            return card;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}

//don't touch please
// //Creating list Card.
//   JSONArray cardList = new JSONArray();
//   cardList.add(jcard);
