package test;

import Library.Books;
import Library.Contents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// A simple subclass of Contents for testing purposes
class TestContents extends Contents {

    public TestContents(int itemID, int libraryID, String category, String title, String author, String publisher, int productionYear, String status, int copies) {
        super(itemID, libraryID, category, title, author, publisher, productionYear, status, copies);
    }
}

class ContentsTest {

    private TestContents content;

    @BeforeEach
    void setUp() {
        // Create a new TestContents object for each test
        content = new TestContents(101, 10, "Book", "Effective Java", "Joshua Bloch", "Addison-Wesley", 2008, "Available", 5);
    }

    @Test
    void testGetItemID() {
        assertEquals(101, content.getItemID());
    }

    @Test
    void testGetLibraryID() {
        assertEquals(10, content.getLibraryID());
    }

    @Test
    void testGetCategory() {
        assertEquals("Book", content.getCategory());
    }

    @Test
    void testGetTitle() {
        assertEquals("Effective Java", content.getTitle());
    }

    @Test
    void testGetAuthor() {
        assertEquals("Joshua Bloch", content.getAuthor());
    }

    @Test
    void testGetPublisher() {
        assertEquals("Addison-Wesley", content.getPublisher());
    }

    @Test
    void testGetProductionYear() {
        assertEquals(2008, content.getProductionYear());
    }

    @Test
    void testGetStatus() {
        assertEquals("Available", content.getStatus());
    }

    @Test
    void testGetCopies() {
        assertEquals(5, content.getCopies());
    }

    @Test
    void testMatches() {
        // The matches method returns 0 in the current implementation, so test that it returns 0
        assertEquals(0, content.matches("Effective Java"));
    }
}