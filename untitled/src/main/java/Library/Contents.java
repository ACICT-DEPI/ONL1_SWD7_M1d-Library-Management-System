package Library;


public abstract class Contents
{
    protected int itemID;
    protected int libraryID;
    protected String category;
    protected String title;
    protected String author;
    protected String publisher;
    protected int productionYear;
    protected String status;
    protected int copies;

    // Constructor

    public Contents(int itemID, int libraryID, String category, String title, String author, String publisher, int productionYear, String status, int copies)
    {
        this.itemID = itemID;
        this.libraryID = libraryID;
        this.category = category;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.productionYear = productionYear;
        this.status = status;
        this.copies = copies;

    }

   // Getters for itemID
   public int getItemID() {
    return itemID;
}

// Getters for libraryID
public int getLibraryID() {
    return libraryID;
}

// Getters for category
public String getCategory() {
    return category;
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
}




