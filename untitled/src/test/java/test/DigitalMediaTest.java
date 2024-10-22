package test;
import Library.DigitalMedia;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DigitalMediaTest {

    @Test
    void testCreateDigitalMedia() {
        DigitalMedia media = new DigitalMedia("Media Title", 1024);
        assertEquals("Media Title", media.getTitle());
        assertEquals(1024, media.getTitle());
    }
}