package tetra.logic;

/**
 * Finds and clears complete lines in a matrix.
 */
public class LineClearer {

    private Matrix matrix;

    /**
     * Class constructor which specifies the matrix to inspect.
     *
     * @param matrix matrix to inspect
     */
    public LineClearer(Matrix matrix) {
        this.matrix = matrix;
    }

    /**
     * Performs a complete line clear, including all the steps involved. This
     * method is basically an aggregate of all the operations provided by this
     * class. First, it locates the first and last complete line in the range
     * yMin..yMax, then it clears any lines in-between, and finally it shifts
     * any rows above the cleared lines downwards by an equal amount.
     *
     * @param yMin y-coordinate of the first line to inspect, inclusive
     * @param yMax y-coordinate of the last line to inspect, inclusive
     * @return the number of cleared lines, which may be 0
     */
    public int clearCompleteLinesAndShiftDown(int yMin, int yMax) {
        int first = findFirstCompleteLine(yMin, yMax);
        int last = findLastCompleteLine(yMin, yMax);
        int count = last - first + 1;

        if (first == -1) {
            return 0;
        }

        clearLines(first, last);
        shiftRowsDown(first - 1, count);

        return count;
    }

    /**
     * Returns true if the specified line is complete, or in other words if it's
     * full.
     *
     * @param y y-coordinate of the line to inspect
     * @return true if the line is complete, false otherwise
     */
    public boolean isCompleteLine(int y) {
        for (int x = 0; x < matrix.getCols(); x++) {
            if (!matrix.isOccupied(x, y)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if the number of blocks on the specified line is zero, false
     * otherwise.
     *
     * @param y y-coordinate of the line to inspect
     * @return true if the line is empty, false otherwise
     */
    public boolean isEmptyLine(int y) {
        for (int x = 0; x < matrix.getCols(); x++) {
            if (matrix.isOccupied(x, y)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Locates the first complete line in the range yMin..yMax, including the
     * lines yMin and yMax.
     *
     * @param yMin y-coordinate of the first line to inspect, inclusive
     * @param yMax y-coordinate of the last line to inspect, inclusive
     * @return y-coordinate of the first complete line, or -1
     */
    public int findFirstCompleteLine(int yMin, int yMax) {
        if (yMin > yMax) {
            return -1;
        }

        for (int y = yMin; y <= yMax; y++) {
            if (isCompleteLine(y)) {
                return y;
            }
        }

        return -1;
    }

    /**
     * Locates the last complete line in the range yMin..yMax, including the
     * lines yMin and yMax.
     *
     * @param yMin y-coordinate of the first line to inspect, inclusive
     * @param yMax y-coordinate of the last line to inspect, inclusive
     * @return y-coordinate of the last complete line, or -1
     */
    public int findLastCompleteLine(int yMin, int yMax) {
        if (yMin > yMax) {
            return -1;
        }

        for (int y = yMax; y >= yMin; y--) {
            if (isCompleteLine(y)) {
                return y;
            }
        }

        return -1;
    }

    /**
     * Removes all blocks from lines whose y-coordinates are within the range
     * first..last, including the rows first and last.
     *
     * @param first y-coordinate of the first line to clear, inclusive
     * @param last y-coordinate of last line to clear, inclusive
     */
    public void clearLines(int first, int last) {
        if (first > last) {
            return;
        }

        for (int y = last; y >= first; y--) {
            for (int x = 0; x < matrix.getCols(); x++) {
                matrix.setBlock(x, y, null);
            }
        }
    }

    /**
     * Shifts rows above a specified point, a specified number of steps in the
     * downward direction.
     *
     * @param lastRowToMove y-coordinate of the last row to move
     * @param howMuch the number of steps to move
     */
    public void shiftRowsDown(int lastRowToMove, int howMuch) {
        if (lastRowToMove < 0 || lastRowToMove >= matrix.getRows()) {
            return;
        }

        if (howMuch <= 0 || lastRowToMove + howMuch >= matrix.getRows()) {
            return;
        }

        for (int y = lastRowToMove; y >= 0; y--) {
            for (int x = 0; x < matrix.getCols(); x++) {
                matrix.setBlock(x, y + howMuch, matrix.getBlock(x, y));
            }
        }

        clearLines(0, howMuch - 1);
    }

}
