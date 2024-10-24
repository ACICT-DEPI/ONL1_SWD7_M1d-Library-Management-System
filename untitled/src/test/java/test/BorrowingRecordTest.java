package test;
import Library.BorrowingRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class BorrowingRecordTest {

    private BorrowingRecord record;
    private Date borrowDate;
    private Date dueDate;

    @BeforeEach
    void setUp() {
        // Initialize borrowDate and dueDate
        borrowDate = new Date(); // Current date for borrowing
        dueDate = new Date(borrowDate.getTime() + (7 * 24 * 60 * 60 * 1000L)); // Due date 7 days after borrow date
        
        // Create a new BorrowingRecord object for each test
        record = new BorrowingRecord("John Doe", "Java Programming", "Book", borrowDate, dueDate);
    }

    @Test
    void testGetSubscriberName() {
        assertEquals("John Doe", record.getSubscriberName());
    }

    @Test
    void testGetItemTitle() {
        assertEquals("Java Programming", record.getItemTitle());
    }

    @Test
    void testGetItemType() {
        assertEquals("Book", record.getItemType());
    }

    @Test
    void testGetBorrowDate() {
        assertEquals(borrowDate, record.getBorrowDate());
    }

    @Test
    void testGetDueDate() {
        assertEquals(dueDate, record.getDueDate());
    }

    @Test
    void testIsReturnedInitially() {
        assertFalse(record.isReturned());
    }

    @Test
    void testMarkAsReturned() {
        record.markAsReturned();
        assertTrue(record.isReturned());
    }

    @Test
    void testIsOverdue() {
        // Initially, the due date is in the future, so the record should not be overdue
        assertFalse(record.isOverdue());
        
        // Simulate an overdue record by setting a past due date
        Date pastDueDate = new Date(borrowDate.getTime() - (1 * 24 * 60 * 60 * 1000L)); // 1 day in the past
        record.extendDueDate(pastDueDate);
        assertTrue(record.isOverdue());
    }

    @Test
    void testExtendDueDate() {
        Date newDueDate = new Date(dueDate.getTime() + (3 * 24 * 60 * 60 * 1000L)); // Extend due date by 3 days
        record.extendDueDate(newDueDate);
        assertEquals(newDueDate, record.getDueDate());
    }

    @Test
    void testGetBorrowDuration() {
        long expectedDuration = 7; // Expected duration in days (since we set dueDate to 7 days later)
        assertEquals(expectedDuration, record.getBorrowDuration());
    }

    @Test
    void testSendReminder() {
        // The method prints a reminder; we can't easily assert printed output, but we can ensure it does not throw exceptions
        assertDoesNotThrow(() -> record.sendReminder());
    }

    @Test
    void testNotifyOverdue() {
        // The method prints a notification; we can't easily assert printed output, but we can ensure it does not throw exceptions
        assertDoesNotThrow(() -> record.notifyOverdue());
    }

    @Test
    void testToString() {
        String expectedString = "Borrowing Record {\n " +
                "Subscriber Name: John Doe" +
                "\nItem Title: Java Programming" +
                "\nItem Type: Book" +
                "\nBorrow Date: " + borrowDate +
                "\nDue Date: " + dueDate +
                "\nReturned: No\n}";
        assertEquals(expectedString, record.toString());
    }
}
