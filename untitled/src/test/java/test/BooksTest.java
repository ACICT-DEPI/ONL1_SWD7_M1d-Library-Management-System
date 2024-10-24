package test;
import Library.Books;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BooksTest {

    @Test
    void testCreateBook() {
        Books book = new Books(0, 0, "1234", "Java Programming", null, 0, null, 0, 0, 0);
        assertEquals(1234, book.getsno());
        assertEquals("Java Programming", book.getTitle());
    }

    @Test
    void testBorrowBook() {
        Books book = new Books(0, 0, "1234", "Java Programming", null, 0, null, 0, 0, 0);
        assertTrue(book.isAvailable());

    }

    @Test
    void testReturnBook() {
        Books book = new Books(0, 0, "1234", "Java Programming", null, 0, null, 0, 0, 0);

        assertTrue(book.isAvailable());
    }
}