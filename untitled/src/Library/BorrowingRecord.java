package Library;

import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BorrowingRecord {
    private String subscriberName;
    private String itemTitle;
    private String itemType;
    private Date borrowDate;
    private Date dueDate;
    private boolean returned;

    // Constructor
    public BorrowingRecord(String subscriberName, String itemTitle, String itemType, Date borrowDate, Date dueDate) {
        this.subscriberName = subscriberName;
        this.itemTitle = itemTitle;
        this.itemType = itemType;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

     // Getter for subscriberName
    public String getSubscriberName() {
        return subscriberName;
    }

     // Getter for itemTitle
    public String getItemTitle() {
        return itemTitle;
    }

     // Getter for itemType
    public String getItemType() {
        return itemType;
    }

     // Getter for borrowDate
    public Date getBorrowDate() {
        return borrowDate;
    }
 
     // Getter for dueDate
    public Date getDueDate() {
        return dueDate;
    }

     // Getter for returned
    public boolean isReturned() {
        return returned;
    }

    // Method to mark the item as returned
    public void markAsReturned() {
        this.returned = true;
    }
    
     //للتحقق مما إذا كانت السلفة تجاوزت تاريخ الاستحقاق
    public boolean isOverdue() {
        return new Date().after(dueDate);
    }

     //لتحديث تاريخ الاستحقاق
    public void extendDueDate(Date newDueDate) {
        this.dueDate = newDueDate;
        System.out.println("Due date extended to: " + newDueDate);
    }
 
     //لحصول على مدة السلفة  (عدد الأيام) بين تاريخ الاقتراض وتاريخ الاستحقاق
    public long getBorrowDuration() {
        long duration = dueDate.getTime() - borrowDate.getTime();
        return duration / (1000 * 60 * 60 * 24); // Convert milliseconds to days
    }
    

     //لإرسال تذكير للمشترك قبل موعد الاستحقاق.
    public void sendReminder() {
        if (!isReturned() && !isOverdue()) {
            System.out.println("Reminder: " + subscriberName + ", your item '" + itemTitle + "' is due on " + dueDate);
        }
    }

    //لإعلام المشترك عند تجاوز موعد الاستحقاق
    public void notifyOverdue() {
        if (isOverdue() && !returned) {
            System.out.println("Notice: " + subscriberName + ", your item '" + itemTitle + "' is overdue!");
        }
    }


    // Method to display borrowing record information
    public void displayRecord() {
        System.out.println("Subscriber Name: " + subscriberName);
        System.out.println("Item Title: " + itemTitle);
        System.out.println("Item Type: " + itemType);
        System.out.println("Borrow Date: " + borrowDate);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Returned: " + (returned ? "Yes" : "No"));
    }
     // Method to save the borrowing record to a text file
    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true for append mode
            writer.write("Subscriber Name: " + subscriberName);
            writer.newLine();
            writer.write("Item Title: " + itemTitle);
            writer.newLine();
            writer.write("Item Type: " + itemType);
            writer.newLine();
            writer.write("Borrow Date: " + borrowDate);
            writer.newLine();
            writer.write("Due Date: " + dueDate);
            writer.newLine();
            writer.write("Returned: " + (returned ? "Yes" : "No"));
            writer.newLine();
            writer.write("--------");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    
     // toString method to provide a string representation of the object
    @Override
    public String toString() {
        return "Borrowing Record {\n " +
               "Subscriber Name: " + subscriberName +
                "\nItem Title: " + itemTitle +"\nItem Type: " + itemType +
                "\nBorrow Date: " + borrowDate +"\nDue Date: " + dueDate +
                "\nReturned: " + (returned ? "Yes" : "No") +"\n}";
    }
}
