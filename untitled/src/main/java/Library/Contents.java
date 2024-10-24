package Library;


public class Contents
{
    protected int itemID;
    protected int libraryID;
    protected String title;
    protected String author;
    protected String publisher;
    protected int productionYear;
    protected String status;
    protected int copies;
    protected double averageRate;

    // Constructor

    public Contents(int itemID, int libraryID, String title, String author,
                    String publisher, int productionYear, String status, int copies, double averageRate)
    {
        this.itemID = itemID;
        this.libraryID = libraryID;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.productionYear = productionYear;
        this.status = status;
        this.copies = copies;
        this.averageRate = averageRate;

    }

   // Getters for itemID
   public int getItemID() {
    return itemID;
}

    // Getters for libraryID
    public int getLibraryID() {
        return libraryID;
    }

    // Getters for title
    public String getTitle() {
        return title;
    }

    // Getters for author
    public String getAuthor() {
        return author;
    }

    // Getters for publisher
    public String getPublisher() {
        return publisher;
    }

    // Getters for productionYear
    public int getProductionYear() {
        return productionYear;
    }

    // Getters for status
    public String getStatus() {
        return status;
    }

    // Getters for copies
    public int getCopies() {
    return copies;
}

// Compare function
    public int matches(String keyWord) {
        return 0;
    }
}
