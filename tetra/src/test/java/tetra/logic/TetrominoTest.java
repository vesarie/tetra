package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetrominoTest {

    @Test
    public void isOccupiedAndGetBlockReturnTheSameAnswer() {
        // Test with one block
        Tetromino tetromino = Tetromino.Z;
        Direction direction = Direction.DOWN;

        for (int y = 0; y < tetromino.HEIGHT; y++) {
            for (int x = 0; x < tetromino.WIDTH; x++) {
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
        assertEquals(0, Tetromino.T.getColor());
    }

}