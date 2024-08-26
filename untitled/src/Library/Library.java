package Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    static List<Library> loadAllBranches() {
        List<Library> allBranches = null;
        // load all branches from file
        return allBranches;
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
