package test;
import Library.Subscriber;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SubscriberTest {

    @Test
    void testCreateSubscriber() {
        Subscriber subscriber = new Subscriber("Yasmeen", "12345", null, null, null, null);
        assertEquals("Yasmeen", subscriber.getName());
        assertEquals("12345", subscriber.getId());
    }

   
}
