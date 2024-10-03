package test;
import Library.Main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void testMainExecution() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
