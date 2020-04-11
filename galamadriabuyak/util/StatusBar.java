package galamadriabuyak.util;

public class StatusBar implements IStatusBar {

    // ATTRIBUTES

    private String lines[];

    // CONSTRUCTORS

    public StatusBar() {
        lines = new String[MAX_LINES];
        setEmptyStatus();
    }

    // COMMANDS

    public void setStatus(String... lines) {
        if (lines == null || doesContainNull(lines)) {
            throw new AssertionError("Null parameter");
        }
        if (lines.length > MAX_LINES) {
            throw new AssertionError("Too much lines");
        }

        final int linesNumber = Math.min(lines.length, MAX_LINES);
        int splitLinesNumber = 0;

        /* Fills the first lines (or all lines if lines.length >= MAX_LINES) */
        for (int i = 0; i < linesNumber; i++) {
            if (lines[i].length() >= WINDOW_WIDTH) {
                this.lines[i] = " " + lines[i].substring(0, WINDOW_WIDTH)
                        + "\n";
                this.lines[i + 1] = " " + lines[i].substring(WINDOW_WIDTH)
                        + "\n";
                splitLinesNumber++;
            } else {
                this.lines[i] = " " + lines[i] + "\n";
            }
        }

        /* Fills the remaining lines (if any) with "\n" */
        for (int i = linesNumber; i < MAX_LINES - splitLinesNumber; i++) {
            this.lines[i] = "\n";
        }
    }

    public void setStatusLine(int lineNumber, String status) {
        if (lineNumber < 1 || lineNumber > MAX_LINES) {
            throw new AssertionError("Out of range line");
        }
        if (status == null) {
            throw new AssertionError("Null parameter");
        }

        this.lines[lineNumber - 1] = " " + status + "\n";
    }

    public void setEmptyStatus() {
        for (int i = 0; i < this.lines.length; i++) {
            this.lines[i] = "\n";
        }
    }

    public void display() {
        for (int i = 0; i < MAX_LINES; i++) {
            System.out.print(this.lines[i]);
        }
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
 
