package test;
import Library.BorrowingRecord;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BorrowingRecordTest {

    @Test
    void testCreateBorrowingRecord() {
        BorrowingRecord record = new BorrowingRecord("1234", "Yasmeen", null, null, null);
        assertEquals("1234", record.getBookId());
        assertEquals("Yasmeen", record.getSubscriberName());
    }
}