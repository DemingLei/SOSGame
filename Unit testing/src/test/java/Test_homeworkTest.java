import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Test_homeworkTest {

    @org.junit.jupiter.api.Test
    void printNumber() {
        assertDoesNotThrow(() -> Test_homework.printNumber("abcdefghij"));
    }

    @org.junit.jupiter.api.Test
    void getMaxIndex() {
        assertEquals(3, Test_homework.getMaxIndex("abcabcabc"));
        assertEquals(-1, Test_homework.getMaxIndex(null));
        assertEquals(0, Test_homework.getMaxIndex(""));
    }

    @Test
    void testPrintNumber() {
        assertDoesNotThrow(() -> Test_homework.printNumber("abcdefghij"));
    }

    @Test
    void testGetMaxIndex() {
        assertEquals(3, Test_homework.getMaxIndex("abcabcabc"));
        assertEquals(-1, Test_homework.getMaxIndex(null));
        assertEquals(0, Test_homework.getMaxIndex(""));
    }
}