package Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public void saveArticleToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("items.txt"))) {
//            writer.write(article.toString());// add all data
            writer.newLine(); // Move to the next line after each article
            System.out.println("Articles have been successfully saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving articles to file: " + e.getMessage());
        }
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

    
