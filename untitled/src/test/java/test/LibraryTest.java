package test;
import Library.Library;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LibraryTest {

    @Test
    void testCreateLibrary() {
        Library library = new Library(1, "Central Branch");
        assertEquals(1, library.getId());
        assertEquals("Central Branch", library.getName());
    }

    @Test
    void testSaveLibraryToFile() {
        Library library = new Library(1, "Central Branch");
        assertDoesNotThrow(() -> library.saveLibraryToFile());
    }
}
