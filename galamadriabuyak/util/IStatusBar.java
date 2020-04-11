package galamadriabuyak.util;

/**
 * Represents a status bar made of MAX_LINES lines.
 *
 * @inv
 *      getStatus().equals(a string containg exactly MAX_LINES "\n")
 * @cons
 *      $DESC$  A new empty status bar.
 *      $POST$  getStatus().equals(the concatenation of MAX_LINES "\n")
 */
public interface IStatusBar {

    // CONSTANTS

    int MAX_LINES = 4;
    int WINDOW_WIDTH = 78;

    // REQUESTS

    /**
     * The status of this status bar.
     */
    String getStatus();

    // COMMANDS

    /**
     * Sets the status of this status bar from the given lines.
     * @pre
     *      Let n ::= the number of lines given
     *          n <= MAX_LINES
     *          forall i in [1..n]
     *              lines[i] != null
     * @post
     *      forall i in [1..min(lines.length, MAX_LINES)]:
     *          getStatus().contains(lines[i] + "\n")
     */
    void setStatus(String... lines);

    /**
     * Sets the status at lineNumber to status.
     * @pre
     *      1 <= lineNumber <= MAX_LINES
     *      status != null
     * @post
     *      Let 
     *      forall i in [1..MAX_LINES]:
     *      i != lineNumber ==>
     *          line i in getStatus() == old line i in getStatus()
     *      i == lineNumber ==>
     *          line i in getStatus() == status
     */
    void setStatusLine(int lineNumber, String status);

    /**
     * Sets the status of this status bar to an empty one.
     * @post
     *      getStatus().equals(the concatenation of MAX_LINES "\n")
     */
    void setEmptyStatus();

    /**
     * Displays this status bar.
     */
    void display();
}
 