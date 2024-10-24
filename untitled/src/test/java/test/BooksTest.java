package test;
import Library.Books;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class BooksTest {

    private Books book;
    private Books[] booksArray;
    private int initialCount;

    @BeforeEach
    void setUp() {
        // Initialize a book instance
        book = new Books(1, 101, "Java Programming", "John Doe", "TechPub", 
                         2024, "Available", 5, 101, 1);
                         
        // Set up an array of books for testing
        booksArray = new Books[10];  // Size of array can be 10
        booksArray[0] = book;
        book.books = booksArray;  // Assigning to the books array in the class
        initialCount = 1;  // We have one book in the array initially
        book.count = initialCount;  // Set the count of books
    }

    @Test
    void testGetsno() {
        assertEquals(101, book.getsno());
    }

    @Test
    void testSetsno() {
        book.setsno(202);
        assertEquals(202, book.getsno());
    }

    @Test
    void testGetCount() {
        assertEquals(1, book.getCount());
    }

    @Test
    void testFindBook() {
        // Test finding a book by serial number
        int foundIndex = book.findBook(101);
        assertEquals(0, foundIndex);  // The book with serial number 101 should be at index 0

        // Test finding a non-existent book
        int notFoundIndex = book.findBook(202);
        assertEquals(-1, notFoundIndex);  // Should return -1 as book with serial number 202 does not exist
    }

    @Test
    void testGetBook() {
        // Test getting a book within bounds
        Books retrievedBook = book.getBook(0);
        assertNotNull(retrievedBook);
        assertEquals("Java Programming", retrievedBook.getTitle());

        // Test getting a book out of bounds
        Books nullBook = book.getBook(2);  // Index 2 is out of bounds as only 1 book exists
        assertNull(nullBook);
    }

    @Test
    void testAddBook() {
        Books newBook = new Books(2, 102, "Advanced Java", "Jane Smith", "CodePub",
                                  2024, "Available", 3, 202, 1);

        boolean added = book.addBook(newBook);
        assertTrue(added);  // The new book should be added successfully
        assertEquals(2, book.count);  // Count should increment

        // Test adding the same book again (should fail because duplicate sno)
        boolean addedAgain = book.addBook(newBook);
        assertFalse(addedAgain);  // Should return false because of duplicate sno
    }

    @Test
    void testRemoveBook() {
        Books bookToRemove = new Books(1, 101, "Java Programming", "John Doe", "TechPub",
                                       2024, "Available", 5, 101, 1);

        // Test removing an existing book
        boolean removed = book.removeBook(bookToRemove);
        assertTrue(removed);  // Should remove the book
        assertEquals(0, book.count);  // Count should decrease

        // Test removing a non-existent book
        boolean removedAgain = book.removeBook(bookToRemove);
        assertFalse(removedAgain);  // Should return false because the book was already removed
    }

    @Test
    void testGetAllBook() {
        // Capture the console output (if needed), for now, we'll check for count
        assertEquals(1, book.count);  // One book should be in the list
    }


}
