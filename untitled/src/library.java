import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class library {
    // this class represents every branch
    private final int id;
    public String branchName;
    private List<Contents> contentsList;
    // Constructor
    public library(int id) {
        this.id = id;
        this.branchName = "";
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

    // Method to save library data to a file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("libs.txt", true))) {
            writer.write(this.id + "," + this.branchName);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while saving library data.");
            e.printStackTrace();
        }
    }

    // Override the toString method to give a string representation of the Library
    @Override
    public String toString() {
        return "Library [ID: " + id + ", Name: " + branchName + "]";
    }
}
