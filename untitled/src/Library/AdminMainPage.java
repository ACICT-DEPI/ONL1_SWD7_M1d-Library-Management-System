package Library;

import java.util.Scanner;

public class AdminMainPage implements LibraryView {

    @Override
    public void showMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Add a new book");
        System.out.println("2. Remove a book");
        System.out.println("3. View all subscribers");
        System.out.println("4. Logout");
    }

    @Override
    public void handleSelection(Scanner sc, int choice) {
        switch (choice) {
            case 1:
                System.out.println("Adding a new book...");
                // Implement the logic to add a new book
                break;
            case 2:
                System.out.println("Removing a book...");
                // Implement the logic to remove a book
                break;
            case 3:
                System.out.println("Viewing all subscribers...");
                // Implement the logic to view all subscribers
                break;
            case 4:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
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
}
