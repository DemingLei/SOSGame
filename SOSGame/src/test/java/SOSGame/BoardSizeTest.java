package SOSGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BoardSizeTest {

    @Test
    void testGetSize() {
        BoardSize boardSize = new BoardSize(5); // Create a BoardSize object and pass in the value 5
        // Use assertEquals to ensure that the return value is 5, otherwise the test fails
        assertEquals(5, boardSize.getSize(), "BoardSize.getSize() should return the size set at construction");
    }
}
