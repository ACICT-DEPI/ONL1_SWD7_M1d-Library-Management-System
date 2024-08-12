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

    

