package Library;

import java.util.Scanner;

public class UserMainPage implements LibraryView {

    @Override
    public void showMenu() {
        System.out.println("User Menu:");
        System.out.println("1. View available books");
        System.out.println("2. Borrow a book");
        System.out.println("3. Return a book");
        System.out.println("4. Logout");
    }

    @Override
    public void run() {

    }

    @Override
    public void viewBooks() {

    }

    @Override
    public void searchBook(String title) {

    }

    @Override
    public void borrowBook(String bookId) {

    }

    @Override
    public void returnBook(String bookId) {

    }

    @Override
    public void handleSelection(Scanner sc, int choice) {
        switch (choice) {
            case 1:
                System.out.println("Displaying available books...");
                // Implement the logic to display available books
                break;
            case 2:
                System.out.println("Borrowing a book...");
                // Implement the logic to borrow a book
                break;
            case 3:
                System.out.println("Returning a book...");
                // Implement the logic to return a book
                break;
            case 4:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

