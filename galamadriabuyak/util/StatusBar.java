package galamadriabuyak.util;

/**
 * Represents a status bar made of MAX_LINES lines.
 *
 * @inv
 *      getStatus().equals(a string containg exactly MAX_LINES "\n")
 */
public class StatusBar {

    // CONSTANTS

    public static final int MAX_LINES = 5;
    public static final int WINDOW_WIDTH = 78;

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
        int splitLinesNumber = 0;

        /* Fills the first lines (or all lines if lines.length >= MAX_LINES) */
        for (int i = 0; i < linesNumber; i++) {

            /**
             * If the line doesn't fit, split it into two lines
             *
             * NOTE: this is enough in the case of this game since the only
             * things that doesn't fit within the default 80 characters are
             * likely to be cards' descriptions ans shuch. However, for the sake
             * of corectness, the status bar should handle even longer strings.
             */
            if (lines[i].length() >= WINDOW_WIDTH) {
                sb.append(" ");
                sb.append(lines[i].substring(0, WINDOW_WIDTH));
                sb.append("\n");
                sb.append(" ");
                sb.append(lines[i].substring(WINDOW_WIDTH));
                sb.append("\n");
                splitLinesNumber++;
            } else {
                sb.append(" ");
                sb.append(lines[i]);
                sb.append("\n");
            }
        }

        /* Fills the remaining lines (if any) with "\n" */
        for (int i = 0; i < MAX_LINES - linesNumber - splitLinesNumber; i++) {
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
