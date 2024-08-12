public class Books extends Contents {
    private String isbn;
    //Constructor
    public Books(int itemID, int libraryID, String title, String author, String isnb,
                 String publisher, int productionYear, String status, int copies)
    {
        super(itemID, libraryID, "book", title, author, publisher, productionYear, status, copies);
        this.isbn = isnb;
    }

    @Override
    public String toString() {
        return "Book [Title: " + this.title + ", Author: " + this.author + ", ISBN: " + this.isbn + "]";
    }
}


