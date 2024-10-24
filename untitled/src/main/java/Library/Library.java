package Library;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library {
    // this class represents every branch
    private int id;
    public String branchName;
    public String branchAddress;
    private List<Integer> contentsList;
    // Constructor
    public Library(int id, String name, String address, ArrayList<Integer> contetns) {
        this.id = id;
        this.branchName = name;
        this.branchAddress = address;
        this.contentsList = contetns;
    }

    public Library(String name, String address) throws SQLException {
        this.branchName = name;
        this.branchAddress = address;
        this.contentsList = new ArrayList<Integer>();
        DBConnection newCon = new DBConnection();
        try {
            // Use PreparedStatement to insert the new branch
            String sql = "INSERT INTO Library (branch_name, branch_address) VALUES (?, ?)";
            PreparedStatement preparedStatement = newCon.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.branchName);
            preparedStatement.setString(2, this.branchAddress);

            int rowsAffected = preparedStatement.executeUpdate(); // Execute the insert
            if (rowsAffected > 0) {
                System.out.println("New branch created");

                // Retrieve the generated ID
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int branchId = generatedKeys.getInt(1); // Get the generated ID
                    System.out.println("New branch ID: " + branchId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating new branch: " + e.getMessage());
        } finally {
            newCon.Close(); // Ensure the connection is closed
        }
    }

    public static List<Library> loadAllBranches() throws SQLException {
        List<Library> branches = new ArrayList<>();
        DBConnection newCon = new DBConnection();
        ResultSet resultSet = newCon.runQuery("SELECT * FROM Library");

        while (resultSet.next()) {
            int branchId = resultSet.getInt("branch_id");
            String branchName = resultSet.getString("branch_name");
            String branchAddress = resultSet.getString("branch_address");
            ArrayList<Integer> contentList = new ArrayList<Integer>();

            // Now load all the content IDs for this branch
            ResultSet contentResultSet = newCon.runQuery("SELECT content_id FROM Contents WHERE branch_id = " + branchId);

            // Add content IDs to the contentList using a normal list operation
            while (contentResultSet.next()) {
                int contentId = contentResultSet.getInt("content_id");
                contentList.add(contentId);  // Directly add the content ID
            }

            // Create a Branch object and add it to the list
            Library branch = new Library(branchId, branchName, branchAddress, contentList);
            branches.add(branch);
        }

        // Close the database connection
        newCon.Close();
        return branches;
    }

    // Getter for the id
    public int getId() {
        return id;
    }

    // Getter for the name
    public String getName() {
        return this.branchName;
    }

    // Setter for the name
    public void setName(String name) {
        this.branchName = name;
    }

    public ArrayList<Contents> getContents () throws SQLException {
        ArrayList<Contents> contentsList = new ArrayList<>();
        DBConnection newCon = new DBConnection();
        if (this.contentsList.isEmpty()) {
            System.out.println("Content list is empty. No contents to retrieve.");
            return contentsList;
        }
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM Contents WHERE content_id IN (");
            for (int i = 0; i < this.contentsList.size(); i++) {
                sql.append("?");
                if (i < this.contentsList.size() - 1) {
                    sql.append(", ");
                }
            }
            sql.append(")");
            PreparedStatement preparedStatement = newCon.getConnection().prepareStatement(sql.toString());
            for (int i = 0; i < this.contentsList.size(); i++) {
                preparedStatement.setInt(i + 1, this.contentsList.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
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
                //Contents(int itemID, int libraryID, String title, String author, String publisher, int productionYear, String status, int copies)
                Contents content = new Contents(contentId,this.id, title, author, publisher, productionYear,
                        status, copies, averageRate);
                contentsList.add(content);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving contents from the database: " + e.getMessage());
        } finally {
            newCon.Close();
        }
        return contentsList;
    }
    public void addContent(Contents newContent) {
        // add new content to the content list
        if (newContent != null) {
            // Assuming the Contents class has a method getId() to retrieve the content ID
            int contentId = newContent.getItemID();
            this.contentsList.add(contentId); // Add the content ID to the contentsList
            System.out.println("Content added to the branch.");
        } else {
            System.out.println("Cannot add null content.");
        }
    }

    public List<Contents> search(String keyword) throws SQLException {
        List<Contents> results = new ArrayList<>();
        ArrayList<Contents> content = getContents();
        for (Contents cont : content) {
            if (cont.matches(keyword) == 0) {
                results.add(cont);
            }
        }
        return results;
    }

    // Override the toString method to give a string representation of the Library.
    @Override
    public String toString() {
        return "Branch [ID: " + id + ", Name: " + branchName + ", Address: " + branchAddress + "]";
    }
}