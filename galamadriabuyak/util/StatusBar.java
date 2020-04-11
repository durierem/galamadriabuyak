package galamadriabuyak.util;

public class StatusBar implements IStatusBar {

    // ATTRIBUTES

    private String status;
    private String lines[];

    // CONSTRUCTORS

    public StatusBar() {
        lines = new String[MAX_LINES];
        setEmptyStatus();
    }

    // REQUESTS

    public String getStatus() {
        return status;
    }

    // COMMANDS

    // TODO: Use the lines array instead of manipulating the whole status.
    public void setStatus(String... lines) {
        if (lines == null || doesContainNull(lines)) {
            throw new AssertionError("Null parameter");
        }

        final StringBuffer sb = new StringBuffer();
        final int linesNumber = Math.min(lines.length, MAX_LINES);
        int splitLinesNumber = 0;

        /* Fills the first lines (or all lines if lines.length >= MAX_LINES) */
        for (int i = 0; i < linesNumber; i++) {

            /*
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

    public void setStatusLine(int lineNumber, String status) {
        if (lineNumber < 1 || lineNumber > MAX_LINES) {
            throw new AssertionError("Out of range line");
        }
        if (status == null) {
            throw new AssertionError("Null parameter");
        }

        lines[lineNumber] = status;
    }

    public void setEmptyStatus() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lines.length; i++) {
            lines[i] = "\n";
            sb.append(lines[i]);
        }
        status = sb.toString();
    }

    public void display() {
        System.out.print(status);
    }

    // TOOLS

    /**
     * Checks if the given array contains a null element.
     * @pre
     *      array != null
     */
    private boolean doesContainNull(Object[] array) {
        assert array != null;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return true;
            }
        }
        return false;
    }
}
 