package Library;
import java.util.Scanner;

public interface LibraryView {
    void showMenu();
    void handleSelection(Scanner sc, int choice);
    void run();
    void viewBooks();
    void searchBook(String title);
    void borrowBook(String bookId);
    void returnBook(String bookId);
}

