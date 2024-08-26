package Library;

public interface LibraryView {
    void viewBooks();
    void searchBook(String title);
    void borrowBook(String bookId);
    void returnBook(String bookId);
}

