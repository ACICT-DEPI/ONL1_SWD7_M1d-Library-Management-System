package Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Articles extends Contents {
    
    private String journal;
    private LocalDate publicationDate;
    public List<String> subscribers;
    public List<Articles> articles; //  TODO: should be removed but handle it's function first
    
      //Constructor

    public Articles(int itemID, int libraryID, String title, String author, String publisher,
                    int productionYear, String status, int copies,String journal,
                    LocalDate publicationDate)
    {
        super(itemID, libraryID, "article", title, author, publisher, productionYear, status, copies);
        this.journal = journal;
        this.publicationDate = publicationDate;
    }

    public void saveArticleToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("items.csv"))) {
            writer.write(this.toString());// TODO: add all data
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

    // Notify subscribers of new article
    public void notifySubscribersOfNewArticle(Articles article) {
        subscribers.forEach(subscriber ->
                System.out.println("Notifying subscriber " + subscriber + " about new article: " + article.getTitle()));
    }

    // Search for articles by author
    public List<Articles> searchByAuthor(String author) {
        List<Articles> matchingArticles = new ArrayList<>();
        for (Articles article : articles) { // articles هو List<Articles> هنا
            if (article.getAuthor().equals(author)) {
                matchingArticles.add(article);
            }
        }
        return matchingArticles;
    }


    @Override
    public String toString() {
        return "Article{" +"journal='" + journal + '\'' + ", publicationDate=" + publicationDate +'}';
    }
}

