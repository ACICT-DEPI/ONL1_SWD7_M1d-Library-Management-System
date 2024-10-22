package test;
import Library.UserMainPage;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UserMainPageTest {

    @Test
    void testCreateUserMainPage() {
        UserMainPage page = new UserMainPage(null);
        assertNotNull(page);
    }
}
