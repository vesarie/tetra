package tetra.logic;

public class LineClearer {

    private Matrix matrix;

    public LineClearer(Matrix matrix) {
        this.matrix = matrix;
    }
    
    public void clearCompleteLinesAndMoveRowsAbove(int yMin, int yMax) {
        int first = findFirstCompleteLine(yMin, yMax);
        int last = findLastCompleteLine(yMin, yMax);

        if (first != -1) {
            clearLines(first, last);
            moveRowsDown(first - 1, last - first + 1);
        }
    }

    public boolean isCompleteLine(int y) {
        for (int x = 0; x < matrix.getCols(); x++) {
            if (!matrix.isOccupied(x, y)) {
                return false;
            }
        }

        return true;
    }

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

    public void moveRowsDown(int lastRowToMove, int howMuch) {
        if (lastRowToMove < 0 || lastRowToMove >= matrix.getRows() - 1) {
            return;
        }

        if (howMuch <= 0 || lastRowToMove + howMuch > matrix.getRows() - 1) {
            return;
        }

        for (int y = lastRowToMove; y >= 0; y--) {
            for (int x = 0; x < matrix.getCols(); x++) {
                matrix.setBlock(x, y + howMuch, matrix.getBlock(x, y));
            }
        }
    }

}