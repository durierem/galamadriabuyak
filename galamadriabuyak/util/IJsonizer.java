package galamadriabuyak.util;

import galamadriabuyak.ICard;

/**
 * Class used to convert cards to Json and Json to cards.
 * Including methods to save/backup json to/from file.
 * 
 * @inv void
 * @pre void
 */
public interface IJSONizer {

    /**
     * Returns the JSON encoded cards array written in the file f.
     */
    ICard[] cardsFromFile(String f);
}
