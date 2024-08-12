package javaapplication٥;

import java.time.LocalDate;

public class Articles extends Contents {
    
    private String journal;
    private LocalDate publicationDate;
    
      //Constructor
    
    public Articles(int itemID, int libraryID, String title, String author, String publisher, int productionYear, String status, int copies,String journal,LocalDate publicationDate) 
    {
        super(itemID, libraryID, "article", title, author, publisher, productionYear, status, copies);
        this.journal = journal;
        this.publicationDate = publicationDate;
    }
    
     //Getting for journal
    public String getJournal() {
        return journal;
    }
    
     //Getting for publicationDate
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    
     //setting for journal
    public void setJournal(String journal) {
        this.journal = journal;
    }
    
     //setting for publicationDate
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
    
}

    
