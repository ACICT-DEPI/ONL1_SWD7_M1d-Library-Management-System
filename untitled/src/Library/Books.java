package Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Books extends Contents {
    private String isbn;
    //Constructor
    public Books(int itemID, int libraryID, String title, String author, String isnb,
                 String publisher, int productionYear, String status, int copies)
    {
        super(itemID, libraryID, "book", title, author, publisher, productionYear, status, copies);
        this.isbn = isnb;
    }

    public void saveBookToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("items.csv"))) {
//            writer.write(books[i].toString()); // save all data
            writer.newLine(); // Move to the next line after each book
            System.out.println("Books have been successfully saved to items.csv");
        } catch (IOException e) {
            System.out.println("An error occurred while saving books to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Book [Title: " + this.title + ", Author: " + this.author + ", ISBN: " + this.isbn + "]";
    }
}


