package Library;

import java.util.Scanner;

public class AdminMainPage implements LibraryView {

    @Override
    public void showMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Add a new Content type");
        System.out.println("2. Remove a content");
        System.out.println("3. View all subscribers");
        System.out.println("4. View all contents");
        System.out.println("5. View all contents");
        System.out.println("6. View top 10 most rated contents");
        System.out.println("7. Search");
        System.out.println("7. View borrowing log");
        System.out.println("8. Logout");
    }

    @Override
    // Method to handle the user's selection
    public void handleSelection(Scanner sc, int choice) {
        switch (choice) {
            case 1 -> addNewContentType();
            case 2 -> removeContent();
            case 3 -> viewAllSubscribers();
            case 4 -> viewAllContents();  // Both cases 4 and 5 handle the same action
            case 5 -> viewTop10MostRatedContents();
            case 6 -> search();
            case 7 -> viewBorrowingLog();
            case 8 -> {
                System.out.println("Logging out...");
                break;
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    @Override
    public void performSearch(Scanner sc) {

    }

    @Override
    public void borrowBook(String bookId) {

    }

    @Override
    public void returnBook(String bookId) {

    }

    private void addNewContentType() {}
    private void removeContent() {}
    private void viewAllSubscribers() {}
    private void viewAllContents() {}
    private void viewTop10MostRatedContents() {}
    private void search() {}
    private void viewBorrowingLog() {}
    private void logout() {}
}
