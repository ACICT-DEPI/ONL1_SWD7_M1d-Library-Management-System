package Library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subscriber {
    private int id;
    private String type;
    private String name;
    private String address;
    private String phone;
    private String s_email;
    private String password;
    private LocalDate subscriptionStartDate;
    private ArrayList<Integer> toReadList;
    private ArrayList<Integer> read;
    private ArrayList<Integer> waiting;

    public Subscriber(String type, String name, String address, String phone, String s_email, String password) {
        // new subscriber
        this.setType(type);
        this.setName(name);
        this.setAddress(address);
        this.setPhone(phone);
        this.setEmail(s_email);
        this.setPassword(password);
        this.subscriptionStartDate = LocalDate.now();
        this.toReadList = new ArrayList<Integer>();
        this.read = new ArrayList<Integer>();
        this.waiting = new ArrayList<Integer>();
    }

    public Subscriber(int id, String type, String name, String address, String phone,
                      String s_email, String password, LocalDate date, ArrayList<Integer> to_read_list,
                      ArrayList<Integer> read, ArrayList<Integer> waiting) {
        // load subscriber from database
        this.setId(id);
        this.setType(type);
        this.setName(name);
        this.setAddress(address);
        this.setPhone(phone);
        this.setEmail(s_email);
        this.setPassword(password);
        this.subscriptionStartDate = date;
        this.toReadList = to_read_list;
        this.read = read;
        this.waiting = waiting;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getType(){
        return type;
    }

    private void setType(String type){
        this.type = type;
    }

    public String getName(){
        return name;
    }

    private void setName(String name){
        if (name == null || name.trim().isEmpty()){
            System.out.println("Invalid name");
        } else{
            this.name = name;
        }
    }

    public String getPassword(){
        return password;
    }

    private void setPassword(String pass){
        if (pass == null || pass.trim().isEmpty()){
            System.out.println("Invalid password");
        } else{
            this.password = pass;
        }
    }

    public String getAddress(){
        return address;
    }

    public boolean isAdmin() {
        return "admin".equals(this.type);
    }

    private void setAddress(String address){
        this.address = address;
    }

    public String getPhone(){
        return phone;
    }

    private void setPhone(String phone){
        if (phone == null || phone.length() < 11){
            System.out.println("Invalid phone number");
        } else{
            this.phone = phone;
        }
    }

    public String getEmail(){
        return s_email;
    }

    private void setEmail(String s_email){
        if (s_email == null || !s_email.contains("@")){
            System.out.println("Invalid email");
        } else{
            this.s_email = s_email;
        }
    }

    public LocalDate getSubscriptionStartDate(){
        return subscriptionStartDate;
    }

    //method to check if subscriber is golden or not
    public boolean isGolden(){
        return type.equalsIgnoreCase("golden");
    }

    //method to calculate borrowing period based on subscription type
    public int getBorrowingPeriod(){
        if (isGolden()) {
            return 3 ;//3 months
        } else{
            return 3 * 7;//3 weeks
        }
    }

    private LocalDate calculateReturnDate(){
        return LocalDate.now().plusDays(getBorrowingPeriod());
    }

    //calculate late fees
    public double calculateLateFees(LocalDate actualReturnDate){
        LocalDate returnDate = calculateReturnDate();
        long daysLate = ChronoUnit.DAYS.between(returnDate, actualReturnDate);
        if (daysLate > 0) {
            return daysLate * 2.0; //2$ per late day
        } else{
            return 0.0;
        }
    }

    //borrow method
    public void borrow(String bookName){
        LocalDate returnDate = calculateReturnDate();
        System.out.println("Borrowed book: \"" + bookName + "\"");
        System.out.println("Return date: " + returnDate);
        LocalDate actualReturnDate = LocalDate.now();
        double lateFees = calculateLateFees(actualReturnDate);
        if (lateFees > 0){
            System.out.println("Late fees: $"+lateFees);
        } else{
            System.out.println("No late fees");
        }
    }

    // to read list
    public void addToReadList(int bookId, String bookName){
        toReadList.add(bookId);
        System.out.println("\"" + bookName + "\" has been added to your to-read list.");
    }

    public void toReadList(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to add a book to your to-read list? (yes/no)");
        String response = scanner.nextLine();
        if(response.equalsIgnoreCase("yes")){
            System.out.println("Enter the name of the book:");
            String bookName = scanner.nextLine();
            addToReadList(1, bookName);
        } else {
            System.out.println("No book was added to your to-read list.");
        }
    }

    public static List<Subscriber> loadAllSubscribers() throws SQLException {
        List<Subscriber> subscribers = new ArrayList<>();
        DBConnection newCon = new DBConnection();
        ResultSet resultSet = newCon.runQuery("select * from Subscribers");
        while (resultSet.next()) {
            int subscriberId = resultSet.getInt("subscriber_id");
            String fullName = resultSet.getString("full_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            boolean isAdmin = resultSet.getBoolean("is_admin");
            String type = resultSet.getString("type");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            // find the subscription start date and if not exist set to null
            LocalDate startSubscription = null;
            if (type.equals("Golden")) {
                // select form subscriptions to find the start date
                ResultSet subscriptionResultSet = newCon.runQuery(
                        "SELECT start_date FROM Subscriptions WHERE subscriber_id = " + subscriberId
                );
                if (subscriptionResultSet.next()) {
                    startSubscription = subscriptionResultSet.getDate("start_date").toLocalDate();
                }
            }
            // load the to_read_list and the read_list and the waiting_list
            ArrayList<Integer> read = new ArrayList<Integer>();
            // loop on the read table
            ResultSet readListResultSet = newCon.runQuery(
                    "SELECT book_id FROM ReadList WHERE subscriber_id = " + subscriberId
            );
            while (readListResultSet.next()) {
                read.add(readListResultSet.getInt("book_id"));
            }
            ArrayList<Integer> to_read = new ArrayList<Integer>();
            // loop on the to read table
            ResultSet toReadListResultSet = newCon.runQuery(
                    "SELECT book_id FROM ToReadList WHERE subscriber_id = " + subscriberId
            );
            while (toReadListResultSet.next()) {
                to_read.add(toReadListResultSet.getInt("book_id"));
            }

            ArrayList<Integer> waiting = new ArrayList<Integer>();
            // loop on the waiting table
            ResultSet waitingListResultSet = newCon.runQuery(
                    "SELECT book_id FROM WaitingList WHERE subscriber_id = " + subscriberId
            );
            while (waitingListResultSet.next()) {
                waiting.add(waitingListResultSet.getInt("book_id"));
            }

            Subscriber ss = new Subscriber(subscriberId, type, fullName, address, phone, email, password,
                                           startSubscription, read, to_read, waiting);
            subscribers.add(ss);
        }
        DBConnection.Close();
        return subscribers;
    }

    //method to send a reminder email before the due date
    public void sendReminder() {
        LocalDate returnDate = calculateReturnDate();
        LocalDate reminderDate = returnDate.minusDays(3); //Send reminder 3 days before due date

        if (LocalDate.now().isAfter(reminderDate) && LocalDate.now().isBefore(returnDate)) {
            // Simulate sending an email
            System.out.println("Reminder: Your borrowed book is due on " + returnDate + ". Please return it on time to avoid late fees.");
            System.out.println("Sending reminder to " + s_email);
            //integrate with an email API here
        } else {
            System.out.println("No reminder needed at this time.");
        }
    }
}
