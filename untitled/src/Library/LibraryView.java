package Library;
import java.util.Scanner;

public interface LibraryView {
    void showMenu();
    void handleSelection(Scanner sc, int choice);
    void performSearch(Scanner sc);
    void borrowBook(String bookId);
    void returnBook(String bookId);
}

