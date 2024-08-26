package Library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    // this class represents every branch
    private final int id;
    public String branchName;
    private List<Contents> contentsList;
    // Constructor
    public Library(int id, String name) {
        this.id = id;
        this.branchName = name;
        this.saveLibraryToFile();
    }

    public static List<Library> loadAllBranches() {
        List<Library> branches = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("libs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    branches.add(new Library(id, name));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading library data.");
        }
        return branches;
    }

    // Getter for the id
    public int getId() {
        return id;
    }

    // Getter for the name
    public String getName() {
        return branchName;
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("libs.txt", true))) {
            writer.write(this.id + "," + this.branchName);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while saving library data.");
        }
    }

    // Override the toString method to give a string representation of the Library.Library
    @Override
    public String toString() {
        return "Library.Library [ID: " + id + ", Name: " + branchName + "]";
    }
}
