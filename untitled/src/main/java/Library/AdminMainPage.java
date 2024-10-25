package Library;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMainPage implements LibraryView {
    private final List<Library> allBranches;
    private final Subscriber subscriber;

    public AdminMainPage(List<Library> allBranches, Subscriber subscriber) {

        this.allBranches = allBranches;
        this.subscriber = subscriber;
    }


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
        System.out.println("Q. Logout");
    }

    @Override
    // Method to handle the user's selection
    public void handleSelection(Scanner sc, int choice) throws SQLException {
        switch (choice) {
            case 1 -> addNewContentType();
            case 2 -> removeContent();
            case 3 -> viewAllSubscribers();
            case 4 -> viewAllContents();  // Both cases 4 and 5 handle the same action
            case 5 -> viewTop10MostRatedContents();
            case 6 -> performSearch(sc);
            case 7 -> viewBorrowingLog();
            case 8 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    @Override
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
                    System.out.println(content); // Assuming Contents class has a meaningful toString() method
                }
            }
        }
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

            // You may need to get the subscriber ID from the current session or context
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
    @Override
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

    private void addNewContentType() {}
    private void removeContent() {}
    private void viewAllSubscribers() throws SQLException {
        List<Subscriber> all = Subscriber.loadAllSubscribers();
        System.out.printf("%-15s %-30s %-30s %-15s %-15s %-20s %-15s %-20s%n",
                "Subscriber ID", "Full Name", "Email", "Is Admin", "Type", "Address", "Phone", "Start Date");
        System.out.println("--------------------------------------------------------------------------");

        for (Subscriber subscriber : all) {
            System.out.printf("%-15d %-30s %-30s %-15b %-15s %-20s %-15s %-20s%n",
                    subscriber.getId(),
                    subscriber.getName(),
                    subscriber.getEmail(),
                    subscriber.isAdmin(),
                    subscriber.getType(),
                    subscriber.getAddress(),
                    subscriber.getPhone(),
                    subscriber.getSubscriptionStartDate() != null ? subscriber.getSubscriptionStartDate().toString() : "N/A");
        }
    }
    private void viewTop10MostRatedContents() {
        ArrayList<Contents> topRatedContents = new ArrayList<>();

        // SQL query to select top 10 most rated contents
        String sql = "SELECT * FROM Contents ORDER BY average_rate DESC LIMIT 10";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Process the result set
            while (resultSet.next()) {
                int contentId = resultSet.getInt("content_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                int productionYear = resultSet.getInt("production_year");
                String status = resultSet.getString("status");
                int copies = resultSet.getInt("copies");
                double averageRate = resultSet.getDouble("average_rate");

                // Create a Contents object (assuming appropriate constructor)
                Contents content = new Contents(contentId, 0, title, author, publisher, productionYear,
                        status, copies, averageRate);
                topRatedContents.add(content);
            }

            // Display the top rated contents
            System.out.println("Top 10 Most Rated Contents:");
            for (Contents content : topRatedContents) {
                System.out.printf("ID: %d, Title: %s, Author: %s, Average Rate: %.2f%n",
                        content.getItemID(), content.getTitle(), content.getAuthor(), content.averageRate);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving top rated contents: " + e.getMessage());
        }
    }

    private void viewBorrowingLog() {}
}
