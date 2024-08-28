package Library;

import java.util.List;
import java.util.Scanner;

public class UserMainPage implements LibraryView {
    private final List<Library> allBranches;

    public UserMainPage(List<Library> allBranches) {
        this.allBranches = allBranches;
    }

    @Override
    public void borrowBook(String bookId) {

    }

    @Override
    public void returnBook(String bookId) {

    }

    @Override
    public void showMenu() {
        System.out.println("User Menu:");
        System.out.println("1. View available books");
        System.out.println("2. Borrow a book");
        System.out.println("3. Return a book");
        System.out.println("4. Search for content");
        System.out.println("8. Logout");
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
                performSearch(sc);
                break;
            case 8:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void performSearch(Scanner sc) {
        System.out.print("Enter search keyword: ");
        String keyword = sc.nextLine();

        // Search across all branches
        for (Library branch : allBranches) {
            List<Contents> results = branch.search(keyword);
            if (results.isEmpty()) {
                System.out.println("No results found in branch: " + branch.getName());
            } else {
                System.out.println("Results in branch: " + branch.getName());
                for (Contents content : results) {
                    System.out.println(content); // Assuming Contents class has a meaningful toString() method
                }
            }
        }
    }
}

