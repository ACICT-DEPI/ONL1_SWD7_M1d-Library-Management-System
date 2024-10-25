
package javaapplication٥;

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DigitalMedia extends Contents {
    private String mediaType;
    private String format;
    private String platform;
    private double averageRating;
    public List<Integer> ratings;
    private String description;
    public List<String> comments;
    
      //Constructor
    public DigitalMedia(int itemID, int libraryID, String title, String author, String publisher, int productionYear, String status, int copies, String mediaType, String format, int count, String platform)
    {
        super(itemID, libraryID, "digital media", title, author, publisher, productionYear, status, copies);
        this.mediaType = mediaType;
        this.format = format;
        this. platform = platform;
        this.description = description;

    }
     
     //Getter for mediaType
    public String getMediaType() {
        return mediaType;
    }
  
     //Getter for format
    public String getFormat() {
        return format;
    }

     //Getter for platform
    public String getPlatform() {
        return platform;
    }

    
     //Getter for averageRating
    public double getAverageRating() {
        return averageRating;
    }

     //Getter for description
    public String getDescription() {
        return description;
    }
   
     //Setter for mediaType
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
     
     //Setter for format
    public void setFormat(String format) {
        this.format = format;
        
    }
     
     //Setter for platform
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
     //method of rating
    public void rate(int rating) {
        this.ratings.add(rating);
        this.calculateAverageRating();
    }

     //method to update the averageRating
    private void calculateAverageRating() {
        if (this.ratings.isEmpty()) {
            this.averageRating = 0; // أو تعيين قيمة افتراضية أخرى
            return;
        }
        double sum = 0;
        for (int r : this.ratings) {
            sum += r;
        }
        this.averageRating = sum / this.ratings.size();
    }
    
    
    // دالة لإضافة تعليق
    public void addReview(String comment) {
        comments.add(comment);
    }
    
    // دالة لعرض جميع التعليقات
    public void displayComments() {
        for (String comment : comments) {
            System.out.println(comment);
        }
    }
    
     // دالة لاستخراج ملخص
    public String getSummary() {
        // هنا نستخدم خوارزمية لتلخيص النصوص
        // هذا مثال بسيط لاستخراج أول جملتين كملخص
        if (description != null && !description.isEmpty()) {
            String[] sentences = description.split("\\.");
            if (sentences.length >= 2) {
                return sentences[0] + ". " + sentences[1];
            } else {
                return description; // إذا كان الوصف جملة واحدة أو أقل، نعيده كاملاً
            }
        } else {
            return "لا يوجد وصف متاح.";
        }
    }
    
    // Save DigitalMedia data to a text file
    public void saveDigitalMediaToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("DigitalMedia Information:");
            writer.newLine();
            writer.write("Item ID: " + getItemID());
            writer.newLine();
            writer.write("Library ID: " + getLibraryID());
            writer.newLine();
            writer.write("Title: " + getTitle());
            writer.newLine();
            writer.write("Author: " + getAuthor());
            writer.newLine();
            writer.write("Publisher: " + getPublisher());
            writer.newLine();
            writer.write("Production Year: " + getProductionYear());
            writer.newLine();
            writer.write("Status: " + getStatus());
            writer.newLine();
            writer.write("Copies: " + getCopies());
            writer.newLine();
            writer.write("Media Type: " + mediaType);
            writer.newLine();
            writer.write("Format: " + format);
            writer.newLine();
            writer.write("Platform: " + platform);
            writer.newLine();
            writer.write("Average Rating: " + averageRating);
            writer.newLine();
            writer.write("Ratings: " + ratings.toString());
            writer.newLine();
            System.out.println("DigitalMedia information has been successfully saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving DigitalMedia to file: " + e.getMessage());
        }
    }
    
   

     // toString method to provide a string representation of the digital media
    @Override
    public String toString() {
        return """
DigitalMedia {
Item ID: """ + getItemID() +"\nLibrary ID: " + getLibraryID() +"\nTitle: " + getTitle() +"\nAuthor: " + getAuthor() +"\nPublisher: " + getPublisher() +"\nProduction Year: " + getProductionYear() +"\nStatus: " + getStatus() +"\nCopies: " + getCopies() +"\nMedia Type: " + mediaType +"\nFormat: " + format +"\nPlatform: " + platform +"\nAverage Rating: " + averageRating +"\nRatings: " + ratings +"\n}";
    }
}

    

