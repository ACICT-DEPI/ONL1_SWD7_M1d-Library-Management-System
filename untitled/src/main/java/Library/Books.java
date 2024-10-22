package Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Books extends Contents {
    Books[] books;
    private int sno;
    private int count;  // TODO: should be replaced to be copies from Content class

    //Constructor

    public Books(int itemID, int libraryID, String title, String author, String publisher, int productionYear, String status, int copies,int sno,int count)
    {
        super(itemID, libraryID, "book", title, author, publisher, productionYear, status, copies );
        this.sno = sno;
        this.count = count;
    }

    // Getter for sno
    public int getsno() {
        return sno;
    }

    //// Getter for count
    public int getCount() {
        return count;
    }

    // Setter for sno
    public void setsno(int sno) {
        this.sno = sno;
    }

    // A method to find a book with a given serial number and return its index, or -1 if not found
    public int findBook(int sno) {
        for (int i = 0; i < count; i++) {
            if (books[i].getsno() == sno) {
                return i;
            }
        }
        return -1;
    }

    // A method to get a book at a specific index, returns null if out of bounds
    public Books getBook(int index) {
        if (index >= 0 && index <= count) {
            return books[index];
        }
        return null;
    }

    // A method to add a book to the library, returns true if added successfully, false otherwise
    public boolean addBook(Books b) {
        if (count < books.length && findBook(b.getsno()) == -1) {
            books[count] = b;
            count++;
            return true;
        }
        return false;

    }

    // A method to remove a book from the library, returns true if removed successfully, false otherwise
    public boolean removeBook(Books b){
        int index = findBook(b.getsno());
        if (index != -1) {
            for (int i = index; i < (count - 1); i++) {
                books[i] = books[i+1]; // Shift left.
            }
            count--;
            return true;
        }
        return false;
    }

    // A method to print information of all books in the library
    public void getAllBook() {
        System.out.println("All the book in the system are: " + "\r\n");

        for(int i = 0; i < count; i++){
            System.out.println(books[i]);
            System.out.println("\r\n");
        }

    }

    public void saveBookToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("items.csv"))) {
//            writer.write(books[i].toString()); // TODO: save all data
            writer.newLine(); // Move to the next line after each book
            System.out.println("Books have been successfully saved to items.csv");
        } catch (IOException e) {
            System.out.println("An error occurred while saving books to file: " + e.getMessage());
        }
    }



    @Override
    public String toString() {
        return "Book [Title: " + this.title + ", Author: " + this.author + ", SNo: " + this.sno + "]";
    }

}

