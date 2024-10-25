package Library;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class UserMainPage implements LibraryView {
    private final List<Library> allBranches;
    private final Subscriber subscriber;

    public UserMainPage(List<Library> allBranches, Subscriber subscriber) {
        this.allBranches = allBranches;
        this.subscriber = subscriber;
    }

    @Override
    public void borrowBook() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Ask the user for the book ID
        System.out.print("Enter the ID of the book you wish to borrow: ");
        int contentId = scanner.nextInt(); // Read the content ID

        // Step 2: Update the Borrowing Records Table
        try {
            // Get the current date for borrow date
            LocalDate borrowDate = LocalDate.now();
            // Set the due date (e.g., two weeks from now)
            LocalDate dueDate = borrowDate.plusWeeks(2);

            // Prepare the SQL statement for inserting into BorrowingRecords
            String insertBorrowingSql = "INSERT INTO BorrowingRecords (subscriber_id, content_id, borrow_date, due_date, is_returned) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement borrowingStatement = DBConnection.getConnection().prepareStatement(insertBorrowingSql);
            borrowingStatement.setInt(1, subscriber.getId()); // Assuming you have a method to get the current user ID
            borrowingStatement.setInt(2, contentId);
            borrowingStatement.setDate(3, Date.valueOf(borrowDate));
            borrowingStatement.setDate(4, Date.valueOf(dueDate));
            borrowingStatement.setBoolean(5, false); // Initially, the book is not returned

            int rowsInserted = borrowingStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Book borrowed successfully!");

                // Step 3: Update the Content Table to decrease copies
                String updateContentSql = "UPDATE Contents SET copies = copies - 1 WHERE content_id = ?";
                PreparedStatement contentStatement = DBConnection.getConnection().prepareStatement(updateContentSql);
                contentStatement.setInt(1, contentId);
                int rowsUpdated = contentStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Number of copies updated successfully.");
                } else {
                    System.out.println("Failed to update the number of copies. Book may not exist or no copies left.");
                }
            } else {
                System.out.println("Failed to borrow the book. Please check the book ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error during borrowing operation: " + e.getMessage());
        } finally {
            DBConnection.Close(); // Ensure the connection is closed after operation
        }
    }

    @Override
    public void returnBook() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the book you wish to return: ");
        int contentId = scanner.nextInt();

        // Update the Borrowing Records table
        DBConnection newCon = new DBConnection();
        try {
            // Update the Borrowing Records to mark the book as returned
            String updateBorrowingSql = "UPDATE BorrowingRecords SET is_returned = TRUE WHERE content_id = ? AND subscriber_id = ?";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(updateBorrowingSql);

            int subscriberId = subscriber.getId();
            preparedStatement.setInt(1, contentId);
            preparedStatement.setInt(2, subscriberId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book returned successfully.");

                // Update the Copies in the Contents table
                String updateContentSql = "UPDATE Contents SET copies = copies + 1 WHERE content_id = ?";
                PreparedStatement updateContentStmt = DBConnection.getConnection().prepareStatement(updateContentSql);
                updateContentStmt.setInt(1, contentId);
                updateContentStmt.executeUpdate();
            } else {
                System.out.println("No borrowing record found for this book.");
            }
        } catch (SQLException e) {
            System.err.println("Error returning the book: " + e.getMessage());
        } finally {
            DBConnection.Close();
        }
    }

    public void viewAllContents() {
        for (Library branch : allBranches) {
            System.out.println("Contents for branch: " + branch.branchName);
            try {
                ArrayList<Contents> contents = branch.getContents(); // Get contents from each branch
                if (contents.isEmpty()) {
                    System.out.println("No contents available in this branch.");
                } else {
                    for (Contents content : contents) {
                        // Print the content details
                        System.out.println("Content ID: " + content.getItemID());
                        System.out.println("Title: " + content.getTitle());
                        System.out.println("Author: " + content.getAuthor() + " Publisher: " + content.getPublisher());
                        System.out.println("Production Year: " + content.getProductionYear() + " Status: " + content.getStatus());
                        System.out.println("Copies: " + content.getCopies() + " Average Rate: " + content.averageRate);
                        System.out.println("------------------------------");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving contents for branch " + branch.branchName + ": " + e.getMessage());
            }
        }
    }

    @Override
    public void showMenu() {
        System.out.println("User Menu:");
        System.out.println("1. View available books");
        System.out.println("2. Borrow a book");
        System.out.println("3. Return a book");
        System.out.println("4. Search for content");
        System.out.println("Q. Logout");
    }

    @Override
    public void handleSelection(Scanner sc, int choice) throws SQLException {
        switch (choice) {
            case 1:
                System.out.println("Displaying available books...");
                viewAllContents();
                break;
            case 2:
                borrowBook();
                break;
            case 3:
                returnBook();
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

    public void performSearch(Scanner sc) throws SQLException {
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
                    System.out.println(content);
                }
            }
        }
    }
}

