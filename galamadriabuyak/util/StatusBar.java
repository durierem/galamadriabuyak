package galamadriabuyak.util;

/**
 * Represents a status bar made of MAX_LINES lines.
 *
 * @inv
 *      getStatus().equals(a string containg exactly MAX_LINES "\n")
 */
public class StatusBar {

    // CONSTANTS

    /**
     * The maximum (and exact) number of lines a StatusBar can display.
     */
    public static final int MAX_LINES = 5;

    // ATTRIBUTES

    private String status;

    // CONSTRUCTORS

    /**
     * A new empty status bar.
     * @post
     *      getStatus().equals(the concatenation of MAX_LINES "\n")
     */
    public StatusBar() {
        setEmptyStatus();
    }

    // REQUESTS

    /**
     * The status of this status bar.
     */
    public String getStatus() {
        return status;
    }

    // COMMANDS

    /**
     * Sets the status of this status bar according to the lines in parameter.
     * @pre
     *      lines != null
     *      for all i in [1..MAX_LINES]:
     *          lines[i] != null
     * @post
     *      lines.length <= MAX_LINES ==>
     *          for all i in [1..min(lines.length, MAX_LINES)]:
     *              getStatus().contains(lines[i] + "\n")
     */
    public void setStatus(final String... lines) {
        final StringBuffer sb = new StringBuffer();
        final int linesNumber = Math.min(lines.length, MAX_LINES);

        /* Fills the first lines (or all lines if lines.length >= MAX_LINES) */
        for (int i = 0; i < linesNumber; i++) {
            sb.append(lines[i]);
            sb.append("\n");
        }

        /* Fills the remaining lines (if any) */
        for (int i = 0; i < MAX_LINES - linesNumber; i++) {
            sb.append("\n");
        }

        status = sb.toString();
    }

    /**
     * Sets the status of this status bar to an empty one.
     * @post
     *      getStatus().equals(the concatenation of MAX_LINES "\n")
     */
    public void setEmptyStatus() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < MAX_LINES; i++) {
            sb.append("\n");
        }
        status = sb.toString();
    }

    /**
     * Displays this status bar.
     */
    public void display() {
        System.out.print(status);
    }
}
