package Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DigitalMedia extends Contents {
    private String mediaType;
    private String format;
    
      //Constructor
    
    public DigitalMedia(int itemID, int libraryID, String title, String author, String publisher, int productionYear, String status, int copies,String mediaType, String format)
    {
        super(itemID, libraryID, "digital media", title, author, publisher, productionYear, status, copies);
        this.mediaType = mediaType;
        this.format = format;
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
//            writer.write("Platform: " + platform);
//            writer.newLine();
//            writer.write("Average Rating: " + averageRating);
//            writer.newLine();
//            writer.write("Ratings: " + ratings.toString());
            writer.newLine();
            System.out.println("DigitalMedia information has been successfully saved to " + filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
     
     //Getter for mediaType
    public String getMediaType() {
        return mediaType;
    }
  
     //Getter for format
    public String getFormat() {
        return format;
    }

     //Setter for mediaType
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
     
     ////Setter for format
    public void setFormat(String format) {
        this.format = format;
    }
    
    
}

    

