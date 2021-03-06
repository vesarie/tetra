package tetra.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class TetrominoTest {

    @Test
    public void isOccupiedAndGetBlockReturnTheSameAnswer() {
        // Test with one block
        Tetromino tetromino = Tetromino.Z;
        Direction direction = Direction.DOWN;

        for (int y = 0; y < tetromino.height; y++) {
            for (int x = 0; x < tetromino.width; x++) {
                String coordinates = String.format("Tetromino Z[DOWN] block (x,y) = (%d,%d)", x, y);

                boolean isOccupied = tetromino.isOccupied(direction, x, y);
                Block block = tetromino.getBlock(direction, x, y);

                assertEquals(coordinates, isOccupied, (block != null));
            }
        }
    }

    @Test
    public void getColorReturnsTheRightColor() {
        // Colors will be set when programming the GUI
        assertEquals(0xaa00ff, Tetromino.T.getColor());
    }

    @Test
    public void isOccupiedReturnsFalseForNegativeCoordinates() {
        assertEquals(false, Tetromino.J.isOccupied(Direction.UP, -1, 0));
        assertEquals(false, Tetromino.J.isOccupied(Direction.UP, 0, -1));
        assertEquals(false, Tetromino.J.isOccupied(Direction.UP, -1, -1));
    }

    @Test
    public void isOccupiedReturnsFalseForTooBigCoordinates() {
        assertEquals(false, Tetromino.I.isOccupied(Direction.UP, 4, 1));
        assertEquals(false, Tetromino.I.isOccupied(Direction.RIGHT, 2, 4));
        assertEquals(false, Tetromino.I.isOccupied(Direction.DOWN, 4, 4));
    }

    @Test
    public void isOccupiedWorksForCornerCoordinates() {
        assertEquals(true, Tetromino.J.isOccupied(Direction.UP, 0, 0));
        assertEquals(true, Tetromino.I.isOccupied(Direction.UP, 3, 1));
    }

    @Test
    public void isOccupiedReturnsTrueForOccupiedTopLeftCorner() {
        assertEquals(true, Tetromino.J.isOccupied(Direction.UP, 0, 0));
        assertEquals(true, Tetromino.S.isOccupied(Direction.UP, 0, 1));
        assertEquals(true, Tetromino.S.isOccupied(Direction.UP, 1, 0));
        assertEquals(true, Tetromino.S.isOccupied(Direction.UP, 1, 1));
    }

    @Test
    public void isOccupiedWorksForHorizontalLineShape() {
        assertEquals(false, Tetromino.I.isOccupied(Direction.UP, 5, 1));
        assertEquals(false, Tetromino.I.isOccupied(Direction.UP, 4, 1));
        assertEquals(true, Tetromino.I.isOccupied(Direction.UP, 3, 1));
        assertEquals(true, Tetromino.I.isOccupied(Direction.UP, 2, 1));
        assertEquals(true, Tetromino.I.isOccupied(Direction.UP, 1, 1));
        assertEquals(true, Tetromino.I.isOccupied(Direction.UP, 0, 1));
        assertEquals(false, Tetromino.I.isOccupied(Direction.UP, -1, 1));
        assertEquals(false, Tetromino.I.isOccupied(Direction.UP, -2, 1));
    }

    @Test
    public void isOccupiedWorksForVerticalLineShape() {
        assertEquals(false, Tetromino.I.isOccupied(Direction.RIGHT, 2, 5));
        assertEquals(false, Tetromino.I.isOccupied(Direction.RIGHT, 2, 4));
        assertEquals(true, Tetromino.I.isOccupied(Direction.RIGHT, 2, 3));
        assertEquals(true, Tetromino.I.isOccupied(Direction.RIGHT, 2, 2));
        assertEquals(true, Tetromino.I.isOccupied(Direction.RIGHT, 2, 1));
        assertEquals(true, Tetromino.I.isOccupied(Direction.RIGHT, 2, 0));
        assertEquals(false, Tetromino.I.isOccupied(Direction.RIGHT, 2, -1));
        assertEquals(false, Tetromino.I.isOccupied(Direction.RIGHT, 2, -2));
    }

}
