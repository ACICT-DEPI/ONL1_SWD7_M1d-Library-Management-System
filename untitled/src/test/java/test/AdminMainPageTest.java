package test;
import  Library.AdminMainPage;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AdminMainPageTest {

    @Test
    void testAdminMainPageCreation() {
        AdminMainPage page = new AdminMainPage();
        assertNotNull(page);
    }
}