package test;

import main.java.Library.Articles;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ArticlesTest {

    @Test
    void testArticleCreation() {
        Articles article = new Articles(0, 0, "Article Title", "Author Name", null, 0, null, 0, null, null);
        assertEquals("Article Title", article.getTitle());
        assertEquals("Author Name", article.getAuthor());
    }
}