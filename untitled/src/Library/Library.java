package Library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library {
    // this class represents every branch
    private static int nextId = 1;
    private int id;
    public String branchName;
    private List<Contents> contentsList;
    // Constructor
    public Library(String name) {
        this.id = nextId++;
        this.branchName = name;
    }

    public Library(int id, String name) {
        this.id = id;
        this.branchName = name;
    }

    public static List<Library> loadAllBranches() {
        List<Library> branches = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("libraries.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    Library library = new Library(id, name);
                    branches.add(library);
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading library data: " + e.getMessage());
        }
        return branches;
    }


//    //options
//    private static void libraryOptions(Subscriber subscriber, Scanner sc) {
//        LocalDate subscriptionStartDate = subscriber.getSubscriptionStartDate();
//        System.out.println("Subscription start date: " + subscriptionStartDate);
//
//        //borrow
//        int borrowingPeriod = subscriber.getBorrowingPeriod();
//        if (borrowingPeriod == 3) {
//            System.out.println("Borrowing period for " + subscriber.getName() + " : 3 months");
//        } else {
//            System.out.println("Borrowing period for " + subscriber.getName() + " : 3 weeks");
//        }
//
//        //to read list
//        subscriber.toReadList();
//        System.out.println("Do you want to borrow a book? (yes/no)");
//        String borrowResponse = sc.nextLine();
//        if (borrowResponse.equalsIgnoreCase("yes")) {
//            System.out.println("Enter the name of the book you want to borrow: ");
//            String bookName = sc.nextLine();
//            subscriber.borrow(bookName);
//        } else {
//            System.out.println("No book borrowed.");
//        }
//    }

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

    public List<Contents> getContents () {
        return this.contentsList;
    }
    public void addContent(Contents newContent) {
        // add new content to the content list

    }

    // Method to save library data to a file
    public void saveLibraryToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("libraries.csv", true))) {
            writer.write(id + "," + branchName + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving library data: " + e.getMessage());
        }
    }

    // Override the toString method to give a string representation of the Library.
    @Override
    public String toString() {
        return "Library.Library [ID: " + id + ", Name: " + branchName + "]";
    }
}