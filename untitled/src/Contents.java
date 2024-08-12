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
    
     //Setter for itemID
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

     //Setter for libraryID
    public void setLibraryID(int libraryID) {
        this.libraryID = libraryID;
    }
    
     //Setter for title
    public void setTitle(String title) {
        this.title = title;
    }
    
     //Setter for author
    public void setAuthor(String author) {
        this.author = author;
    }

     //Setter for publisher
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
     //Setter for productionYear
    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

     //Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

     //Setter for copies
    public void setCopies(int copies) {
        this.copies = copies;
    }
    
    //If borrowing, make sure copies are available
    
    public void borrowItem()
    {
        if (copies > 0) 
        {
            copies--;
            updateStatus();
        }
        else 
            System.out.println("No copies available to borrow.");
        
    }

    private void updateStatus() 
    {
        if (copies == 0) 
            
            status = "Not available";
        
    }
    
    //If return, we will increase the number of copies
    
     public void returnItem() 
     {
        copies++;
        status = "On shelf";
    }
    
}
